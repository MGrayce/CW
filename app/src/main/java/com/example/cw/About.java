package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class About extends AppCompatActivity {
    String event [] = {"Hotels","Boutiques","Clubs","Cinemas"};
    String hotels = "https://www.orbitz.com/Hotel-Search?adults=2&endDate=2019-11-24&hotelId=17151130&pwaDialog=exit-intent&regionId=2226&sort=RECOMMENDED&startDate=2019-11-23&useRewards=true";
    String boutiques = "https://www.google.com/search?safe=active&sxsrf=ACYBGNR_WnnGQDT9hTU2jwBByueA0nqaXg:1573295716027&q=shopping+malls+in+mbarara&npsic=0&rflfq=1&rlha=0&rllag=-606682,30662532,99&tbm=lcl&ved=2ahUKEwie856i99zlAhXDa1AKHR4vCSwQjGp6BAgKEDw&tbs=lrf:!2m1!1e2!2m1!1e3!2m1!1e16!3sIAE,lf:1,lf_ui:2&rldoc=1#rlfi=hd:;si:;mv:[[-0.5908855138360786,30.65017819947502],[-0.6295071325621058,30.568982368176194],null,[-0.6101963578577555,30.609580283825608],14]";
    String clubs = "http://hikersbay.com/africa/uganda/mbarara/night-club.html?lang=en";
    String country_cc = "https://www.countrycinemaug.com/";
    String pep_cc = "https://www.pep.co.ug/";
    String EASYuri = "geo:-0.6020966, 30.6636947?q=easy view complex";
    String easy_cc = "https://placesmap.net/UG/Easy-view-complex-117261/";
    String PEPuri = "geo:-0.6020966, 30.6636947?q=pep store";
    String Silveruri = "geo:-0.6020966, 30.6636947?q=silver theater";
    String Countryuri = "geo:-0.6020966, 30.6636947?q=country cinema mbarara";
    String moreCinemas = "geo:-0.6020966, 30.6636947?q=cinemas";
    String moreBoutiques = "geo:-0.6020966, 30.6636947?q=shopping malls";
    String silver = "https://placesmap.net/UG/Silver-Theater-3542085/";
    WebView webview;
    TextView cc_map,cc_desc,ss_map,ss_desc,all;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            String title = mBundle.getString("Title");
            getSupportActionBar().setTitle(title);
                if(title.equals(event[0])){
                    setContentView(R.layout.event_content);
                    progressBar = findViewById(R.id.progressBar);
                    webview = findViewById(R.id.hotel_web);
                    eventWeb(hotels);

                }
                else if(title.equals(event[1])){
                    setContentView(R.layout.boutiques_details);
                    cc_map = findViewById(R.id.cc_map);
                    cc_map.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadMap(PEPuri);
                        }
                    });
                    cc_desc = findViewById(R.id.vd_cc);
                    cc_desc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setContentView(R.layout.event_content);
                            progressBar = findViewById(R.id.progressBar);
                            webview = findViewById(R.id.hotel_web);
                            eventWeb(pep_cc);
                        }
                    });
                    ss_map = findViewById(R.id.ss_map);
                    ss_map.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadMap(EASYuri);
                        }
                    });
                    ss_desc = findViewById(R.id.vd_ss);
                    ss_desc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setContentView(R.layout.event_content);
                            progressBar = findViewById(R.id.progressBar);
                            webview = findViewById(R.id.hotel_web);
                            eventWeb(easy_cc);
                        }
                    });
                    all = findViewById(R.id.all);
                    all.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadMap(moreBoutiques);
                        }
                    });
                }
                else if(title.equals(event[2])){
                    setContentView(R.layout.event_content);
                    progressBar = findViewById(R.id.progressBar);
                    webview = findViewById(R.id.hotel_web);
                    eventWeb(clubs);
                }
                else {
                    setContentView(R.layout.cinemas_details);
                    cc_map = findViewById(R.id.cc_map);
                    cc_map.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadMap(Countryuri);
                        }
                    });
                    cc_desc = findViewById(R.id.vd_cc);
                    cc_desc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setContentView(R.layout.event_content);
                            progressBar = findViewById(R.id.progressBar);
                            webview = findViewById(R.id.hotel_web);
                            eventWeb(country_cc);
                        }
                    });
                    ss_map = findViewById(R.id.ss_map);
                    ss_map.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadMap(Silveruri);
                        }
                    });
                    ss_desc = findViewById(R.id.vd_ss);
                    ss_desc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setContentView(R.layout.event_content);
                            progressBar = findViewById(R.id.progressBar);
                            webview = findViewById(R.id.hotel_web);
                            eventWeb(silver);
                        }
                    });
                    all = findViewById(R.id.all);
                    all.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadMap(moreCinemas);
                        }
                    });

                }

        }
    }
    public void eventWeb(String getUrl){
        Bundle mBundle = getIntent().getExtras();
        String title = mBundle.getString("Title");
        Toast.makeText(this, "Loading "+title, Toast.LENGTH_SHORT).show();
        webview.setWebViewClient(new WebClient());
        WebSettings set = webview.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        webview.loadUrl(getUrl);
        progressBar.setVisibility(View.INVISIBLE);
    }
    public void loadMap(String uri){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mapIntent.setPackage("com.google.android.apps.maps");
        mapIntent.resolveActivity(getPackageManager());
        startActivity(mapIntent);
    }
}

class WebClient extends WebViewClient {
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

}
