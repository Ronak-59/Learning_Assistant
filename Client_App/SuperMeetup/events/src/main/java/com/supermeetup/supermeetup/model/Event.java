package com.supermeetup.supermeetup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * GET https://api.meetup.com/find/events
 */

public class Event {

    @SerializedName("created")
    @Expose
    private long created;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("time")
    @Expose
    private long time;
    @SerializedName("rsvp_close_offset")
    @Expose
    private String rsvpCloseOffset;
    @SerializedName("updated")
    @Expose
    private long updated;
    @SerializedName("utc_offset")
    @Expose
    private long utcOffset;
    @SerializedName("waitlist_count")
    @Expose
    private int waitlistCount;
    @SerializedName("yes_rsvp_count")
    @Expose
    private int yesRsvpCount;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("event_host")
    @Expose
    private EventHost eventHost;
    @SerializedName("fee")
    @Expose
    private Fee fee;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("how_to_find_us")
    @Expose
    private String howToFindUs;
    @SerializedName("visibility")
    @Expose
    private String visibility;

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getRsvpCloseOffset() {
        return rsvpCloseOffset;
    }

    public void setRsvpCloseOffset(String rsvpCloseOffset) {
        this.rsvpCloseOffset = rsvpCloseOffset;
    }

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public long getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(long utcOffset) {
        this.utcOffset = utcOffset;
    }

    public int getWaitlistCount() {
        return waitlistCount;
    }

    public void setWaitlistCount(int waitlistCount) {
        this.waitlistCount = waitlistCount;
    }

    public int getYesRsvpCount() {
        return yesRsvpCount;
    }

    public void setYesRsvpCount(int yesRsvpCount) {
        this.yesRsvpCount = yesRsvpCount;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public EventHost getEventHost() {
        return eventHost;
    }

    public void setEventHost(EventHost eventHost) {
        this.eventHost = eventHost;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHowToFindUs() {
        return howToFindUs;
    }

    public void setHowToFindUs(String howToFindUs) {
        this.howToFindUs = howToFindUs;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

}
