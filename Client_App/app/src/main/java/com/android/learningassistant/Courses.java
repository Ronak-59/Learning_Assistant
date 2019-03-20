package com.android.learningassistant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.developers.algoexplorer.*;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Courses.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Courses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Courses extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CardView cardview1;
    CardView cardview2;
    CardView cardview3;
    CardView cardview4;
    CardView cardview5;
    CardView cardview6;
     VideoView videoPlay;
    private OnFragmentInteractionListener mListener;

    public Courses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Courses.
     */
    // TODO: Rename and change types and number of parameters
    public static Courses newInstance(String param1, String param2) {
        Courses fragment = new Courses();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(getContext(), com.developers.algoexplorer.MainActivity.class));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_courses, container, false);
     /*   videoPlay = (VideoView)view.findViewById(R.id.video_play);

        cardview1=(CardView)view.findViewById(R.id.c_android);
        cardview1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                videoPlay.setVideoPath("http://r1---sn-gvnuxaxjvh-gv8e.googlevideo.com/videoplayback?initcwndbps=1983750&ip=80.254.104.117&beids=%5B9452306%5D&mime=video%2Fmp4&ms=au&mt=1485617421&sparams=dur%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Cratebypass%2Csource%2Cupn%2Cexpire&mv=m&id=o-AE_HdVlrEnmxMl1dC-KpUyvtTnZqXLqDbINDzhhKMWHB&pl=19&mm=31&mn=sn-gvnuxaxjvh-gv8e&signature=AF7EF05DBE98C143C54382660FF024BBDE3114AF.57C0B1F4FFE23C2DECF8B91C135D8FAFC2310307&lmt=1472114561390169&itag=22&upn=Xau3-VtdmoU&source=youtube&ipbits=0&dur=589.276&pcm2cms=yes&ratebypass=yes&key=yt6&expire=1485639095&title=Android+App+Development+for+Beginners+-+1+-+Introduction");
                videoPlay.setVisibility(View.VISIBLE);
                videoPlay.start();
            }
        });
        cardview2=(CardView)view.findViewById(R.id.c_web);
        cardview2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                videoPlay.setVideoPath("https://r2---sn-gwpa-a3ve.googlevideo.com/videoplayback?id=o-ANg4G6itiXa2DViUx1xhdEEUFFz6SbzADuVUhJD_5M4g&gir=yes&mm=31&mn=sn-gwpa-a3ve&initcwndbps=246250&source=youtube&signature=C2C25F0351AFA4BE51E530C9CDA643435A715EAD.63917EE7B4C3C2B323A7A0A5330E0CEA7900CDC4&ms=au&mt=1485625827&mv=m&clen=6472787&pl=17&ei=-9mMWNKKIcncoAOzn5qADw&ipbits=0&upn=d3YcGk6qHAw&requiressl=yes&itag=18&ratebypass=yes&dur=256.603&lmt=1407549372864160&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&mime=video%2Fmp4&key=yt6&ip=47.8.208.183&expire=1485647451&title=HTML5+Tutorial+-+1+-+Introduction");
                videoPlay.setVisibility(View.VISIBLE);
                videoPlay.start();
            }
        });
        cardview3=(CardView)view.findViewById(R.id.c_java);
        cardview3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                videoPlay.setVideoPath("http://r2---sn-bg07dnll.googlevideo.com/videoplayback?ms=au&pl=23&mv=m&initcwndbps=473750&ipbits=0&source=youtube&dur=440.923&nh=IgpwcjAzLmdydTA2KgkxMjcuMC4wLjE&ip=200.152.70.190&key=yt6&expire=1485641892&mime=video%2Fmp4&id=o-AL-f8x9ooSeHEx_lAc8yUKwtEP6ZuNYWB-SxoHWeAslS&itag=18&mt=1485620122&upn=9UM_FReavj0&mn=sn-bg07dnll&mm=31&ratebypass=yes&sparams=dur%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cnh%2Cpl%2Cratebypass%2Csource%2Cupn%2Cexpire&signature=C47E8D7AD0CD8693AA274AA562245EE149F54D35.560B011D49CEBDE113134DE108FAD7AAD814324F&lmt=1429602328146707&title=Java+Programming+Tutorial+-+1+-+Installing+the+JDK");
                videoPlay.setVisibility(View.VISIBLE);
                videoPlay.start();
            }
        });
        cardview4=(CardView)view.findViewById(R.id.c_cpp);
        cardview4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                videoPlay.setVideoPath("http://r4---sn-ipoxu-un56.googlevideo.com/videoplayback?initcwndbps=2427500&signature=C0262AD2F9AED7E7F89D890B862F58475844A55F.20C729663287CC9A292710D4B37076C559E2252A&ratebypass=yes&source=youtube&dur=473.385&sparams=dur%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Cratebypass%2Csource%2Cupn%2Cexpire&key=yt6&upn=QdZHhxoKzD4&itag=18&lmt=1427901876593795&pl=20&mv=m&mt=1485625827&ms=au&ipbits=0&pcm2cms=yes&id=o-AKQ0RPtleRWSllCSNyeXpgLBRp3dJ-r72mjHcRBXt79Y&expire=1485647508&mime=video%2Fmp4&ip=114.36.52.217&mn=sn-ipoxu-un56&mm=31&title=Buckys+C%2B%2B+Programming+Tutorials+-+1+-+Installing+CodeBlocks");
                videoPlay.setVisibility(View.VISIBLE);
                videoPlay.start();
            }
        });
        cardview5=(CardView)view.findViewById(R.id.c_python);
        cardview5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                videoPlay.setVideoPath("http://r3---sn-a8au-vgqe.googlevideo.com/videoplayback?itag=18&upn=d0EFBC0fm38&sparams=dur%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Cratebypass%2Csource%2Cupn%2Cexpire&expire=1485648597&mime=video%2Fmp4&pcm2cms=yes&ratebypass=yes&initcwndbps=8791250&ipbits=0&source=youtube&dur=198.182&mv=m&lmt=1394285208781978&ms=au&ip=173.234.194.248&pl=20&mt=1485626727&mm=31&mn=sn-a8au-vgqe&id=o-AIg7pVsWLj_eJ0dU0CJr3qq9oQxgac6a6aptw49NEsot&key=yt6&signature=B4D5C9F2E49E3D3BCFC09FBBB74BE62472F656AB.5FE2C8624E88A492BAE3F4FEB2B12C41801B9F0D&title=Python+Programming+Tutorial+-+1+-+Installing+Python");
                videoPlay.setVisibility(View.VISIBLE);
                videoPlay.start();
            }
        });
        cardview6=(CardView)view.findViewById(R.id.c_networking);
        cardview6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                videoPlay.setVideoPath("http://r3---sn-bg0e7n76.googlevideo.com/videoplayback?mime=video%2Fmp4&ip=177.87.10.166&ipbits=0&source=youtube&sparams=dur%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cnh%2Cpl%2Cratebypass%2Csource%2Cupn%2Cexpire&id=o-ADjM2YC8u26fzEmAO5_C65pfYhntdbwgnJaHYTBcT3m9&upn=9vRvcOeC50w&itag=22&expire=1485648744&initcwndbps=597500&ratebypass=yes&mt=1485627026&mm=31&mn=sn-bg0e7n76&dur=382.618&nh=IgpwcjAxLmdydTA2KgkxMjcuMC4wLjE&pl=24&mv=m&ms=au&signature=648509D84ABD381D34916604A12917ACCFA8A309.3F05DDA08FEA4938FA6607BA93A67CE048F8FD&lmt=1472137674307766&key=yt6&title=Computer+Networking+Tutorial+-+1+-+What+is+a+Computer+Network");
                videoPlay.setVisibility(View.VISIBLE);
                videoPlay.start();
            }
        });
        */
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
