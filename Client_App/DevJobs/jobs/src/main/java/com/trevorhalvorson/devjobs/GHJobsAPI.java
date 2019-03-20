package com.trevorhalvorson.devjobs;

import com.trevorhalvorson.devjobs.model.Job;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Trevor on 7/15/2015.
 */
public interface GHJobsAPI {

    @GET("/positions.json")
    void getGHJobs(@Query("page") String page,
                          @Query("description") String desc,
                          @Query("location") String loc,
                          Callback<ArrayList<Job>> response);

}
