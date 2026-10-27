package com.ravens.mergegrid;
import android.app.Activity;import android.os.Bundle;import android.webkit.WebView;import android.webkit.WebSettings;import android.graphics.Color;
public class MainActivity extends Activity {
  WebView w;
  @Override protected void onCreate(Bundle b){super.onCreate(b);
    getWindow().setStatusBarColor(Color.parseColor("#07070f"));
    w=new WebView(this);w.setBackgroundColor(Color.parseColor("#07070f"));
    WebSettings s=w.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);s.setAllowFileAccess(true);
    setContentView(w);w.loadUrl("file:///android_asset/index.html");}
  @Override public void onBackPressed(){finish();}
}
