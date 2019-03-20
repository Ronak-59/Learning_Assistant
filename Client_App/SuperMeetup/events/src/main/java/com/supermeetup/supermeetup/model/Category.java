package com.supermeetup.supermeetup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuxin on 10/13/17.
 * Sample: https://secure.meetup.com/meetup_api/console/?path=/find/topic_categories
 * "0": {
 *      "id": 242,
 *      "shortname": "outdoors-adventure",
 *      "name": "Outdoors & Adventure",
 *      "sort_name": "Outdoors & Adventure",
 *      "photo": {
 *          "id": 450131943,
 *          "highres_link": "https://secure.meetupstatic.com/photos/event/2/e/a/7/highres_450131943.jpeg",
 *          "photo_link": "https://secure.meetupstatic.com/photos/event/2/e/a/7/600_450131943.jpeg",
 *          "thumb_link": "https://secure.meetupstatic.com/photos/event/2/e/a/7/thumb_450131943.jpeg",
 *          "type": "event",
 *          "base_url": "https://secure.meetupstatic.com"
 *          },
 *      "category_ids": [
 *              3,
 *              23
 *          ]
 *  }
 */

public class Category {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("shortname")
    @Expose
    private String shortname;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sort_name")
    @Expose
    private String sortName;
    @SerializedName("photo")
    @Expose
    private Photo photo;
    @SerializedName("category_ids")
    @Expose
    private List<Long> categoryIds = null;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
