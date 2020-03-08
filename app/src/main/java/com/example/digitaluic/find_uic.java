package com.example.digitaluic;


import android.app.Notification;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.digitaluic.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.app.Activity.RESULT_OK;
import static java.lang.Compiler.enable;

/**
 * A simple {@link Fragment} subclass.
 */

// BLUETOOTH ON/OFF WORKING PROPERLY
// BLUETOOTH DISCOVERY WORKING PROPERLY

public class find_uic extends Fragment {


    private Button refreshButton;
    private ListView uicList;
    private Switch btSwitch;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    private final static int REQUEST_ENABLE_BT = 1;
    final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mReceiver);;
    }

    public find_uic() {
        // Required empty public constructor
    }


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        //setButtonText("Bluetooth off");
                        btSwitch.setChecked(false);
                        break;
                    case BluetoothAdapter.STATE_ON:
                        //setButtonText("Bluetooth on");
                        btSwitch.setChecked(true);
                        findDevices();
                        break;
                }
            }

            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
              //  Toast.makeText(getActivity(), deviceName, Toast.LENGTH_SHORT).show();
                String deviceHardwareAddress = device.getAddress(); // MAC address
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_uic, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btSwitch = getView().findViewById(R.id.BTswitch);
        refreshButton = getView().findViewById(R.id.refreshButton);

        btSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btSwitch.isChecked()){
                    Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBT, REQUEST_ENABLE_BT);
                    findDevices();
                }else{
                    bluetoothAdapter.disable();
                }
            }
        });

        if(bluetoothAdapter == null){
            Toast.makeText(getActivity(), "Device doesn't support bluetooth", Toast.LENGTH_SHORT).show();
        }else{
            //device supports bluetooth
            if(!bluetoothAdapter.isEnabled()){
                //bluetooth is off
                btSwitch.setChecked(false);
                Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBT, REQUEST_ENABLE_BT);
               // btSwitch.setChecked(true);

            }else{
                btSwitch.setChecked(true);
                findDevices();
            }

//            refreshButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(bluetoothAdapter.isEnabled()) {
//                        Toast.makeText(getActivity(), "PRESSED REFRESH", Toast.LENGTH_SHORT).show();
//                        findDevices();
//                    }
//                }
//            });

            IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            getActivity().registerReceiver(mReceiver, filter);

        }



//

//        uicList = getView().findViewById(R.id.uicList);
//        btSwitch = getView().findViewById(R.id.BTswitch);
//
//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//
//        arrayList = new ArrayList<>();
//        adapter = new ArrayAdapter<>(getActivity(), R.layout.listview_1, arrayList);
//        uicList.setAdapter(adapter);
//
//        btSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(btSwitch.isChecked()) {
//                    enable();
//                }
//                else{
//           bluetoothAdapter.disable();
//            arrayList.clear();
//            adapter.notifyDataSetChanged();
//                }
//            }
//        });
//
//        if(btSwitch.isChecked()) {
//            enable();
//        }
//        else{
//            bluetoothAdapter.disable();
//            arrayList.clear();
//            adapter.notifyDataSetChanged();
//        }
//
//        if(adapter.getCount() == 0){
//            arrayList.add("No devices found, try refreshing again!");
//            adapter.notifyDataSetChanged();
//        }
//
//
//        refreshButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                arrayList.clear();
//                adapter.notifyDataSetChanged();
//                findDevices();
//            }
//        });
    }



//    // Create a BroadcastReceiver for ACTION_FOUND.
//    private final BroadcastReceiver receiver = new BroadcastReceiver() {
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            uicList = getView().findViewById(R.id.uicList);
//            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//                // Discovery has found a device. Get the BluetoothDevice
//                // object and its info from the Intent.
//                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                String deviceName = device.getName();
//                String deviceHardwareAddress = device.getAddress(); // MAC address
//
//                arrayList.add(deviceName);
//                adapter.notifyDataSetChanged();
//
//            }
//
//            HashSet hs = new HashSet();
//            hs.addAll(arrayList);
//            arrayList.clear();
//            arrayList.addAll(hs);
//            adapter.notifyDataSetChanged();
//        }
//    };
////
//
    private  void findDevices() {
        // to look for prebonded devices
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
            }
        }

        // Register for broadcasts when a device is discovered.
          bluetoothAdapter.startDiscovery();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        getActivity().registerReceiver(mReceiver, filter);
    }

//    private void enable() {
//        if(bluetoothAdapter == null){
//            // doesnt support bluetooth
//            Toast.makeText(getActivity(), "Device Doesnt support bluetooth", Toast.LENGTH_SHORT).show();
//        }else if (!bluetoothAdapter.isEnabled()) {
//            // to enable bluetooth
////            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
////            startActivityForResult(enableBtIntent,1);
//            bluetoothAdapter.enable();
//            findDevices();
//        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_ENABLE_BT) {
            // Make sure the request was successful

            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                btSwitch.setChecked(true);
              //  findDevices();
                // Do something with the contact here (bigger example below)
            }else{
                Toast.makeText(getActivity(), "Bluetooth couldn't be turned on!", Toast.LENGTH_SHORT).show();
                btSwitch.setChecked(false);
            }
        }
    }



}
