package com.example.digitaluic;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class icMode_drawer extends Fragment{

    //VERSION CONTROL
    public String CURRENT_VERSION = "1.22"; //update this in updateNotes.java as well
    //VERSION CONTROL

    public icMode_drawer() {
        // Required empty public constructor
    }

    SearchView mySearchView;
    ListView myListView;

    ArrayList<Spanned> list;
    ArrayAdapter<Spanned> adapter;
    private FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmenent
        return inflater.inflate(R.layout.fragment_ic_mode_drawer, container, false);


    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        HashMap<String, Object> defaults = new HashMap<>();
        defaults.put("APP_VERSION", 1.0);
        final Task<Void> fetch = remoteConfig.fetch(0);

        fetch.addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                remoteConfig.fetchAndActivate();

//                if(remoteConfig.getString("APP_VERSION") != null) {
//
//
//                    if (Float.parseFloat(remoteConfig.getString("APP_VERSION")) > Float.parseFloat(CURRENT_VERSION)) {
//
//                        UpdateDialog updateDialog = new UpdateDialog();
//                        updateDialog.show(getFragmentManager(), "update dialog");
//
//                    } else {
//                        //for debugging
//                    }
//                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 5000 ms
                        if(Float.parseFloat(remoteConfig.getString("APP_VERSION")) > Float.parseFloat(CURRENT_VERSION)){
                            UpdateDialog updateDialog = new UpdateDialog();
                            updateDialog.show(getFragmentManager(), "update dialog");
                        }
                    }
                }, 5000);


            }
        });


        myListView = getView().findViewById(R.id.myList);
        mySearchView = getView().findViewById(R.id.searchView);

        //for changing text color of search view
        int id = mySearchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = mySearchView.findViewById(id);
        textView.setTextColor(Color.WHITE);
        textView.setHintTextColor(Color.parseColor("#b5b5b5"));


        //for changing divider color of searchView



        myListView.setDivider(null);
        list = new ArrayList<>();

        list.add(Html.fromHtml("<b><font color=\"white\">" + " IC 7400" + "</b></font>" + "<br>- NAND GATE (Quad-Dual input)"));
        list.add(Html.fromHtml("<b><font color=\"white\">" + " IC 7402" + "</b></font>" + "<br>- NOR GATE (Quad-Dual input)"));
        list.add(Html.fromHtml("<b><font color=\"white\">" + " IC 7404" + "</b></font>" + "<br>- NOT GATE (Hex Inverter)"));
        list.add(Html.fromHtml("<b><font color=\"white\">" + " IC 7408" + "</b></font>" + "<br>- AND GATE(Quad-Dual input)"));
        list.add(Html.fromHtml("<b><font color=\"white\">" + " IC 7432" + "</b></font>" + "<br>- OR GATE(Quad-Dual input)"));
        list.add(Html.fromHtml("<b><font color=\"white\">" + " IC 7486" + "</b></font>" + "<br>- XOR GATE (Quad-Dual input)"));


        adapter  = new ArrayAdapter(view.getContext(), R.layout.listview_1, list);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String temp = parent.getItemAtPosition(position).toString().substring(3,7).trim();

                if(temp.equals("7421")){
                    Intent intent = new Intent(getActivity(), AndGate.class);
                    startActivity(intent);
                }else if(temp.equals("7400")){
                    Intent intent = new Intent(getActivity(), NandGate.class);
                    startActivity(intent);
                }else if(temp.equals("7402")){
                    Intent intent = new Intent(getActivity(), NorGate.class);
                    startActivity(intent);
                }else if(temp.equals("7404")){
                    Intent intent = new Intent(getActivity(), NotGate.class);
                    startActivity(intent);
                }else if(temp.equals("7408")){
                    Intent intent = new Intent(getActivity(), AndGate2.class);
                    startActivity(intent);
                }else if(temp.equals("7432")){
                    Intent intent = new Intent(getActivity(), OrGate.class);
                    startActivity(intent);
                }else if(temp.equals("7486")){
                    Intent intent = new Intent(getActivity(), XorGate.class);
                    startActivity(intent);
                }
            }
        });
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // listening upon completion
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //continuous listening
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
