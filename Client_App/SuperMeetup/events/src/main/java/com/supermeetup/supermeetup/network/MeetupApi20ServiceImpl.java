package com.supermeetup.supermeetup.network;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth20Service;

public class MeetupApi20ServiceImpl extends OAuth20Service {

    public MeetupApi20ServiceImpl(DefaultApi20 api, OAuthConfig config) {
        super(api, config);
    }

    @Override
    public void signRequest(OAuth2AccessToken accessToken, OAuthRequest request) {
        request.addQuerystringParameter("oauth2_access_token", accessToken.getAccessToken());
    }
}
