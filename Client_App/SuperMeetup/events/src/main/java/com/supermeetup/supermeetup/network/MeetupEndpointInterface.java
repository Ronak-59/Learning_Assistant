package com.supermeetup.supermeetup.network;

import android.support.annotation.Nullable;

import com.supermeetup.supermeetup.model.Category;
import com.supermeetup.supermeetup.model.Event;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MeetupEndpointInterface {
    /**
     *
     * @param fields A comma-delimited list of optional fields to populate in the response
     * @param lat Approximate target latitude
     * @param lon Approximate target longitude
     * @param radius Radius in miles. May be 0.0-100.0, 'global' or 'smart', a dynamic radius based on the number of active groups in the area. Defaults to member's preferred radius
     * @param text Raw full text search query
     * @return The api call
     */
    @GET("find/events")
    Call<ArrayList<Event>> findEvent(@Nullable @Query("fields") String fields,
                                     @Nullable @Query("lat") Double lat,
                                     @Nullable @Query("lon") Double lon,
                                     @Nullable @Query("radius") Float radius,
                                     @Nullable @Query("text") String text);

    /**
     *
     * @param fields An radius (in miles) to center a request for "best_topics". When not provided, the members default alert radius is used
     * @param lat An optional approximate latitude to center a request for "best_topics". When not provided, lat associated with member is used
     * @param lon An optional approximate longitude to center a request for "best_topics". When not provided, lon associated with member is used
     * @param radius An radius (in miles) to center a request for "best_topics". When not provided, the members default alert radius is used
     * @return The api call
     */
    @GET("/find/topic_categories")
    Call<ArrayList<Category>> findTopicCategories(@Nullable @Query("fields") String fields,
                                                  @Nullable @Query("lat") Double lat,
                                                  @Nullable @Query("lon") Double lon,
                                                  @Nullable @Query("radius") Float radius);

    /**
     *
     * @param fields A comma-delimited list of optional fields to populate in the response
     * @param lat Approximate target latitude
     * @param lon Approximate target longitude
     * @param page A target minimum number of events to return in a single page of results. The number returned is non-deterministic but a best-effort attempt will be made to return at least some. Defaults to 32
     * @param self_groups Boolean indicator of whether or not to include events within your existing Meetup network. This includes groups in locations that may differ from the target location. Defaults to true
     * @param topic_category Numeric topic category identifier for filtering recommendations by a topic category
     * @return The api call
     */
    @GET("/recommended/events")
    Call<ArrayList<Event>> recommendedEvents(@Nullable @Query("fields") String fields,
                                             @Nullable @Query("lat") Double lat,
                                             @Nullable @Query("lon") Double lon,
                                             @Nullable @Query("page") Integer page,
                                             @Nullable @Query("self_groups") Boolean self_groups,
                                             @Nullable @Query("topic_category") Integer topic_category);
}
