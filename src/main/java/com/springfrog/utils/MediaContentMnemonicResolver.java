package com.springfrog.utils;

import com.springfrog.dto.Document;
import com.springfrog.dto.MediaContent;

public class MediaContentMnemonicResolver {

    public static MediaContentMnemonic resolveMnemonic(MediaContent mediaContent) {
        if (mediaContent instanceof Document) {
            Document document = (Document) mediaContent;
            String path = document.getPath();
            if (path.matches("\\.(jpg|bmp|png|gif|jpeg)$")) {
                return MediaContentMnemonic.PHOTO;
            } else {
                return MediaContentMnemonic.FILE;
            }
        }
        return MediaContentMnemonic.UNSUPPORTED;
    }
}
