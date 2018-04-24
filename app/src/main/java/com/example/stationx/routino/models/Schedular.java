package com.example.stationx.routino.models;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stationx.routino.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Schedular extends Fragment {


    public Schedular() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedular, container, false);
    }

}
