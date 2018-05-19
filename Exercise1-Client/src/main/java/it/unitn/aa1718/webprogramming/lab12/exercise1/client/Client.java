/*
 * AA 2017-2018
 * Introduction to Web Programming
 * Lab 12 - WebServices
 * UniTN
 */
package it.unitn.aa1718.webprogramming.lab12.exercise1.client;

import it.unitn.aa1718.webprogramming.lab12.exercise1.services.MapGroupGenerator;
import it.unitn.aa1718.webprogramming.lab12.exercise1.services.MapGroupGenerator_Service;
import java.util.List;

/**
 *
 * @author Stefano Chirico &lt;stefano dot chirico at unitn dot it&gt;
 * @since 1.0.180519
 * @version 1.0.180519
 */
public class Client {
    public static void main(String[] args) {
        MapGroupGenerator service = new MapGroupGenerator_Service().getMapGroupGeneratorPort();
        List<Object> latLng = service.getLatLng(2131943431);
        if (latLng.size() == 2) {
            Double distance = 100.0;
            Double centerLat = (Double) latLng.get(0);
            Double centerLng = (Double) latLng.get(1);

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

            System.out.println("(" + topLat + ", " + topLng + ") - (" + bottomLat + ", " + bottomLng + ")");

            List<Integer> shopIds = service.getShopIdsInQuadrant(topLat, topLng, bottomLat, bottomLng);
            System.out.println("There are " + shopIds.size() + " shops: " + shopIds);
        }
    }
}
