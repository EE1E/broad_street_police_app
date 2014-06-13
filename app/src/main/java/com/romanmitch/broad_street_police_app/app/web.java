package com.romanmitch.broad_street_police_app.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.app.Activity;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class web extends ActionBarActivity {

    SharedPreferences shared;


    private WebView webView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        shared = getSharedPreferences("A", Context.MODE_PRIVATE);

        webView = (WebView) findViewById(R.id.webview);
        if(shared.getString("web", "").equals("map")) {
            webView.setWebViewClient(new WebViewClient());
            WebSettings webSettings = webView.getSettings();
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            webView.getSettings().setJavaScriptEnabled(true);

            webView.loadUrl("http://www.google.com/fusiontables/embedviz?q=select+col3+from+1tWJClbFUNMsZCipW_vE-iw3pHrheRNMFVLUdkog1&viz=MAP&h=false&lat=52.43964717472843&lng=-1.9000556754779154&t=1&z=13&l=col3&y=2&tmplt=2&hml=TWO_COL_LAT_LNG");

        }
        if(shared.getString("web", "").equals("spreadsheet")) {
            webView.setWebViewClient(new WebViewClient());
              WebSettings webSettings = webView.getSettings();
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            webView.getSettings().setJavaScriptEnabled(true);
            //webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);

            String summary ="<iframe src=\"https://docs.google.com/spreadsheets/d/1AR9ypWeN5ty60hQvucjPqjnincKno_EzzBj8RYVG1Js/pubhtml?widget=true&amp;headers=false\"  width=\"600\" height=\"780\" style=\"border: none;\"></iframe>";
            webView.loadData(summary, "text/html", null);

           // webView.loadUrl("http://docs.google.com/spreadsheets/d/1AR9ypWeN5ty60hQvucjPqjnincKno_EzzBj8RYVG1Js/edit#gid=75854638");

           // webView.setVerticalScrollBarEnabled(false);


        }


}


}



/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

