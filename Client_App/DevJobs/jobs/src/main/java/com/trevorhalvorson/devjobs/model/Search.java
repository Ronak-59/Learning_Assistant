package com.trevorhalvorson.devjobs.model;

import java.io.Serializable;

/**
 * Created by Trevor Halvorson on 11/21/2015.
 */
public class Search implements Serializable {
    private String mDescription;
    private String mLocation;

    public Search(String description, String location) {
        mDescription = description;
        mLocation = location;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    @Override
    public String toString() {
        if (getLocation() != null) {
            return getDescription() + " in " + getLocation();
        }
        return getDescription();
    }
}
