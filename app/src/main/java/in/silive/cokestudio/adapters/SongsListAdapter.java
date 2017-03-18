package in.silive.cokestudio.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.silive.cokestudio.R;

/**
 * Created by akriti on 18/3/17.
 */

public class SongsListAdapter extends BaseAdapter {
    public static ArrayList<String> names_of_songs = new ArrayList<>();
    public static ArrayList<String> url_of_songs = new ArrayList<>();
    public static ArrayList<String> artist_of_songs = new ArrayList<>();
    public static ArrayList<String> image_of_songs = new ArrayList<>();
    public static Context context;

    public SongsListAdapter(Context c,ArrayList<String>names,ArrayList<String>url,ArrayList<String>artist,ArrayList<String>image) {
        this.names_of_songs = names;
        this.url_of_songs = url;
        this.artist_of_songs = artist;
        this.image_of_songs = image;
        this.context = c;
    }

    private class Holder{
        TextView song_name, name_of_artists;
        ImageView song_img, song_play, song_download;
        CheckBox song_fav;
    }
    @Override
    public int getCount() {
        return names_of_songs.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.songs_list_adapter,viewGroup,false);
        Holder holder = new Holder();
        holder.song_name = (TextView)view.findViewById(R.id.song_name);
        holder.name_of_artists = (TextView)view.findViewById(R.id.name_of_artists);
        holder.song_img = (ImageView)view.findViewById(R.id.song_img);
        holder.song_play = (ImageView)view.findViewById(R.id.song_play);
        holder.song_download = (ImageView)view.findViewById(R.id.song_download);
        holder.song_name.setText(names_of_songs.get(i));
        holder.name_of_artists.setText("Artists : "+artist_of_songs);
        holder.song_img.setImageURI(Uri.parse(image_of_songs.get(i)));

        return view;
    }
}
