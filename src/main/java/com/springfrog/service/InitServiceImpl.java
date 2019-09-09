package com.springfrog.service;

import com.springfrog.dao.UserDao;
import com.springfrog.dao.UserProfileDao;
import com.springfrog.dto.User;
import com.springfrog.dto.UserProfile;
import com.springfrog.dto.UserProfileType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

@Service("initService")
@Transactional
@Order(1)
public class InitServiceImpl implements InitService {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    UserProfileDao userProfileDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void initializeDataSource() {
        for (UserProfileType type : UserProfileType.values()) {
            userProfileDao.save(new UserProfile(type.getUserProfileType()));
        }
        User user1 = new User("yurets2000", "12345",
                "Yura", "Bezlyudnyy", "uraura05052000@gmail.com");
        user1.setUserProfiles(new HashSet<>(
                Arrays.asList(userProfileDao.findByType(UserProfileType.ADMIN.toString()))));
        User user2 = new User("stas2000", "1111",
                "Stas", "Bezpalko", "pandanom@gmail.com");

        userDao.save(user1);
        userDao.save(user2);

        /*
        Query filenameSequence = sessionFactory.getCurrentSession().createSQLQuery(
                "CREATE SEQUENCE FILENAME_SEQUENCE START WITH 1 INCREMENT BY 1 CACHE 100");
        filenameSequence.executeUpdate();
        */
    }
}
