package src;

import java.util.ArrayList;

public class Menu {

    TextUI ui = new TextUI();
    FileIO io = new FileIO();
    ArrayList<User> login = new ArrayList<>();

    User user = new User("", "");


    public Menu() {
    }

    //Starts the program, where it reads the users answer, login, create account or something went wrong
    public void startMenu() {

        io.scanMedia("100bedstefilm.txt");
        io.scanMedia("100bedsteserier.txt");
        Boolean validInput = false;
        String password = "";
        String username = "";
        User user = new User(username, password);

        while (!validInput) {
            String input = String.valueOf(ui.getInput("Hello, welcome to CHILL! Do you want to create account or login?"));

            if (input.equalsIgnoreCase("create account")) {
                createLogin();
                validInput = true;
            } else if (input.equalsIgnoreCase("login")) {
                login(user);
                validInput = true;
            } else {
                ui.displayMessage("Something went wrong, please try again");
            }
        }
        io.readLoginFromFile("data.txt");
    }


    //Shows the mainMenu and save the users respond.
    public void mainMenu(User user) {
        String input = ui.getInput("Please select one of the following: " +
                "\n 1: Search for a specific movie" +
                "\n 2: Search for a specific series" +
                "\n 3: Choose category" +
                "\n 4: Movies you've seen" +
                "\n 5: Saved movies" +
                "\n 6: View all movies" +
                "\n 7: View all series" +
                "\n 8: Seasons and episodes");

        int inputInt = Integer.valueOf(input);
        switch (inputInt) {
            case 1:
                movieSearch("100bedstefilm.txt", user);
                break;
            case 2:
                seriesSearch("100bedsteserier.txt", user);
                break;
            case 3:
                chooseCategorie();
                break;
            case 4:
                user.getWatchedMedia(user);
                break;
            case 5:
                user.getSavedMedia(user);
                break;
            case 6:
                viewAllMovies(user);
                break;
            case 7:
                viewAllSeries(user);
                break;
            case 8:
                seriesSeasonsAndEpisodes(user);
                //case 9:
                //  io.publication("100bedstefilm.txt");
                // break;
            default:
                ui.displayMessage("Please write a number between 1-8");
        }
    }

    public void createLogin() {
        FileIO io = new FileIO();
        String inputUsername = ui.getInput("Please write a username: ");
        String inputPassword = ui.getInput("Please write a password: ");
        User user = new User(inputUsername, inputPassword);
        login.add(user);
        io.saveLogin(login);
        mainMenu(user);

    }

    public void login(User user)
    {
        FileIO io = new FileIO();
        String inputUsername = ui.getInput("Please write your username: ");
        String inputPassword = ui.getInput("Please write your password: ");
        try {
            boolean loggedIn = (io.readFile(inputUsername, inputPassword, "data.txt"));
            if(loggedIn)
            {
                ui.displayMessage("Welcome back!");
                mainMenu(user);
            } else {
                ui.displayMessage("Sorry! Something went wrong, please try again later.");
                //Handle the unsuccessful login
            }
        } catch(RuntimeException e) {
            // Log or handle the exception
            ui.displayMessage("Error occurred: " + e.getMessage());

        }
    }

    //Marwa
    public void movieSearch(String filepath, User user) {
        ArrayList<User> input = new ArrayList<>();
        ui.displayMessage("All movies: " + io.scanMedia("100bedstefilm.txt"));
        FileIO io = new FileIO();
        String inputSearch = ui.getInput("Search movie: ");
        ArrayList<String> searchingResults = io.searchMovies(inputSearch, filepath);

        if (searchingResults.size() == 0) {
            ui.displayMessage("no search results was found");
        } else {
            ui.displayMessage("search results: ");
            for (String results : searchingResults) {
                ui.displayMessage(results);
            }
        }
        String inputSelect = ui.getInput("Select movie: ");
        String movieFindInFile = String.valueOf(io.searchInFile("100bedstefilm.txt", inputSelect));
        ui.displayMessage("You choose: " + movieFindInFile);
        playOrSave(movieFindInFile, user);
    }

    public void seriesSearch(String filepath, User user) {
        FileIO io = new FileIO();
        String inputSearch = ui.getInput("Search series");
        ArrayList<String> searchingResults = io.searchMovies(inputSearch, filepath);

        if (searchingResults.size() == 0) {
            ui.displayMessage("no search results was found");
        } else {
            ui.displayMessage("search results: ");
            for (String results : searchingResults) {
                ui.displayMessage(results);
            }
        }
        String inputSelect = ui.getInput("Select series");
        playOrSave(inputSelect, user);
    }



    public void play(String mediaFindInFile, User user)
    {
        ui.displayMessage(mediaFindInFile + " is now playing");
        user.addToWatchedMedia(mediaFindInFile, user);

    }

    //Make a method call from FileIO
    public void chooseCategorie()
    {
        String input = ui.getInput("Choose a category. Drama or War.");
        ui.displayMessage(String.valueOf(io.searchInFile("100bedstefilm.txt", input)));
        ui.displayMessage(String.valueOf(io.searchInFile("100bedsteserier.txt", input)));
    }

    public void viewAllSeries(User user) {
        Boolean isItValid = false;
        ui.displayMessage("All series: " + io.scanMedia("100bedsteserier.txt"));
        String serieSave = ui.getInput("Write the name of the serie you want to save or play");
        String mediaFindInFile = String.valueOf(io.searchInFile("100bedsteserier.txt", serieSave));
        ui.displayMessage("You choose: " +  mediaFindInFile);
        playOrSave(mediaFindInFile, user);
    }

    public void viewAllMovies(User user) {

        Boolean isValid = false;
        ui.displayMessage("All movies: " + io.scanMedia("100bedstefilm.txt"));
        String movieSave = ui.getInput("Write the name of the movie you want to save or play");
        String movieFindInFile = String.valueOf(io.searchInFile("100bedstefilm.txt", movieSave));
        ui.displayMessage("You choose: " + movieFindInFile);
        playOrSave(movieFindInFile, user);
    }


    public void playOrSave(String mediaFindInFile, User user) {
        Boolean isValid = false;
        while(!isValid) {
            String choice = ui.getInput("Do you want to save or play?");
            if(choice.equals("save")) {
                user.addToSavedMedia(mediaFindInFile, user);
                ui.displayMessage("Your media has been saved.");
                isValid = true;
            } else if(choice.equals("play")) {
                play(mediaFindInFile, user);
                isValid = true;
            } else {
                ui.displayMessage("Please write save or play");
            } }
    }

    public void seriesSeasonsAndEpisodes(User user)
    {
        Series series = new Series();
        ui.displayMessage("All series: " + io.scanMedia("100bedsteserier.txt"));
        series.seasonsAndEpisodes();
        String input = ui.getInput("Do you want to go back to main menu? Y/N");
        if (input.equalsIgnoreCase("Y") ){
            mainMenu(user);
        } else {
            ui.displayMessage("We hope to see you soon again!");
        }
    }
}
