/*
 * AA 2017-2018
 * Introduction to Web Programming
 * Lab 12 - WebServices
 * UniTN
 */
package it.unitn.aa1718.webprogramming.lab12.exercise1.entities;

/**
 * Class that describes a shop entity.
 * 
 * @author Stefano Chirico &lt;s dot chirico at unitn dot it&gt;
 * @since 1.0.180517
 * @version 1.0.180517
 */
public class Shop {
    private String name;
    private String web;
    private String street;
    private String streetNumber;
    private String zipCode;
    private String city;
    private String province;
    private Double lng;
    private Double lat;

    /**
     * Returns the name of the shop.
     * 
     * @return the name of the shop
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the new name of the shop.
     * 
     * @param name the name to set
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the web street of the shop official site.
     * @return the web street of the shop official site
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public String getWeb() {
        return web;
    }

    /**
     * Sets the new web street of the shop official site.
     * @param web the web street to set
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public void setWeb(String web) {
        this.web = web;
    }

    /**
     * Returns the street of the shop.
     * 
     * @return the street of the shop
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the new street of the shop.
     * 
     * @param street the street to set
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Returns the street number of the shop.
     * 
     * @return the streetNumber
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Sets the new street number of the shop.
     * 
     * @param streetNumber the streetNumber to set
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * Returns the zip code of the shop.
     * 
     * @return the zipCode of the shop
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the new zip code of the shop.
     * 
     * @param zipCode the zipCode to set
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Returns the city of the shop.
     * 
     * @return the city
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the new city of the shop.
     * 
     * @param city the city to set
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the province of the shop.
     * 
     * @return the province
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the new province of the shop.
     * 
     * @param province the province to set
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Returns the longitude coordinate of the shop.
     * 
     * @return the lng coordinate of the shop
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public Double getLng() {
        return lng;
    }

    /**
     * Sets the new longitude coordinate of the shop.
     * 
     * @param lng the lng to set
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * Returns the latitude coordinate of the shop.
     * 
     * @return the lat coordinate of the shop
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public Double getLat() {
        return lat;
    }

    /**
     * Sets the new latitude coordinate of the shop.
     * 
     * @param lat the lat to set
     * 
     * @author Stefano Chirico
     * @since 1.0.180517
     * @version 1.0.180517
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }
}
