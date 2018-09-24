package com.ehouse.springmvc.utils;

import java.util.UUID;

public class UUIDutil {

    public static String getuuid() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }

    public static String getuuid(String inputStr) {
        UUID uuid = UUID.nameUUIDFromBytes(inputStr.getBytes());
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }

}
