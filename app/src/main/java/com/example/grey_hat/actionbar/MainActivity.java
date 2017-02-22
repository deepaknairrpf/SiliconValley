package com.example.grey_hat.actionbar;

import android.app.ActionBar;
;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;

import static com.example.grey_hat.actionbar.R.menu.menu_menu;

public class MainActivity extends FragmentActivity implements ListView.OnItemClickListener {
    private ShareActionProvider shareActionProvider;
        private ImageView imageView;
        private  FragmentTransaction ft;
        private Fragment fragment;
        private int fragId=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null)
        {
            Log.e("ERR","fragment restore instance ");
            fragId=savedInstanceState.getInt("fragId",-1);
            if(fragId!=-1) {
                Log.e("ERR","fragment restore instance " + fragId);
                switch (fragId)
                {

                    case 0:
                        fragment = new HomeFrag();
                        break;
                    case 1:
                        fragment = new seriesCharacterFrag();
                        break;
                    case 2:
                        fragment = new Scraper();
                        break;
                    default:
                        fragment=null;
                        break;

                }
                if(fragment!=null) {
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.mainFrag, fragment, "myFragment");
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }
            }
        }

        ActionBar actionBar  =getActionBar();
        actionBar.setTitle("Silicon Valley ");
        ListView listView = (ListView)findViewById(R.id.drawer_list);
        String array[]={"Home","Characters","Bloopers","Gallery"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,array);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        imageView = (ImageView) findViewById(R.id.MainImg);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("ERR","onsave");
        outState.putInt("fragId",fragId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(fragId==-1) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                imageView.setBackgroundResource(R.drawable.wallp);
            } else {
                imageView.setBackgroundResource(R.drawable.walpapr);
            }
        }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menu_menu,menu);
        MenuItem menuItem = (MenuItem)menu.findItem(R.id.share);
        shareActionProvider =(ShareActionProvider) menuItem.getActionProvider();
        setIntent("Silicon Valley is just Awesome!!!");
        return super.onCreateOptionsMenu(menu);


    }


    private void setIntent(String text)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,text);
        shareActionProvider.setShareIntent(intent);

    }

    @Override
    public void onBackPressed() {
        if(fragment==null)
        super.onBackPressed();
        else
        {
           getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            fragment=null;
        }
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        final MediaPlayer mp = MediaPlayer.create(this,R.raw.menuclick);
        mp.setVolume(0.3f,0.3f);
        mp.start();
        return super.onMenuItemSelected(featureId, item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final MediaPlayer mp = MediaPlayer.create(this,R.raw.menuclick);
        mp.setVolume(0.3f,0.3f);
        mp.start();
        imageView.setVisibility(View.INVISIBLE);
         RelativeLayout drwrRel = (RelativeLayout)findViewById(R.id.linList);
        fragId=position;
        switch (position)
        {

            case 0:
                fragment = new HomeFrag();
                break;
            case 1:
                fragment = new seriesCharacterFrag();
                break;
            case 2:
                fragment = new Scraper();
                break;
            default:
                fragment=null;
                break;

        }
        if(fragment!=null) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrag, fragment, "myFragment");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        DrawerLayout dw = (DrawerLayout)findViewById(R.id.drawer_layout);
        dw.closeDrawer(drwrRel);

    }
}
