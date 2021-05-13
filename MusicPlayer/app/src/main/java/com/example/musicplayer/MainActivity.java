package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnItemClickListener{

    public ArrayList<Music> musicList;
    public MusicAdapter mAdapter;
    public RecyclerView mRecyclerView;
    ArrayList<String> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicList = new ArrayList<>();
        mList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.musicRecyclerView);
        addMusicFile();
        musicRecyclerView();

    }
    public void addMusicFile() {
        Field[] f = R.raw.class.getDeclaredFields();

        for(int i = 0; i < f.length; i++) {
           String musicName = f[i].getName();
           musicList.add(new Music(musicName));
           mList.add(f[i].getName());
           Log.d("m", musicName);
           musicRecyclerView();
        }
    }

    public void musicRecyclerView() {
        mAdapter = new MusicAdapter(musicList , MainActivity.this, this);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(int position) {

        Intent musicIntent = new Intent(this, MusicDetail.class);

        Music musicName = musicList.get(position);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/raw/"+musicName.getMusic());
        musicIntent.putExtra("name", uri);
        musicIntent.putExtra("musicName", musicName.getMusic());

        musicIntent.putExtra("list", mList);
        startActivity(musicIntent);

    }
}