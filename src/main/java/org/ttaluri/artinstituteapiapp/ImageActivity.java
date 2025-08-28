package org.ttaluri.artinstituteapiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

public class ImageActivity extends AppCompatActivity {

    private PhotoView artworkImageView;
    private TextView artworkTitleTextView;
    private TextView artistNameTextView;
    private TextView artistDetailsTextView, copyrightTextView;
    private ImageView artInstituteLogo;
    private TextView zoomScaleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        artworkImageView = findViewById(R.id.artworkImageView);
        artworkTitleTextView = findViewById(R.id.artworkTitleTextView);
        artistNameTextView = findViewById(R.id.artistNameTextView);
        artistDetailsTextView = findViewById(R.id.artistDetailsTextView);
        artInstituteLogo = findViewById(R.id.artInstituteLogo);
        copyrightTextView = findViewById(R.id.copyrightText);
        zoomScaleTextView = findViewById(R.id.zoomScaleTextView);


        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String artistDisplay = intent.getStringExtra("artist_display");
        String imageUrl = intent.getStringExtra("image_url");

        artworkTitleTextView.setText(title);
        if (artistDisplay != null && artistDisplay.contains(",")) {
            String[] artistInfo = artistDisplay.split(",");
            artistNameTextView.setText(artistInfo[0].trim());
            artistDetailsTextView.setText(artistInfo[1].trim());
        }


        Glide.with(this).load(imageUrl).into(artworkImageView);

        artworkImageView.setOnScaleChangeListener((scaleFactor, focusX, focusY) -> {
            int zoomPercent = Math.round(scaleFactor * 100);
            zoomScaleTextView.setText("Scale: " + zoomPercent + "%");
        });


        artInstituteLogo.setOnClickListener(v -> {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        });

        copyrightTextView.setOnClickListener(v -> {
            Intent intents = new Intent(this, CopyrightActivity.class);
            startActivity(intents);
        });

    }
}
