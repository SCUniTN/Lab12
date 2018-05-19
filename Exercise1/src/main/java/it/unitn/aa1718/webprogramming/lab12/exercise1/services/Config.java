/*
 * AA 2017-2018
 * Introduction to Web Programming
 * Lab 12 - WebServices
 * UniTN
 */
package it.unitn.aa1718.webprogramming.lab12.exercise1.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author Stefano Chirico &lt;s dot chirico at unitn dot it&gt;
 * @since 1.0.0.180518
 * @version 1.0.0.180518
 */
public class Config {

    static Properties properties = new Properties();

    public static void initialize(URL u) throws IOException {
        try (InputStream is = u.openStream()) {
            properties.load(is);
        }
    }
    
    public static void initialize(File f) throws IOException {
        try (FileReader fr = new FileReader(f)) {
            properties.load(fr);
        }
    }

    public static void initialize(Properties p) {
        properties = p;
    }

    public static String getMapsPath() {
        return properties.getProperty("maps.path", "");
    }

    public static String getFilesMapPath() {
        return properties.getProperty("files.map.path", "");
    }
    
    public static String getIconsMapPath() {
        return properties.getProperty("icons.map.path", "");
    }
    
    public static String getMapPublicUrl() {
        return properties.getProperty("map.public.url", "");
    }
    
    public static String getXlsPath() {
        return properties.getProperty("xls.path", ".");
    }

    public static String getMoreInfoLink() {
        return properties.getProperty("moreinfo.link");
    }
    
    public static String getShortUrlBase() {
        return properties.getProperty("shorturl.base");
    }
}
