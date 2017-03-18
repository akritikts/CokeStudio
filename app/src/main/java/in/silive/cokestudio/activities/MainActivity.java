package in.silive.cokestudio.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import in.silive.cokestudio.R;
import in.silive.cokestudio.adapters.SongsListAdapter;
import in.silive.cokestudio.network.HttpHandler;
import in.silive.cokestudio.utils.Config;

public class MainActivity extends AppCompatActivity {
    ListView songs_list_view;
    public static ArrayList<String> names_of_songs = new ArrayList<>();
    public static ArrayList<String> url_of_songs = new ArrayList<>();
    public static ArrayList<String> artist_of_songs = new ArrayList<>();
    public static ArrayList<String> image_of_songs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songs_list_view = (ListView)findViewById(R.id.songs_list_view);
        new FetchData(this).execute();
        /*songs_list_view.setAdapter(new SongsListAdapter(getApplicationContext(),names_of_songs,url_of_songs,artist_of_songs,image_of_songs));*/

    }
    public class FetchData extends AsyncTask<Void,Void,String> {
        public Context context;
        public URL H_url;
        public HttpURLConnection H_connection;
        public BufferedReader H_bufferedReader;
        public StringBuilder H_response;
        public ProgressDialog progressDialog;
        public String jsonStr;
        public FetchData(Context c) {
            context = c;
            this.progressDialog = new ProgressDialog(c);
        }
        @Override
        protected String doInBackground(Void... voids) {
            try {
                H_url = new URL(Config.API_URL);
                H_connection = (HttpURLConnection) H_url.openConnection();
                Log.d("TAG", "url : " + H_url);
                Log.d("TAG", "connection");
                H_connection.setRequestMethod("GET");
                H_connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                Log.d("TAG", "connection req property get");
                H_connection.connect();
                Log.d("TAG", "connection estb");
                H_bufferedReader = new BufferedReader(new InputStreamReader(H_connection.getInputStream()));
                Log.d("TAG", "buff readr");
                HttpHandler sh = new HttpHandler();
                jsonStr = sh.makeServiceCall(Config.API_URL);
            } catch (Exception e) {
                Log.d("TAG", "NO connection");
                e.printStackTrace();
            }
            return jsonStr;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Fetching Data ");
            progressDialog.show();
        }
        @Override
        protected void onPostExecute(String s)
        {
            // super.onPostExecute(s);
            parsing_Batsmen(s);
            //notify data set changed;
            progressDialog.dismiss();
            songs_list_view.setAdapter(new SongsListAdapter(getApplicationContext(),names_of_songs,url_of_songs,artist_of_songs,image_of_songs));
        }
        public void parsing_Batsmen(String s) {
            try {
                Log.d("TAG", "try parsing");
                Log.d("TAG", "JSON data : "+s);
                //Log.d("TAG", "JSON obj created");


                Log.d("TAG", "JSON array fetched");
                //JSONObject jsonObject = new JSONObject("");
                JSONArray List_of_batsmen = new JSONArray(s);
                Log.d("TAG", "JSON array fetched"+List_of_batsmen);
                for (int i = 0; i < List_of_batsmen.length(); i++) {
                    JSONObject songs_list = List_of_batsmen.getJSONObject(i);
                    names_of_songs.add(songs_list.getString("song"));
                    url_of_songs.add(songs_list.getString("url"));
                    artist_of_songs.add(songs_list.getString("artists"));
                    image_of_songs.add(songs_list.getString("cover_image"));

                }
                Log.d("TAG", "Item added");

            }
            catch (Exception e) {
                Log.d("TAG", "Printing stack trace : "+"\n\n");
                e.printStackTrace();
                Log.d("TAG", "Parsing not working");
            }
        }
    }

}
