package com.android.learningassistant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.clover_studio.spikachatmodule.ChatActivity;
import com.clover_studio.spikachatmodule.models.Config;
import com.clover_studio.spikachatmodule.models.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Forum.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Forum#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Forum extends Fragment {

    List<String> spinnerArray =  new ArrayList<String>();
    Button enterRoom;
    private String roomId;
    private FirebaseAuth auth;



    private OnFragmentInteractionListener mListener;

    public Forum() {
        // Required empty public constructor
    }


    public static Forum newInstance(String param1, String param2) {
        Forum fragment = new Forum();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_forum, container, false);
        auth = FirebaseAuth.getInstance();
        spinnerArray.add("Computer Department");
        spinnerArray.add("IT Department");
        spinnerArray.add("EXTC Department");
        spinnerArray.add("Mechanical Department");
        spinnerArray.add("Production Department");
        spinnerArray.add("Chemical Department");
        spinnerArray.add("Bio-medical Department");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       final Spinner sItems = (Spinner) view.findViewById(R.id.spinner);
        sItems.setAdapter(adapter);

        enterRoom = (Button)view.findViewById(R.id.joinRoom);
        enterRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String selected = sItems.getSelectedItem().toString();
                    if (selected.equals("Computer Department")) {
                       roomId = "101";
                    }
                    else if (selected.equals("IT Department")) {
                        roomId = "102";
                    }
                    else if (selected.equals("EXTC Department")) {
                        roomId = "103";
                    }
                    else if (selected.equals("Mechanical Department")) {
                        roomId = "104";
                    }
                    else if (selected.equals("Production Department")) {
                        roomId = "105";
                    }
                    else if (selected.equals("Chemical Department")) {
                        roomId = "106";
                    }
                    else if (selected.equals("Bio-medical Department")) {
                        roomId = "107";
                    }
                    User user = new User();
                    user.roomID = roomId;
                    user.userID = auth.getCurrentUser().getDisplayName().toString();
                    user.name = auth.getCurrentUser().getDisplayName().toString();
                    user.avatarURL = auth.getCurrentUser().getPhotoUrl().toString();
                    Config config = new Config();
                    config.apiBaseUrl = "http://10.120.63.145:80/spika/v1/";
                    config.socketUrl = "http://10.120.63.145:80/spika";

                    ChatActivity.startChatActivityWithConfig(getContext(), user, config);

            }
        });

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
