package com.insanecircuits.insanecircuits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ListPosts extends AppCompatActivity {
    protected JSONObject mBlogData=null;
    public static final String TAG = SplashScreen.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_posts);
        try {
            mBlogData= new JSONObject(getIntent().getStringExtra("mBlogData"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v(TAG,mBlogData.toString());
    }
}
