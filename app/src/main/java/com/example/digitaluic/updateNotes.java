package com.example.digitaluic;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */

public class updateNotes extends Fragment {

    //VERSION CONTROL
    public String CURRENT_VERSION = "1.22";
    //VERSION CONTROL

    private FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

    public updateNotes() {
        // Required empty public constructor
    }
    public TextView updateNotes1, updateNotes2, updateNotes3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
  //      Log.w("drawer_activity", "HMM from drawer_activity");
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_update_notes, container, false);
        updateNotes1 = RootView.findViewById(R.id.updateNotesXML);
        updateNotes2 = RootView.findViewById(R.id.updateNotesXML2);
        updateNotes3 =  RootView.findViewById(R.id.updateNotesVer);

        return RootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateNotes3.setText("version " + CURRENT_VERSION);     // MOVE THIS OUTSIDE FETCH RN

        final Task<Void> fetch = remoteConfig.fetch(0);

        fetch.addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                remoteConfig.fetchAndActivate();
                updateNotes1.setText(remoteConfig.getString("UPDATE_NOTES_1"));
                updateNotes2.setText(remoteConfig.getString("UPDATE_NOTES_2"));
            }

        });

//        updateNotes1 = RootView().findViewById(R.id.updateNotesXML);
//        updateNotes2 = RootView().findViewById(R.id.updateNotesXML2);
//        updateNotes3 =  RootView().findViewById(R.id.updateNotesVer);

//        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
//        HashMap<String, Object> defaults = new HashMap<>();
//        defaults.put("UPDATE_NOTES_1", "-null");
//        defaults.put("UPDATE_NOTES_2", "-null");
//        //defaults.put("UPDATE_NOTES_3", "-null");
//
//        final Task<Void> fetch = remoteConfig.fetch(0);
//        fetch.addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//
//                updateNotes1.setText(remoteConfig.getString("UPDATE_NOTES_1"));
//                updateNotes2.setText(remoteConfig.getString("UPDATE_NOTES_2"));
//            }
//        });

    }






}
