package com.insanecircuits.insanecircuits;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Arunkumar on 10/16/2015.
 */
public class  BlogPostModel implements Serializable{
    private String mPostTitle;
    private String mPostSummary;
    private String mAuthor;
    private String mPostfeaturedImage;
    private String mPostUrl;
    private Date mPostpublishdate;
    private String mPostContent;

    public String getPostContent() {
        return mPostContent;
    }

    public void setPostContent(String mPostContent) {
        this.mPostContent = mPostContent;
    }

    public String getPostTitle() {
        return mPostTitle;
    }

    public void setPostTitle(String mPostTitle) {
        this.mPostTitle = mPostTitle;
    }

    public String getPostSummary() {
        return mPostSummary;
    }

    public void setPostSummary(String mPostSummary) {
        this.mPostSummary = mPostSummary;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getPostfeaturedImage() {
        return mPostfeaturedImage;
    }

    public void setPostfeaturedImage(String mPostfeaturedImage) {
        this.mPostfeaturedImage = mPostfeaturedImage;
    }

    public String getPostUrl() {
        return mPostUrl;
    }

    public void setPostUrl(String mPostUrl) {
        this.mPostUrl = mPostUrl;
    }

    public Date getPostpublishdate() {
        return mPostpublishdate;
    }

    public void setPostpublishdate(Date mPostpublishdate) {
        this.mPostpublishdate = mPostpublishdate;
    }
}
