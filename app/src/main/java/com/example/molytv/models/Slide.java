package com.example.molytv.models;

public class Slide {

    private int Image ;
    private String Title;
    private String Link;
    // Add more field depand on whay you wa&nt ...

    public Slide(int image, String title,String link) {
        Image = image;
        Title = title;
        Link = link;
    }


    public int getImage() {
        return Image;
    }

    public String getTitle() {
        return Title;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
