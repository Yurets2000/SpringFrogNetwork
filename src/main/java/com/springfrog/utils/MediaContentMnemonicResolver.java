package com.springfrog.utils;

import com.springfrog.dto.Document;
import com.springfrog.dto.MediaContent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MediaContentMnemonicResolver {

    public static MediaContentMnemonic resolveMnemonic(MediaContent mediaContent) {
        if (mediaContent instanceof Document) {
            Document document = (Document) mediaContent;
            String path = document.getPath();
            Pattern pattern = Pattern.compile("\\.(jpg|bmp|png|gif|jpeg)$");
            Matcher matcher = pattern.matcher(path);
            if (matcher.find()) {
                return MediaContentMnemonic.PHOTO;
            } else {
                return MediaContentMnemonic.FILE;
            }
        }
        return MediaContentMnemonic.UNSUPPORTED;
    }
}
