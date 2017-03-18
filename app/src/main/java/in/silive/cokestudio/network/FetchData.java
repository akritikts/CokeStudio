package in.silive.cokestudio.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import in.silive.cokestudio.utils.Config;

/**
 * Created by akriti on 18/3/17.
 */

public class FetchData extends AsyncTask<Void,Void,String> {
    public Context context;
    public URL H_url;
    public HttpURLConnection H_connection;
    public BufferedReader H_bufferedReader;
    public StringBuilder H_response;
    public ProgressDialog progressDialog;
    public String jsonStr;
    public static ArrayList<String> names_of_players;
    public static ArrayList<String> desc_of_players;
    public static ArrayList<Integer> img_of_players;
    public static ArrayList<String> country_of_players;
    public static ArrayList<Long> runs_of_players;
    public static ArrayList<Integer> matches_of_players;
    public FetchData(Context c) {
        context = c;
        this.progressDialog = new ProgressDialog(c);
    }
    @Override
    protected String doInBackground(Void... voids) {
        try {
            H_url = new URL(Config.API_KEY);
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
            jsonStr = sh.makeServiceCall(Config.API_KEY);
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
    }
    public void parsing_Batsmen(String s) {
        try {
            Log.d("TAG", "try parsing");
            Log.d("TAG", "JSON data : "+s);
            //Log.d("TAG", "JSON obj created");

/*JSONArray List_of_kings = King_object.getJSONArray();*/
            Log.d("TAG", "JSON array fetched");
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray List_of_batsmen = jsonObject.getJSONArray("records");
            for (int i = 0; i < List_of_batsmen.length(); i++) {
                JSONObject batsm_list = List_of_batsmen.getJSONObject(i);

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
