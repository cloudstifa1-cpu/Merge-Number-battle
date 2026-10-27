package com.ravens.mergegrid;
import android.app.Activity;import android.os.Bundle;import android.webkit.WebView;import android.webkit.WebSettings;import android.graphics.Color;import android.os.Vibrator;import android.os.VibrationEffect;import android.os.Build;import android.webkit.JavascriptInterface;
public class MainActivity extends Activity {
  WebView w;
  @Override protected void onCreate(Bundle b){super.onCreate(b);
    getWindow().setStatusBarColor(Color.parseColor("#07070f"));
    w=new WebView(this);w.setBackgroundColor(Color.parseColor("#07070f"));
    WebSettings s=w.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);s.setAllowFileAccess(true);s.setMediaPlaybackRequiresUserGesture(false);
    w.addJavascriptInterface(new Bridge(),"Android");setContentView(w);w.loadUrl("file:///android_asset/index.html");}
  class Bridge{@JavascriptInterface public void vibrate(int ms){Vibrator v=(Vibrator)getSystemService(VIBRATOR_SERVICE);if(v==null)return;
    if(Build.VERSION.SDK_INT>=26)v.vibrate(VibrationEffect.createOneShot(ms,VibrationEffect.DEFAULT_AMPLITUDE));else v.vibrate(ms);}}
  @Override public void onBackPressed(){finish();}
}
