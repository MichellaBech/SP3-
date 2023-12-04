package src;

import java.util.HashSet;

public class User {

    private String username;
    private String password;

    private HashSet<String> watchedMedia = new HashSet<String>();
    private HashSet<String> savedMedia = new HashSet<String>();

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public void addToWatchedMedia(String mediaFindInFile, User user)
    {
        FileIO io = new FileIO();
        watchedMedia.add(mediaFindInFile);
        io.watchedMediaToFile("watchedMedia.txt", user);
    }

    public void addToSavedMedia(String mediaFindInFile, User user)
    {
        FileIO io = new FileIO();
        savedMedia.add(mediaFindInFile);
        io.savedMediaToFile("savedMedia.txt", user);

    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public HashSet<String> getWatchedMedia(User user)
    {
        TextUI ui = new TextUI();
        // ui.displayMessage("Your saved Media List: ");
        //ui.displayMessage("" + watchedMedia);
        return watchedMedia;
    }

    public HashSet<String> getSavedMedia(User user)
    {
        TextUI ui = new TextUI();
        // ui.displayMessage("Your saved Media List: ");
        // ui.displayMessage("" + savedMedia);
        return savedMedia;
    }

    public String toString()
    {
        return "Username: " + getUsername() +  " " + "Password: " + getPassword();
    }

    public void printWatchedMedia(User user)
    {
        TextUI ui = new TextUI();
        ui.displayMessage("Your saved Media: " + watchedMedia);
    }

}

