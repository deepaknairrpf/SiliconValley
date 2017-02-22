package com.example.grey_hat.actionbar;


import android.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment implements  YouTubePlayer.OnInitializedListener{
    private YouTubePlayerView youTubePlayerView;
    private static final String YoutubeDeveloperKey = "AIzaSyBI7q2aWR8VAfyigl-QVSfj0nUwA_0y61E";
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private View view;
    private YouTubePlayer youTubePlayer;
    private  int seekTime=0;
    private static final String videoId="69V__a49xtw";
    public HomeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setTitle("Home");
        View rootView = inflater.inflate(R.layout.frag_home, container, false);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.Trailer,youTubePlayerFragment).commit();
        youTubePlayerFragment.initialize(YoutubeDeveloperKey,this);
        return rootView;
    }

    @Override
    public void onStart() {
       View view = getView();
        TextView textView = (TextView)view.findViewById(R.id.welcomeDes);
        String welcomeDes = "Set amidst the high-tech world of the Silicon Valley in USA,the series revolves around the struggles of six programmers who are trying to make a mark in the big, bad world of programming.\n\n" +
                "Genre: Comedy\n" +
                "Writers: Mike Judge, Alec Berg, John Altschuler, Carrie Kemper, more\n" +
                "Awards: Critics' Choice Television Award for Best Supporting Actor in a Comedy Series and many more.\n";
       textView.setText(welcomeDes);

        super.onStart();
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            this.youTubePlayer=player;
            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(videoId);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(youTubePlayer!=null)
        {
            youTubePlayer.loadVideo(videoId,seekTime);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        seekTime=youTubePlayer.getCurrentTimeMillis();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {

        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(getActivity(), RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = "Error! ";
            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}