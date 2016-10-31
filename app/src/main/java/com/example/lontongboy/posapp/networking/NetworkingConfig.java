package com.example.lontongboy.posapp.networking;

/**
 * This <PosappSP> project in package <com.example.lontongboy.posapp> created by :
 * Name         : syafiq
 * Date / Time  : 30 October 2016, 8:41 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class NetworkingConfig
{
    public  static final String BASE_URL = "http://10.42.0.1/";
    public  static final String SITE_URL = "http://10.42.0.1/index.php/";

    public static String BASE_URL(String path)
    {
        return BASE_URL+path;
    }

    public static String SITE_URL(String path)
    {
        return SITE_URL+path;
    }
}
