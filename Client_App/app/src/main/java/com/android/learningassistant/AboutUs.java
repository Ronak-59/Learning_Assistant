package com.android.learningassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;



public class AboutUs extends AppCompatActivity {

    LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        AboutView view = AboutBuilder.with(this)
                .setPhoto(R.drawable.developer)
                .setCover(R.mipmap.profile_cover)
                .setName("Ronak Doshi")
                .setLinksColumnsCount(5)
                .setSubTitle("Android Developer")
                .setBrief("I'm warmed of mobile technologies. Ideas maker, curious and nature lover.")
                .setAppIcon(R.drawable.elearning_icon)
                .setAppName(R.string.app_name)
                .addGitHubLink("Ronak-59")
                .addFacebookLink("ronak01doshi")
                .addLinkedInLink("ronak-doshi")
                .addEmailLink("ronak01doshi@gmail.com")
                .addWhatsappLink("Ronak Doshi","+918655384740")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .setActionsColumnsCount(1)
                .addShareAction(R.string.app_name)
                .addFeedbackAction("ronak01doshi@gmail.com")
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();

        addContentView(view, layoutParams);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}