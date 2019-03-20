package com.supermeetup.supermeetup.model;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yuxin on 10/13/17.
 * Sample: https://secure.meetup.com/meetup_api/console/?path=/find/venues
 * "venue": {
 * "id": 1869971,
 * "name": "Studio 302",
 * "lat": 37.748023986816406,
 * "lon": -122.41981506347656,
 * "repinned": false,
 * "address_1": "3435 Cesar Chavez ",
 * "city": "San Francisco",
 * "country": "us",
 * "localized_country_name": "USA",
 * "zip": "94110",
 * "state": "CA"
 * }
 */

public class Venue {

    public static final String VISIBILITYTYPE_PUBLIC = "public";
    public static final String VISIBILITYTYPE_PRIVATE = "private";

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lon")
    @Expose
    private double lon;
    @SerializedName("repinned")
    @Expose
    private boolean repinned;
    @SerializedName("address_1")
    @Expose
    private String address1;
    @SerializedName("address_2")
    @Expose
    private String address2;
    @SerializedName("address_3")
    @Expose
    private String address3;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("zpi")
    @Expose
    private String zip;
    @SerializedName("localized_country_name")
    @Expose
    private String localizedCountryName;
    @SerializedName("visibility")
    @Expose
    private String visibility;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isRepinned() {
        return repinned;
    }

    public void setRepinned(boolean repinned) {
        this.repinned = repinned;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLocalizedCountryName() {
        return localizedCountryName;
    }

    public void setLocalizedCountryName(String localizedCountryName) {
        this.localizedCountryName = localizedCountryName;
    }

    public boolean isVisible(){
        return !VISIBILITYTYPE_PRIVATE.equals(visibility);
    }

    public String getFullAddress(){
        StringBuilder sb = new StringBuilder(address1);
        if(!TextUtils.isEmpty(address2)){
            sb.append(" ");
            sb.append(address2);
        }
        if(!TextUtils.isEmpty(address3)){
            sb.append(" ");
            sb.append(address3);
        }
        if(!TextUtils.isEmpty(city)){
            sb.append(", ");
            sb.append(city);
        }
        if(!TextUtils.isEmpty(city)){
            sb.append(", ");
            sb.append(city);
        }
        if(!TextUtils.isEmpty(state)){
            sb.append(", ");
            sb.append(state);
        }
        if(!TextUtils.isEmpty(zip)){
            sb.append(" ");
            sb.append(zip);
        }
        if(!TextUtils.isEmpty(localizedCountryName)){
            sb.append(", ");
            sb.append(localizedCountryName);
        }

        return sb.toString();
    }

}
