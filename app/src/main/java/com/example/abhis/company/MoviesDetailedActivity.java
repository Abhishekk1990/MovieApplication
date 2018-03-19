package com.example.abhis.company;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by abhis on 3/19/2018.
 */

public class MoviesDetailedActivity extends Activity {

    private ImageView image1;
    private TextView title, date, rating, overview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movies_details);
        image1 = (ImageView) findViewById(R.id.postered);

        title = (TextView) findViewById(R.id.titles);

        date = (TextView) findViewById(R.id.dated);

        rating = (TextView) findViewById(R.id.ratings);

        overview = (TextView) findViewById(R.id.overviews);

        MoviesMeta details = (MoviesMeta) getIntent().getExtras().getSerializable("MOVIE_DETAILS");

        if (details != null) {

            Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + details.getPoster_path()).into(image1);

            title.setText(details.getOriginal_title());
            date.setText(details.getRelease_date());
            rating.setText(Double.toString(details.getVote_average()));
            overview.setText(details.getOverview());

        }
    }
}


