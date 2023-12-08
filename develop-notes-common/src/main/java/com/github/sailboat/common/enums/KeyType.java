package com.github.sailboat.common.enums;

public enum KeyType {
    RSA("RSA"),
    PSS("RSASSA-PSS");

    private final String algo;

    KeyType(String keyAlgo) {
        this.algo = keyAlgo;
    }

    public String keyAlgo() {
        return algo;
    }
}
