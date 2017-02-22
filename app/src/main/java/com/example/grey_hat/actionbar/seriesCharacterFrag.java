package com.example.grey_hat.actionbar;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class seriesCharacterFrag extends Fragment implements  ListView.OnItemClickListener {
    private Context contextObj;
    private final String [] characters = {"Gilfoyle","Richard Hendricks","Dinesh","Elrich Bachman","Jared","Nelson Bighetti aka Big head","Gavin Belson"
    ,"Monica","Chin Yang","Russ Hannemann"};
    private final Integer[] resorceIds={R.drawable.gil_foyle,R.drawable.richard,R.drawable.dinesh,R.drawable.elrich,R.drawable.jared
    ,R.drawable.bighead,R.drawable.belsonhighres,R.drawable.monica2,R.drawable.chinyang,R.drawable.russ};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contextObj = inflater.getContext();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_series_characters, container, false);


    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        ListView list = (ListView)view.findViewById(R.id.PizList);
        customAdapter custmAdaptr = new customAdapter(getActivity(),characters,resorceIds);
        list.setAdapter(custmAdaptr);
        list.setOnItemClickListener(this);
}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setTitle("Geeks !");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final MediaPlayer mp = MediaPlayer.create(getActivity(),R.raw.buttonclick);
        mp.setVolume(0.3f,0.3f);
        mp.start();
        final String[] idArray={"dkrzZ8-gfn0","gx8QOVsSMvI","cqYaVaQxMOo","l9_PfruyLlU","GpbG6w43DM0"
        ,"0owqfskr_jQ","HqJubhty8NY","pDxlzgLWKIA","2StbLLvdx6I","Ra8San4epfY"};
        Intent intent = new Intent(getActivity(),youTube.class);
        intent.putExtra("Video_ID",idArray[position]);
        startActivity(intent);
    }
}
