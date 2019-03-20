package com.supermeetup.supermeetup.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import com.codepath.oauth.OAuthLoginActionBarActivity;
import com.supermeetup.supermeetup.R;
import com.supermeetup.supermeetup.common.Util;
import com.supermeetup.supermeetup.databinding.ActivityLoginEventsBinding;
import com.supermeetup.supermeetup.network.MeetupClient;

/**
 * A login screen that offers login via meetup oauth.
 */
public class LoginActivity extends OAuthLoginActionBarActivity<MeetupClient> {

    private ActivityLoginEventsBinding binding;
    private boolean isAttempingLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_events);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }


    public void attemptLogin() {
        // Show a progress spinner, and kick off a background task to
        // perform the user login attempt.
        binding.progressbar.setVisibility(View.VISIBLE);
        binding.login.setVisibility(View.GONE);
        isAttempingLogin = true;
        getClient().connect();
    }

    @Override
    public void onResume(){
        super.onResume();
        isAttempingLogin = Util.readBoolean(this, Util.KEY_ATTEMPTINGLOGIN);
        if(isAttempingLogin){
            binding.progressbar.setVisibility(View.VISIBLE);
            binding.login.setVisibility(View.GONE);
        }else{
            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
            alphaAnimation.setDuration(2000);
            alphaAnimation.setFillAfter(true);
            binding.loginlayout.startAnimation(alphaAnimation);
            binding.progressbar.setVisibility(View.GONE);
            binding.login.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause(){
        Util.writeBoolean(this, Util.KEY_ATTEMPTINGLOGIN, isAttempingLogin);
        super.onPause();
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    @Override
    public void onLoginFailure(Exception e) {
        Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_LONG).show();
        binding.progressbar.setVisibility(View.GONE);
        binding.login.setVisibility(View.VISIBLE);
        isAttempingLogin = false;
        Util.writeBoolean(this, Util.KEY_ATTEMPTINGLOGIN, isAttempingLogin);
    }

    @Override
    public void onDestroy(){
        Util.writeBoolean(this, Util.KEY_ATTEMPTINGLOGIN, false);
        super.onDestroy();
    }

}

