package com.example.abhis.company;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView1 = findViewById(R.id.list1);
        listView1.setOnItemClickListener(this);
        new CheckConnectionStatus().execute("https://api.themoviedb.org/3/movie/popular?api_key=526260143dbfd495e114779215769705&language=en-US&page=1");

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, MoviesDetailedActivity.class);
        intent.putExtra("MOVIE_DETAILS", (MoviesMeta) parent.getItemAtPosition(position));
        startActivity(intent);


    }

    class CheckConnectionStatus extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            try {

                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try

        {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = bufferedReader.readLine();
            bufferedReader.close();
            return s;

        }
        catch(IOException e)

        {
            Log.e("Error: ", e.getMessage(), e);
        }

    return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        JSONObject jsonObject = null;
        try {

//Parent JSON Object. Json object start at { and end at }
            jsonObject = new JSONObject(s);
            ArrayList<MoviesMeta> movieList = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                MoviesMeta movieDetails = new MoviesMeta();
                movieDetails.setOriginal_title(object.getString("original_title"));
                movieDetails.setVote_average(object.getDouble("vote_average"));
                movieDetails.setOverview(object.getString("overview"));
                movieDetails.setRelease_date(object.getString("release_date"));
                movieDetails.setPoster_path(object.getString("poster_path"));
                movieList.add(movieDetails);


            }
            MoviesArrayAdapter movieArrayAdapter = new MoviesArrayAdapter(MainActivity.this, R.layout.movies_listed, movieList);

            listView1.setAdapter(movieArrayAdapter);
        } catch (JSONException e) {

            e.printStackTrace();
        }

    }
}
}