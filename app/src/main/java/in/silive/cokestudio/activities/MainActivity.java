package in.silive.cokestudio.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import in.silive.cokestudio.R;

public class MainActivity extends AppCompatActivity {
    ListView songs_list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songs_list_view = (ListView)findViewById(R.id.songs_list_view);
    }
}
