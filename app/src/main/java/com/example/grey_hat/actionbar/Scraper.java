package com.example.grey_hat.actionbar;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.R.attr.bitmap;
import static android.R.attr.onClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class Scraper extends Fragment implements AdapterView.OnItemClickListener{

private ProgressDialog mProgressDialog;
    String url = "http://www.androidbegin.com";
    private ListView listView;
    private ImageView imgerr;
    ArrayList<String> imgUrls = new ArrayList<>();
    ArrayList<Bitmap> Bitmaps = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> videoIDs = new ArrayList<>();
    private boolean AttachFlag;
    public Scraper() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onResume() {
        super.onResume();
        listView = (ListView)getView().findViewById(R.id.scrapperList);
        listView.setOnItemClickListener(this);
        imgerr=(ImageView)getView().findViewById(R.id.errImg);
     //   getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        new Title().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scraper, container, false);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),youTube.class);
        intent.putExtra("Video_ID",videoIDs.get(position).substring(9));
        Log.e("Debuggin",videoIDs.get(position).substring(9));
        startActivity(intent);

    }


    private class Title extends AsyncTask<Void, Void, Short> {
        String title;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            mProgressDialog = new ProgressDialog(getActivity());
//            mProgressDialog.setTitle("Android Basic JSoup Tutorial");
//            mProgressDialog.setMessage("Loading...");
//            mProgressDialog.setIndeterminate(false);
//            mProgressDialog.show();
        }

        @Override
        protected Short doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("https://www.youtube.com/results?search_query=Silicon Valley").timeout(6000).get();

                Elements imgs = doc.select("span[class=yt-thumb-simple]");
                Elements contents = doc.getElementsByClass("yt-uix-tile-link yt-ui-ellipsis yt-ui-ellipsis-2 yt-uix-sessionlink      spf-link ");

                int i =0;



                for(Element image : imgs)
                {
                    Elements images = image.getElementsByTag("img");
                    //System.out.println(images.toString());
                    for(Element j : images)
                    {
                        if(j.attr("data-thumb")=="")
                            imgUrls.add(j.attr("src"));
                        else
                            imgUrls.add(j.attr("data-thumb"));
                    }
                }


                for(Element content : contents)
                {

                    titles.add(content.attr("title"));
                    videoIDs.add(content.attr("href"));

                }

                for(String url : imgUrls)
                {
                    InputStream input = new java.net.URL(url).openStream();
                    // Decode Bitmap
                    Bitmaps.add(BitmapFactory.decodeStream(input));
                }

            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Short result) {
            // Set title into TextView
            //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
            if(result==0)
            {
                Toast.makeText(getContext(), "Oopsie", Toast.LENGTH_LONG).show();
                imgerr.setBackgroundResource(R.drawable.errimg);
                listView.setVisibility(View.INVISIBLE);
                imgerr.setVisibility(View.VISIBLE);
                //mProgressDialog.dismiss();
                return;
            }
            customCardAdapter customCardAdapter = new customCardAdapter(getActivity(),Bitmaps,titles,videoIDs);
            listView.setAdapter(customCardAdapter);
          //  mProgressDialog.dismiss();


        }
    }


}
