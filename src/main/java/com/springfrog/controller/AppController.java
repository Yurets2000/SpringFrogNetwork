package com.springfrog.controller;

import com.springfrog.dto.SimpleChat;
import com.springfrog.dto.User;
import com.springfrog.dto.UserProfile;
import com.springfrog.model.MessageWrapper;
import com.springfrog.model.SearchedUser;
import com.springfrog.service.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

import static com.springfrog.dto.UserProfileType.*;

@Controller
@SessionAttributes("roles")
public class AppController {

    @Autowired
    Environment environment;

    @Autowired
    InitService initService;

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    SimpleChatService simpleChatService;

    @Autowired
    GroupChatService groupChatService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @PostConstruct
    public void initialize() {
        initService.initializeDataSource();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        if (isCurrentAuthenticationAnonymous()) {
            System.out.println("---------------ANONYMOUS--------------------");
            return "index";
        } else {
            Set<UserProfile> principalProfiles = getPrincipal().getUserProfiles();
            for (UserProfile profile : principalProfiles) {
                String type = profile.getType();
                if (type.equals(ADMIN.toString()) || type.equals(DBA.toString())) {
                    System.out.println("---------------LIST--------------------");
                    return "redirect:/list";
                } else if (type.equals(USER.toString())) {
                    System.out.println("---------------PROFILE--------------------");
                    return "redirect:/profile";
                }
            }
        }
        return "index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedInUser", getPrincipal().getSsoId());
        return "usersList";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(ModelMap model) {
        if (isCurrentAuthenticationAnonymous()) {
            User user = new User();
            model.addAttribute("user", user);
            return "registration";
        } else {
            return "redirect:/logout";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid User user, BindingResult result,
                               ModelMap model) {
        user.setUserProfiles(new HashSet<>(Arrays.asList(userProfileService.findByType("USER"))));
        if (result.hasErrors()) {
            return "registration";
        }
        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
            FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }

        userService.saveUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
        model.addAttribute("loggedInUser", getPrincipal().getSsoId());
        return "registrationSuccess";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public String userForm(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedInUser", getPrincipal().getSsoId());
        return "userForm";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult result,
                          ModelMap model) {
        if (result.hasErrors()) {
            return "userForm";
        }
        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
            FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "userForm";
        }
        if (user.getUserProfiles().isEmpty()) {
            FieldError profilesError = new FieldError("user", "userProfiles", messageSource.getMessage("NotEmpty.user.userProfiles", null, Locale.getDefault()));
            result.addError(profilesError);
            return "userForm";
        }
        userService.saveUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
        model.addAttribute("loggedInUser", getPrincipal().getSsoId());
        return "redirect:/list";
    }

    @RequestMapping(value = "/edit-user-{ssoId}", method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedInUser", getPrincipal().getSsoId());
        return "registration";
    }

    @RequestMapping(value = "/edit-user-{ssoId}", method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String ssoId) {
        if (result.hasErrors()) {
            return "registration";
        }

        userService.updateUser(user);

        model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        model.addAttribute("loggedInUser", getPrincipal().getSsoId());
        return "registrationSuccess";
    }

    @RequestMapping(value = "/delete-user-{ssoId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/list";
    }

    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedInUser", getPrincipal().getSsoId());
        return "accessDenied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/logout";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login";
    }

    @Transactional
    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public String friendsPage(ModelMap modelMap) {
        User principal = getPrincipal();
        Hibernate.initialize(principal.getFriends());
        modelMap.put("friends", principal.getFriends());
        return "friends";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchPage(ModelMap modelMap) {
        modelMap.put("searchedUser", null);
        return "search";
    }

    @Transactional
    @RequestMapping(value = "/search-user", method = RequestMethod.GET)
    public String searchUser(@RequestParam String ssoId, ModelMap modelMap) {
        User user = userService.findBySSO(ssoId);
        User principal = getPrincipal();
        modelMap.addAttribute("searchedUser", new SearchedUser(user,
                userService.canAddToFriends(principal, user),
                userService.isAlreadyInvited(principal, user),
                userService.isAlreadyInvited(user, principal)));
        return "search";
    }

    @Transactional
    @RequestMapping(value = "/invite-user-{ssoId}", method = RequestMethod.POST)
    public String inviteUser(@PathVariable String ssoId) {
        User invitee = userService.findBySSO(ssoId);
        userService.inviteUser(getPrincipal(), invitee);
        return "search";
    }

    @Transactional
    @RequestMapping(value = "/add-friend-{ssoId}", method = RequestMethod.POST)
    public String addFriend(@PathVariable String ssoId) {
        User friend = userService.findBySSO(ssoId);
        userService.addToFriends(getPrincipal(), friend);
        return "search";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String principalProfilePage(RedirectAttributes attributes) {
        attributes.addAttribute("ssoId", getPrincipal().getSsoId());
        return "redirect:/view-profile-{ssoId}";
    }

    @RequestMapping(value = "/view-profile-{ssoId}", method = RequestMethod.GET)
    public String profilePage(@PathVariable("ssoId") String ssoId, ModelMap modelMap) {
        User user = userService.findBySSO(ssoId);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("loggedInUser", getPrincipal().getSsoId());
        return "profile";
    }

    @Transactional
    @RequestMapping(value = "/upload-photo", method = RequestMethod.POST)
    public String uploadPhoto(@RequestParam CommonsMultipartFile file) {
        User principal = getPrincipal();
        userService.updateProfilePhoto(principal, file);
        return "redirect:/profile";
    }

    @Transactional
    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    public String chatsPage(ModelMap modelMap) {
        User principal = getPrincipal();
        List<SimpleChat> chats = principal.getSimpleChats();
        chats.forEach(chat -> simpleChatService.initializeSimpleChat(principal, chat));
        modelMap.addAttribute("chats", chats);
        return "chats";
    }

    @Transactional
    @RequestMapping(value = "/simple-chat-{chatId}", method = RequestMethod.GET)
    public String simpleChatPage(@PathVariable Integer chatId, ModelMap modelMap) {
        User principal = getPrincipal();
        SimpleChat chat = simpleChatService.findById(chatId);
        Hibernate.initialize(chat.getMessages());
        simpleChatService.initializeSimpleChat(principal, chat);
        modelMap.addAttribute("chat", chat);
        modelMap.addAttribute("messageWrapper", new MessageWrapper());
        return "simpleChat";
    }

    @Transactional
    @RequestMapping(value = "/get-simple-chat-{ssoId}", method = RequestMethod.GET)
    public String getSimpleChat(@PathVariable String ssoId, RedirectAttributes attributes) {
        User principal = getPrincipal();
        User user = userService.findBySSO(ssoId);
        SimpleChat chat = simpleChatService.getSimpleChat(principal, user);
        attributes.addAttribute("chatId", chat.getId());
        return "redirect:/simple-chat-{chatId}";
    }

    @Transactional
    @RequestMapping(value = "/write-to-simple-chat-{chatId}", method = RequestMethod.POST)
    public String writeToSimpleChat(@PathVariable Integer chatId,
                                    @ModelAttribute("messageWrapper") MessageWrapper messageWrapper,
                                    RedirectAttributes attributes) {
        User principal = getPrincipal();
        SimpleChat chat = simpleChatService.findById(chatId);
        simpleChatService.initializeSimpleChat(principal, chat);
        userService.addMessage(chat, messageWrapper);
        attributes.addAttribute("chatId", chat.getId());
        return "redirect:/simple-chat-{chatId}";
    }

    private User getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userService.findBySSO(userName);
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

}
