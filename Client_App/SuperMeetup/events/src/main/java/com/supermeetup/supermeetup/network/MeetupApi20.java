package com.supermeetup.supermeetup.network;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.builder.api.OAuth2SignatureType;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.oauth.OAuth20Service;

public class MeetupApi20 extends DefaultApi20 {

    public static final String ACCESS_TOKEN_ENDPOINT = "https://secure.meetup.com/oauth2/access";

    protected MeetupApi20() {
    }

    private static class InstanceHolder {
        private static final MeetupApi20 INSTANCE = new MeetupApi20();
    }

    public static MeetupApi20 instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_ENDPOINT;
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://secure.meetup.com/oauth2/authorize";
    }

    @Override
    public OAuth20Service createService(OAuthConfig config) {
        return new MeetupApi20ServiceImpl(this, config);
    }

    @Override
    public OAuth2SignatureType getSignatureType() {
        return OAuth2SignatureType.BEARER_URI_QUERY_PARAMETER;
    }
}
