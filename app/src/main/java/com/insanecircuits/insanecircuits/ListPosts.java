package com.insanecircuits.insanecircuits;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import static android.text.Html.fromHtml;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ListPosts extends AppCompatActivity {
    protected JSONObject mBlogData=null;
    public static final String TAG = SplashScreen.class.getCanonicalName();
    public static Integer NUMBER_OF_POSTS;
    protected populateListing mListing;
    protected BlogPostModel mPost;
    private final String KEY_TITLE = "title";
    private final String KEY_AUTHOR = "author";
    JSONArray blogPosts;
    private BlogPostsList PostList=new BlogPostsList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_posts);
        try {
            mBlogData= new JSONObject(getIntent().getStringExtra("mBlogData"));
            NUMBER_OF_POSTS= getIntent().getIntExtra("postCount",10);
            mListing=new populateListing();
            mListing.execute(mBlogData);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v(TAG, mBlogData.toString());
    }
    private class populateListing extends AsyncTask<Object,Void,JSONObject> {
        protected JSONObject doInBackground(Object... params) {
            int postCount=0;
            try {
                blogPosts = mBlogData.getJSONArray("posts");
                String featureImageUrl;
                JSONObject featuredImage=null;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for(postCount=0; postCount< NUMBER_OF_POSTS; postCount++)
                {
                    JSONObject post = blogPosts.getJSONObject(postCount);
                    mPost = new BlogPostModel();
                    mPost.setPostTitle(fromHtml(post.getString(KEY_TITLE)).toString());
                    mPost.setAuthor(fromHtml(post.getString(KEY_AUTHOR)).toString());
                    JSONArray featuredImageArray=post.getJSONArray("attachments");
                    int featuredImageIndex=featuredImageArray.length();
                    if(featuredImageIndex!=0) {
                        featuredImage = featuredImageArray.getJSONObject(featuredImageIndex-1);
                        mPost.setPostfeaturedImage(fromHtml(featuredImage.getString("url")).toString());
                    }
                    else{
                        mPost.setPostfeaturedImage("http://www.insanecircuits.com/wp-content/uploads/2015/12/ic_launcher-2.png");
                    }
                    mPost.setPostfeaturedImage(fromHtml(post.getString(KEY_TITLE)).toString());
                    mPost.setPostpublishdate(formatter.parse(post.getString("date")));
                    mPost.setPostUrl(fromHtml(post.getString("url")).toString());
                    mPost.setPostSummary(fromHtml(post.getString("excerpt")).toString());
                    mPost.setPostContent(post.getString("content"));
                    Log.v(TAG, String.format("Title: %s", mPost.getPostTitle()));
                    Log.v(TAG, String.format("Content: %s", mPost.getPostContent()));
                    PostList.getPostList().add(mPost);
                }

            }
            catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return mBlogData;
        }

        protected void onPostExecute(JSONObject mBlogData) {
            Log.v(TAG,"All Posts gathered.");
        }
    }
}
