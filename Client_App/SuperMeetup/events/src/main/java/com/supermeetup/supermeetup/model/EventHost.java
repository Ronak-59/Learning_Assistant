package com.supermeetup.supermeetup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yuxin on 10/13/17.
 * Sample:
 *  {
 *      "id": 182465237,
 *      "name": "Ranelle Reyes",
 *      "intro": "Hello! Don't watch too much anime nowadays since I slowly transition to watching a lot more jDramas, kDrama, and asian variety shows, but love a lot of the older anime out there! Come talk to me about TGS 2015, I was there! :P",
 *      "photo": {
 *          "id": 254979559,
 *          "highres_link": "https://secure.meetupstatic.com/photos/member/9/a/8/7/highres_254979559.jpeg",
 *          "photo_link": "https://secure.meetupstatic.com/photos/member/9/a/8/7/member_254979559.jpeg",
 *          "thumb_link": "https://secure.meetupstatic.com/photos/member/9/a/8/7/thumb_254979559.jpeg",
 *          "type": "member",
 *          "base_url": "https://secure.meetupstatic.com"
 *      }
 *  }
 */

public class EventHost {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("intro")
    @Expose
    private String intro;
    @SerializedName("photo")
    @Expose
    private Photo photo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

}
