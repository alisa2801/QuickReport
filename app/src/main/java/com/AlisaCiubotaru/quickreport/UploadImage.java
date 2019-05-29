package com.AlisaCiubotaru.quickreport;

public class UploadImage {
    private String mName;
    private String mImageUrl;

    public  UploadImage(){

    }

    public  UploadImage(String name, String imageUrl){
        if(name.trim().equals("")){
            name = "No Name";
        }
        mName = name;
        mImageUrl = imageUrl;
    }

    public String getmName(){
        return mName;
    }

    public void setmName(String name) {
        mName = name;
    }

    public String getmImageUrl(){
        return mImageUrl;
    }

    public void setmImageUrl(String imageUrl){
        mImageUrl = imageUrl;
    }
}
