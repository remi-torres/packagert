package com.example.packagert.util;

import java.util.Locale;

public class StringUtil {

    public static String capitalize(final String value){
        return value.substring(0, 1).toUpperCase(Locale.ROOT) + value.substring(1);
    }
}
