/*
 * AA 2017-2018
 * Introduction to Web Programming
 * Lab 12 - WebServices
 * UniTN
 */
package it.unitn.aa1718.webprogramming.lab12.exercise1.services;

import com.alibaba.fastjson.JSONObject;
import it.unitn.aa1718.webprogramming.lab12.exercise1.entities.Shop;
import it.unitn.aa1718.webprogramming.lab12.exercise1.maps.ClusterMap;
import it.unitn.aa1718.webprogramming.lab12.exercise1.maps.GoogleMap;
import it.unitn.aa1718.webprogramming.lab12.exercise1.maps.InfoMapConstants;
import it.unitn.aa1718.webprogramming.lab12.exercise1.maps.SinglePinMap;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * The web-service generates Google Map(s) for the online shopping system.
 * 
 * @author Stefano Chirico &lt;s dot chirico at unitn dot it&gt;
 * @since 1.0.180517
 * @version 1.0.180517
 */
@WebService(serviceName = "MapGroupGenerator")
public class MapGroupGenerator implements InfoMapConstants {
    private double[] swPoint, nePoint;
    
    /**
     * Returns the GPS coordinates of the medium point of all the GPS
     * coordinates of the input shops. 
     *
     * @param shops the list of shop used to calculate the medium point.
     * @return the GPS coordinates of the medium point.
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    private void computeGpsCoord(List<Shop> shops) {

        double minLat = Double.POSITIVE_INFINITY;
        double maxLat = Double.NEGATIVE_INFINITY;
        double minLng = Double.POSITIVE_INFINITY;
        double maxLng = Double.NEGATIVE_INFINITY;

        for (Shop shop : shops) {
            if (shop.getLat()< minLat) {
                minLat = shop.getLat();
            }
            if (shop.getLat() > maxLat) {
                maxLat = shop.getLat();
            }
            if (shop.getLng() < minLng) {
                minLng = shop.getLng();
            }
            if (shop.getLng() > maxLng) {
                maxLng = shop.getLng();
            }
        }

        swPoint = new double[]{minLat, minLng};
        nePoint = new double[]{maxLat, maxLng};
    }
    
    /**
     * Generates the list (JSON format) of all the shops to view on the map.<br>
     * <b>Note</b>: if a shop has more than an id_row, then will be visualized
     * one or more instances of pins or circles.
     *
     * @param shopList the list of shops to map
     * @return the json string of the list
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    private String createListJSON(List<Shop> shopList) {
        List<LinkedHashMap<String, String>> shopsLList = new LinkedList<>();
        LinkedHashMap<String, String> shop;
        for (Shop s : shopList) {
            shop = new LinkedHashMap<>();
            shop.put(NAME, s.getName());
            shop.put(WEB, s.getWeb());
            shop.put(STREET, s.getStreet());
            shop.put(STREET_NUMBER, s.getStreetNumber());
            shop.put(ZIP_CODE, s.getZipCode());
            shop.put(CITY, s.getCity());
            shop.put(PROVINCE, s.getProvince());
            shop.put(LNG, s.getLng()+ "");
            shop.put(LAT, s.getLat()+ "");

            shopsLList.add(shop);
        }

        return JSONObject.toJSONString(shopsLList);
    }
    
    /**
     * Returns the String &quot;yyyMM&quot; used to get the folder where save
     * the generated map files.
     * 
     * @return the folder name used to save the generated map files.
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    private String getThisYearMonthFolder() {
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMM");
        return ft.format(now);
    }
    
    /**
     * Returns the path of the file created starting from the folderPath and
     * the htmltext passed as parameters.
     * @param folderPath the folder path
     * @param htmlText the content of the html file
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    private String writeFile(File folderPath, StringBuilder htmlText) {
        try {
            folderPath = new File(folderPath, getThisYearMonthFolder());
            folderPath.mkdirs();

            File fileMap = new File(folderPath, System.currentTimeMillis() + "_Ex1-GMap.html");
            try (Writer writer = new BufferedWriter(new FileWriter(fileMap))) {
                writer.write(htmlText.toString());
                writer.flush();
            }

            return fileMap.getCanonicalPath();
        } catch (IOException ex) {
            //TODO: Log the exception
        }

        return null;
    }
    

    /**
     * This SOAP web-service returns the public URL used to map, via Google
     * Maps,all the shops near the one passed as parameter and in an area
     * described by center(Lat/Lng), sw(Lat/Lng), ne(Lat/Lng).
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     * 
     * @param userId
     * @param shopIds
     * @param aggregationTypeId
     * @param mapTypeId
     * @param centerLat
     * @param centerLng
     * @param swLat
     * @param swLng
     * @param neLat
     * @param neLng
     * @param divWidth
     * @param divHeight
     * @param zoom
     * @return 
     */
    @WebMethod(operationName = "getPublicURLMapNeighbors")
    public String getPublicURLMapNeighbors(
            @WebParam(name = "userId") Integer userId,
            @WebParam(name = "shopIds") List<Integer> shopIds,
            @WebParam(name = "aggregationTypeId") Integer aggregationTypeId,
            @WebParam(name = "mapTypeId") Integer mapTypeId,
            @WebParam(name = "centerLat") Double centerLat,
            @WebParam(name = "centerLng") Double centerLng,
            @WebParam(name = "swLat") Double swLat,
            @WebParam(name = "swLng") Double swLng,
            @WebParam(name = "neLat") Double neLat,
            @WebParam(name = "neLng") Double neLng,
            @WebParam(name = "width") Integer divWidth,
            @WebParam(name = "height") Integer divHeight,
            @WebParam(name = "zoom") Integer zoom
    ) {
        List<Shop> shops = new ArrayList<>();
        
        String title = "Google Map: Shops neighbors";
        
        String resources;
        GoogleMap googleMap;
        if (aggregationTypeId == SINGLE_PIN_MAP_ID) {
            title += " - Single Pin Map";
            googleMap = new SinglePinMap();
        } else {
            title += " - Cluster Map";
            googleMap = new ClusterMap();
        }
        resources = createListJSON(shops);
        
        googleMap.setResources(resources);
        googleMap.setMapOptions(title, mapTypeId, zoom);

        if ((swLat != null) && (swLng != null) && (neLat != null) && (neLng != null)) {
            swPoint = new double[]{swLat, swLng};
            nePoint = new double[]{neLat, neLng};
        } else {
            computeGpsCoord(shops);
        }
        googleMap.setCenterPoint(new double[]{centerLat, centerLng});
        googleMap.setLatLngBounds(swPoint, nePoint);
        googleMap.setDivDimension(divWidth, divHeight);

        StringBuilder testoHtml = googleMap.generateHtml();
        String mapFilePath;
        mapFilePath = writeFile(new File(Config.getMapsPath(), userId.toString()), testoHtml);

        //generate a public url
        String mapPublicUrl = Config.getMapPublicUrl() + File.separator
                + userId + File.separator
                + getThisYearMonthFolder() + File.separator
                + new File(mapFilePath).getName();

        return mapPublicUrl;
    }
    
