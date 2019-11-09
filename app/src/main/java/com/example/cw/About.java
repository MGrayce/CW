package com.example.cw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class About extends AppCompatActivity {
    String event [] = {"Hotels","Boutiques","Clubs","Cinemas"};
    String hotels = "https://www.orbitz.com/Hotel-Search?adults=2&endDate=2019-11-24&hotelId=17151130&pwaDialog=exit-intent&regionId=2226&sort=RECOMMENDED&startDate=2019-11-23&useRewards=true";
    String boutiques = "https://www.google.com/search?safe=active&sxsrf=ACYBGNR_WnnGQDT9hTU2jwBByueA0nqaXg:1573295716027&q=shopping+malls+in+mbarara&npsic=0&rflfq=1&rlha=0&rllag=-606682,30662532,99&tbm=lcl&ved=2ahUKEwie856i99zlAhXDa1AKHR4vCSwQjGp6BAgKEDw&tbs=lrf:!2m1!1e2!2m1!1e3!2m1!1e16!3sIAE,lf:1,lf_ui:2&rldoc=1#rlfi=hd:;si:;mv:[[-0.5908855138360786,30.65017819947502],[-0.6295071325621058,30.568982368176194],null,[-0.6101963578577555,30.609580283825608],14]";
    String clubs = "http://hikersbay.com/africa/uganda/mbarara/night-club.html?lang=en";
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            String title = mBundle.getString("Title");
            getSupportActionBar().setTitle(title);
                if(title.equals(event[0])){
                    setContentView(R.layout.event_content);
                    webview = findViewById(R.id.hotel_web);
                    eventWeb(hotels);

                }
                else if(title.equals(event[1])){
                    setContentView(R.layout.event_content);
                    webview = findViewById(R.id.hotel_web);
                    eventWeb(boutiques);
                }
                else if(title.equals(event[2])){
                    setContentView(R.layout.event_content);
                    webview = findViewById(R.id.hotel_web);
                    eventWeb(clubs);
                }
                else {
                    setContentView(R.layout.event_content);
                    webview = findViewById(R.id.hotel_web);
                    eventWeb(hotels);
                }


        }



    }
    public void eventWeb(String getUrl){

        webview.setWebViewClient(new WebClient());
        WebSettings set = webview.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        webview.loadUrl(getUrl);

    }
}
class WebClient extends WebViewClient {
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
