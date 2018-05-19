/*
 * AA 2017-2018
 * Introduction to Web Programming
 * Lab 12 - WebServices
 * UniTN
 */
package it.unitn.aa1718.webprogramming.lab12.exercise1.maps;

/**
 *
 * @author Stefano Chirico &lt;s dot chirico at unitn dot it&gt;
 * @since 1.0.180517
 * @version 1.0.180517
 */
public interface InfoMapConstants {
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String WEB = "web";
    public static final String STREET = "street";
    public static final String STREET_NUMBER = "streetNumber";
    public static final String ZIP_CODE = "zipCode";
    public static final String CITY = "city";
    public static final String PROVINCE = "province";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String ROW = "row";
    //--
    public static final int CIRCLE_MAP_ID = 0;
    public static final int CLUSTER_MAP_ID = 1;
    public static final int ICON_MAP_ID = 2;
    public static final int CLUSTER_ICON_MAP_ID = 3;
    public static final int SINGLE_PIN_MAP_ID = 4;
    
    public static final int HYBRID_ID = 0;
    public static final int ROADMAP_ID = 1;
    public static final String HYBRID_NAME = "HYBRID";
    public static final String ROADMAP_NAME = "ROADMAP";
}
