package src;

import java.util.ArrayList;

public abstract class Media {

    private String publication;

    private ArrayList<Media> rating;

    private String title;
    private ArrayList<Media> categories;

    public Media(String title, String publication)
    {
        this.title = title;
        this.publication = publication;
    }

    public String getPublication(){
        return publication;

    }

}
