package org.ttaluri.artinstituteapiapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ArtworkActivity extends AppCompatActivity {

    private ImageView artInstituteLogo, artworkImageView, externalLinkIcon;
    private TextView artworkTitleTextView, artworkDateTextView, artistNameTextView, artistDetailsTextView;
    private TextView departmentTextView, placeOfOriginTextView, artworkTypeMediumTextView, dimensionsTextView, creditLineTextView, galleryLinkTextView, copyrightTextView;
    private String galleryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork);


        artInstituteLogo = findViewById(R.id.artInstituteLogo);
        artworkTitleTextView = findViewById(R.id.artworkTitleTextView);
        artworkDateTextView = findViewById(R.id.artworkDateTextView);
        artistNameTextView = findViewById(R.id.artistNameTextView);
        artistDetailsTextView = findViewById(R.id.artistDetailsTextView);
        artworkImageView = findViewById(R.id.artworkImageView);
        departmentTextView = findViewById(R.id.departmentTextView);
        placeOfOriginTextView = findViewById(R.id.placeOfOriginTextView);
        artworkTypeMediumTextView = findViewById(R.id.artworkTypeMediumTextView);
        dimensionsTextView = findViewById(R.id.dimensionsTextView);
        creditLineTextView = findViewById(R.id.creditLineTextView);
        galleryLinkTextView = findViewById(R.id.galleryLinkTextView);
        externalLinkIcon = findViewById(R.id.externalLinkIcon);
        copyrightTextView = findViewById(R.id.copyrightText);


        artInstituteLogo.setOnClickListener(v -> finish());


        String title = getIntent().getStringExtra("title");
        String dateDisplay = getIntent().getStringExtra("date_display");
        String artistDisplay = getIntent().getStringExtra("artist_display");
        String imageUrl = getIntent().getStringExtra("image_url");
        String department = getIntent().getStringExtra("department_title");
        String placeOfOrigin = getIntent().getStringExtra("place_of_origin");
        String artworkType = getIntent().getStringExtra("artwork_type_title");
        String medium = getIntent().getStringExtra("medium_display");
        String dimensions = getIntent().getStringExtra("dimensions");
        String creditLine = getIntent().getStringExtra("credit_line");
        galleryId = getIntent().getStringExtra("gallery_id");
        String galleryTitle = getIntent().getStringExtra("gallery_title");


        artworkTitleTextView.setText(title);
        artworkDateTextView.setText(dateDisplay);
        artistNameTextView.setText(artistDisplay.split(",")[0]);
        artistDetailsTextView.setText(artistDisplay.split(",")[1]);
        Picasso.get().load(imageUrl).into(artworkImageView);
        departmentTextView.setText(department);
        placeOfOriginTextView.setText(placeOfOrigin);
        artworkTypeMediumTextView.setText(artworkType + " - " + medium);
        dimensionsTextView.setText(dimensions);
        creditLineTextView.setText(creditLine);


        if ("null".equals(galleryId)) {
            galleryLinkTextView.setText("Not on Display");
            externalLinkIcon.setVisibility(View.GONE);
        } else {
            galleryLinkTextView.setText(galleryTitle);
            externalLinkIcon.setVisibility(View.VISIBLE);
            galleryLinkTextView.setOnClickListener(v -> {
                String galleryUrl = "https://www.artic.edu/galleries/" + galleryId;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(galleryUrl));
                startActivity(browserIntent);
            });
        }


        copyrightTextView.setOnClickListener(v -> {
            Intent intent = new Intent(this, CopyrightActivity.class);
            startActivity(intent);
        });



        artworkImageView.setOnClickListener(v -> {
            Intent intent = new Intent(ArtworkActivity.this, ImageActivity.class);
            intent.putExtra("image_url", imageUrl);
            intent.putExtra("title", title);
            intent.putExtra("artist_display", artistDisplay);
            startActivity(intent);
        });
    }
}
