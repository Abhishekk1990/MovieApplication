package com.example.abhis.company;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by abhis on 3/19/2018.
 */

public class MoviesArrayAdapter extends ArrayAdapter {

    private List<MoviesMeta> movieDetailsList;

    private int resource;

    private Context context;

    public MoviesArrayAdapter(Context context, int resource, List<MoviesMeta> movieDetails) {
        super(context, resource, movieDetails);
        this.context = context;
        this.movieDetailsList = movieDetails;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MoviesMeta details = movieDetailsList.get(position);

        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView movieName = (TextView) view.findViewById(R.id.textView1);
        ImageView image = (ImageView) view.findViewById(R.id.imageView1);
        movieName.setText(details.getOriginal_title());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + details.getPoster_path()).into(image);
        return view;
    }
}
