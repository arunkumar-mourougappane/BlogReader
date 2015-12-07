package com.insanecircuits.insanecircuits;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Arunkumar on 12/6/2015.
 */
public class BlogPostsList implements Serializable{
    ArrayList<BlogPostModel> PostList=new ArrayList<BlogPostModel>();

    public ArrayList<BlogPostModel> getPostList() {
        return PostList;
    }

    public void setPostList(ArrayList<BlogPostModel> postList) {
        PostList = postList;
    }
}
