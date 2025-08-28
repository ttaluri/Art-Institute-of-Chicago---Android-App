package org.ttaluri.artinstituteapiapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import android.net.Uri;


public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton, randomButton;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ImageView clearSearchButton;
    private List<Artwork> artworkList = new ArrayList<>();
    private ArtworkAdapter artworkAdapter;
    private RequestQueue requestQueue;
    private final String BASE_IMAGE_URL = "https://www.artic.edu/iiif/2/";
    private static final int MAX_RESULTS = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        randomButton = findViewById(R.id.randomButton);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        clearSearchButton = findViewById(R.id.clearSearchButton);
        requestQueue = Volley.newRequestQueue(this);


        artworkAdapter = new ArtworkAdapter(artworkList, this);
        recyclerView.setAdapter(artworkAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchButton.setOnClickListener(view -> {
            String keyword = searchEditText.getText().toString().trim();

            // here we are checking if the given keyword is too short or empty, if so calling the respective methods
            if (keyword.length() <= 3) {
                showSearchStringTooShortDialog();
            } else if (!keyword.isEmpty()) {
                searchArtwork(keyword);
            }
        });

        clearSearchButton.setOnClickListener(view -> searchEditText.setText(""));


        artworkAdapter.setOnItemClickListener(artwork -> {
            Intent intent = new Intent(MainActivity.this, ArtworkActivity.class);


            intent.putExtra("title", artwork.getTitle());
            intent.putExtra("date_display", artwork.getDateDisplay());
            intent.putExtra("artist_display", artwork.getArtist()); // Use getArtist() instead of getArtistDisplay()
            intent.putExtra("image_url", artwork.getFullImageUrl());
            intent.putExtra("department_title", artwork.getDepartmentTitle());
            intent.putExtra("place_of_origin", artwork.getPlaceOfOrigin());
            intent.putExtra("artwork_type_title", artwork.getArtworkTypeTitle());
            intent.putExtra("medium_display", artwork.getMediumDisplay());
            intent.putExtra("dimensions", artwork.getDimensions());
            intent.putExtra("credit_line", artwork.getCreditLine());
            intent.putExtra("gallery_id", artwork.getGalleryId());
            intent.putExtra("gallery_title", artwork.getGalleryTitle());

            startActivity(intent);
        });

        /*artworkAdapter.setOnItemClickListener(artwork -> {
            Intent intent = new Intent(MainActivity.this, ArtworkActivity.class);
            intent.putExtra("artworkDetails", (CharSequence) artwork);
            startActivity(intent);
        });*/

        randomButton.setOnClickListener(view -> {
            addDummyArtworks();
            Toast.makeText(MainActivity.this, "Random Button Clicked", Toast.LENGTH_SHORT).show();
        });
    }

    private void addDummyArtworks() {
        // I have created this dummy artwork objects for testing purposes
        artworkList.clear();
        artworkList.add(new Artwork("Artwork 1", "Date 1", "Artist 1", "Details 1",
                "Medium 1", "Artwork Type 1", "123",
                "Dimensions 1", "Department 1", "Credit Line 1",
                "Place of Origin 1", "Gallery 1", "123",
                "https://www.artic.edu/iiif/2/2d484387-2509-5e8e-2c43-22f9981972eb/full/200,/0/default.jpg",
                "https://www.artic.edu/iiif/2/2d484387-2509-5e8e-2c43-22f9981972eb/full/843,/0/default.jpg"));
        artworkList.add(new Artwork("Artwork 2", "Date 2", "Artist 2", "Details 2",
                "Medium 2", "Artwork Type 2", "456",
                "Dimensions 2", "Department 2", "Credit Line 2",
                "Place of Origin 2", "Gallery 2", "456",
                "https://www.artic.edu/iiif/2/1db67905-d421-95bf-1e91-4b60dd776886/full/200,/0/default.jpg",
                "https://www.artic.edu/iiif/2/1db67905-d421-95bf-1e91-4b60dd776886/full/843,/0/default.jpg"));
        artworkList.add(new Artwork("Artwork 3", "Date 3", "Artist 3", "Details 3",
                "Medium 3", "Artwork Type 3", "789",
                "Dimensions 3", "Department 3", "Credit Line 3",
                "Place of Origin 3", "Gallery 3", "789",
                "https://www.artic.edu/iiif/2/d5ebfc97-c87c-b3ae-816f-5c8b519a082c/full/200,/0/default.jpg",
                "https://www.artic.edu/iiif/2/d5ebfc97-c87c-b3ae-816f-5c8b519a082c/full/843,/0/default.jpg"));
        artworkList.add(new Artwork("Artwork 4", "Date 4", "Artist 4", "Details 4",
                "Medium 4", "Artwork Type 4", "101",
                "Dimensions 4", "Department 4", "Credit Line 4",
                "Place of Origin 4", "Gallery 4", "101",
                "https://www.artic.edu/iiif/2/630b093e-f171-764c-6cc7-4bbfb009f789/full/200,/0/default.jpg",
                "https://www.artic.edu/iiif/2/630b093e-f171-764c-6cc7-4bbfb009f789/full/843,/0/default.jpg"));

        artworkAdapter.notifyDataSetChanged();
    }




    private void searchArtwork(String keyword) {
        String baseUrl = "https://api.artic.edu/api/v1/artworks/search";
        String limit = String.valueOf(MAX_RESULTS);
        String page = "1";


        Uri builtUri = Uri.parse(baseUrl).buildUpon()
                .appendQueryParameter("q", keyword)
                .appendQueryParameter("limit", limit)
                .appendQueryParameter("page", page)
                .appendQueryParameter("fields", "title,date_display,artist_display,medium_display," +
                        "artwork_type_title,image_id,dimensions,department_title,credit_line," +
                        "place_of_origin,gallery_title,gallery_id,id,api_link")
                .build();


        String url = builtUri.toString();
        progressBar.setVisibility(View.VISIBLE);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray artworks = response.getJSONArray("data");
                        artworkList.clear();

                        for (int i = 0; i < artworks.length(); i++) {
                            JSONObject artworkJson = artworks.getJSONObject(i);
                            String title = artworkJson.optString("title", "");
                            String dateDisplay = artworkJson.optString("date_display", "");
                            String artistDisplay = artworkJson.optString("artist_display", "");
                            String mediumDisplay = artworkJson.optString("medium_display", "");
                            String artworkTypeTitle = artworkJson.optString("artwork_type_title", "");
                            String imageId = artworkJson.optString("image_id", "");
                            String dimensions = artworkJson.optString("dimensions", "");
                            String departmentTitle = artworkJson.optString("department_title", "");
                            String creditLine = artworkJson.optString("credit_line", "");
                            String placeOfOrigin = artworkJson.optString("place_of_origin", "");
                            String galleryTitle = artworkJson.optString("gallery_title", "Not on Display");
                            String galleryId = artworkJson.optString("gallery_id", null);
                            String apiLink = artworkJson.optString("api_link", "");


                            Artwork artwork = new Artwork(title, dateDisplay, artistDisplay, "",
                                    mediumDisplay, artworkTypeTitle, imageId, dimensions,
                                    departmentTitle, creditLine, placeOfOrigin, galleryTitle,
                                    galleryId, apiLink, BASE_IMAGE_URL + imageId + "/full/843,/0/default.jpg");
                            artworkList.add(artwork);
                        }


                        if (artworkList.isEmpty()) {
                            showNoResultsDialog();
                        } else {

                            artworkAdapter.notifyDataSetChanged();
                        }

                        progressBar.setVisibility(View.GONE);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressBar.setVisibility(View.GONE);
                    }
                }, error -> {

            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
        });


        requestQueue.add(jsonObjectRequest);
    }



    private void showNoResultsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Search Results Found");
        builder.setMessage("Sorry, but there are no results matching your search.");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSearchStringTooShortDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search string too short.");
        builder.setMessage("Please enter a longer search string.");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void openCopyrightActivity(View view) {
        Intent intent = new Intent(this, CopyrightActivity.class);
        startActivity(intent);
    }
}

