package com.example.lucian.sqlite.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lucian.sqlite.R;

public class HeaderFragment extends Fragment {

    //    public HeaderFragment(String s){
//
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        Bundle params = getArguments();
//        Log.i("PDM", params.getString("title"));
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_header, container, false);
//
////        TextView tv = (TextView) rootView.findViewById(R.id.title);
////        tv.setText(getArguments().getString("title"));
//
//        return rootView;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_header, container, false);
        return rootView;
    }

}
