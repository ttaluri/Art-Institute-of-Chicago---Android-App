package org.ttaluri.artinstituteapiapp;


import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class GalleryActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        webView = findViewById(R.id.webView);
        webView.loadUrl("https://www.artic.edu/galleries/25474");
    }

    @Override
    protected void onDestroy() {

        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}
