package com.springfrog.utils;

public enum MediaContentMnemonic {
    PHOTO("PHOTO"),
    FILE("FILE"),
    UNSUPPORTED("UNSUPPORTED");

    private String mnemonic;

    MediaContentMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getMnemonic() {
        return mnemonic;
    }
}
