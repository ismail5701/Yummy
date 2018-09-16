package com.example.dell.yummy.user.store;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.yummy.Constants;
import com.example.dell.yummy.DataSingleton;
import com.example.dell.yummy.R;
import com.example.dell.yummy.IFragmentListener;
import com.example.dell.yummy.model.StoreDetails;
import com.example.dell.yummy.webservice.RetrofitNetworksCalls;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserStoresFragment extends Fragment {
    RecyclerView recyclerView;
    IFragmentListener miFragmentListener;
    List<StoreDetails> StoreList;


    public UserStoresFragment() {
        // Required empty public constructor
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null
                    && intent.getAction().equals(Constants.NOTIFY_STORE_DETAILS)) {
                showStoreDetails();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_stores, container,
                false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.rv_stores);
        recyclerView.setHasFixedSize(true);
        IntentFilter intentFilter = new IntentFilter(Constants.NOTIFY_STORE_DETAILS);
        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(broadcastReceiver, intentFilter);

    }

    public void addListener(IFragmentListener miFragmentListener) {
        this.miFragmentListener = miFragmentListener;
    }

    public void showStoreDetails() {
        RetrofitNetworksCalls retrofitNetworksCalls = DataSingleton.getInstance()
                .getRetrofitNetworksCallsObject();
        if (retrofitNetworksCalls != null) {
            List<StoreDetails> details = retrofitNetworksCalls.getStoreDetailsList();
            if (details != null) {

                UserStoresAdapter adapter = new UserStoresAdapter(getActivity(),
                        details, miFragmentListener);
                //setting adapter to recyclerview
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        }

    }
}