    @WebMethod(operationName = "getShopIdsInQuadrant")
    public List<Integer> getShopIdsInQuadrant(
            @WebParam(name = "topLat") Double topLat,
            @WebParam(name = "topLng") Double topLng,
            @WebParam(name = "bottomLat") Double bottomLat,
            @WebParam(name = "bottomLng") Double bottomLng
    ) {
        if ((topLat == null) || (topLng == null) || (bottomLat == null) || (bottomLng == null)) {
            return new ArrayList<>();
        }

        return new ArrayList<>();
    }
    
    @WebMethod(operationName = "getBounds")
    public List<Double> getBounds(
            @WebParam(name = "centerLat") Double centerLat,
            @WebParam(name = "centerLng") Double centerLng,
            @WebParam(name = "distance") Double distance
    ) {
        if ((centerLat == null) || (centerLng == null)) {
            return new ArrayList<>();
        }
        if (distance <= 0) {
            distance = 10.0;
        }

        Double ddistance = Math.sqrt(2 * Math.pow(distance, 2));
        ddistance = ddistance / 6371;

        Double topBrng = Math.toRadians(315);
        Double bottomBrng = Math.toRadians(135);

        centerLat = Math.toRadians(centerLat);
        centerLng = Math.toRadians(centerLng);

        Double topLat = Math.asin(
                (Math.sin(centerLat) * Math.cos(ddistance))
                + (Math.cos(centerLat) * Math.sin(ddistance) * Math.cos(topBrng)));
        Double topLng = centerLng + Math.atan2(
                Math.sin(topBrng) * Math.sin(ddistance) * Math.cos(centerLat),
                Math.cos(ddistance) - (Math.sin(centerLat) * Math.sin(topLat)));
        topLng = (topLng + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

        topLat = Math.toDegrees(topLat);
        topLng = Math.toDegrees(topLng);

        Double bottomLat = Math.asin(
                (Math.sin(centerLat) * Math.cos(ddistance))
                + (Math.cos(centerLat) * Math.sin(ddistance) * Math.cos(bottomBrng)));
        Double bottomLng = centerLng + Math.atan2(
                Math.sin(bottomBrng) * Math.sin(ddistance) * Math.cos(centerLat),
                Math.cos(ddistance) - (Math.sin(centerLat) * Math.sin(bottomLat)));
        bottomLng = (bottomLng + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

        bottomLat = Math.toDegrees(bottomLat);
        bottomLng = Math.toDegrees(bottomLng);

        if (bottomLat < topLat) {
            Double temp = bottomLat;
            bottomLat = topLat;
            topLat = temp;
        }
        if (bottomLng < topLng) {
            Double temp = bottomLng;
            bottomLng = topLng;
            topLng = temp;
        }

        ArrayList<Double> res = new ArrayList<>();
        res.add(topLat);    //sw lat
        res.add(bottomLng); //sw lng
        res.add(bottomLat); //ne lat
        res.add(topLng);    //ne lng

        return res;
    }
    
    @WebMethod(operationName = "getLatLng")
    public Object[] getLatLng(@WebParam(name = "shopId") Integer shopId) {
        if (shopId == null) {
            return new Object[0];
        }

        return new Object[] { 46.0167117, 11.2922441 };
    }
}
