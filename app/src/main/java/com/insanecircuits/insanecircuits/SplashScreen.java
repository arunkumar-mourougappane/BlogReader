package com.insanecircuits.insanecircuits;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.http.StatusLine;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static android.text.Html.fromHtml;

public class SplashScreen extends AppCompatActivity{
    public static final String TAG = SplashScreen.class.getCanonicalName();
    protected static final String blogURL = "http://www.insanecircuits.com/api/get_recent_posts/?count=";
    protected int NUMBER_OF_POSTS = 10;
    protected JSONObject mBlogData;
    protected ImageView loopLogo;
    protected ObjectAnimator animFadein;
    protected ObjectAnimator animFadeOut;
    OkHttpClient client=null;
    private Request request=null;
    final AnimatorSet mAnimationSet = new AnimatorSet();
    static boolean JSONloaded=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        loopLogo = (ImageView) findViewById(R.id.imageView2);

        animFadeOut = ObjectAnimator.ofFloat(loopLogo, "alpha",  1f, 0f);
        animFadeOut.setDuration(2000);
        animFadein = ObjectAnimator.ofFloat(loopLogo, "alpha", 0f, 1f);
        animFadein.setDuration(2000);
        if(isNetworkAvailable()) {
            client=new OkHttpClient();
            request=new Request.Builder().url(blogURL + NUMBER_OF_POSTS).build();
            Call call=client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        if (response.isSuccessful()) {
                            Log.v(TAG, response.body().string());
                            JSONloaded=true;

                        }

                    } catch (IOException e) {
                        Log.e(TAG, "Exception Caught:", e);
                    }
                }
            });
            mAnimationSet.play(animFadein).after(animFadeOut);
            mAnimationSet.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if(!JSONloaded)
                             mAnimationSet.start();
                }

            });
            mAnimationSet.start();


        }
        else{
            Toast.makeText(this, R.string.networkErrorMessage,
                    Toast.LENGTH_LONG).show();
            UpdateDisplayforError();
            System.out.println("NO Network.");
        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return (networkInfo != null) && (networkInfo.isConnected());
    }


    private void UpdateDisplayforError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.errorMessage));
        builder.setMessage(getString(R.string.error_connection));
        builder.setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}
