package com.supermeetup.supermeetup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yuxin on 10/13/17.
 * Sample: https://secure.meetup.com/meetup_api/console/?path=/find/topic_categories
 * "photo": {
 *      "id": 450131943,
 *      "highres_link": "https://secure.meetupstatic.com/photos/event/2/e/a/7/highres_450131943.jpeg",
 *      "photo_link": "https://secure.meetupstatic.com/photos/event/2/e/a/7/600_450131943.jpeg",
 *      "thumb_link": "https://secure.meetupstatic.com/photos/event/2/e/a/7/thumb_450131943.jpeg",
 *      "type": "event",
 *      "base_url": "https://secure.meetupstatic.com"
 *  }
 */

public class Photo {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("highres_link")
    @Expose
    private String highresLink;
    @SerializedName("photo_link")
    @Expose
    private String photoLink;
    @SerializedName("thumb_link")
    @Expose
    private String thumbLink;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("base_url")
    @Expose
    private String baseUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHighresLink() {
        return highresLink;
    }

    public void setHighresLink(String highresLink) {
        this.highresLink = highresLink;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getThumbLink() {
        return thumbLink;
    }

    public void setThumbLink(String thumbLink) {
        this.thumbLink = thumbLink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

}
