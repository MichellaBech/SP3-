package src;

import java.util.Scanner;

public class TextUI {

    private Scanner scan = new Scanner(System.in);

    //shows a message and returns the user's input as a String
    public String getInput(String msg){
        this.displayMessage(msg);
        return scan.nextLine();
    }

    public void displayMessage(String msg){

        System.out.println(msg);

    }

    //Method for the userInput in the movieSearch method in Menu class
    public int getIntInput(String msg) {
        int userInput=0;
        boolean isValidInput=false;

        while(!isValidInput) {
            try{
                System.out.print(msg);
                userInput = Integer.parseInt(scan.nextLine());
                isValidInput = true;
            }catch (NumberFormatException e){
                System.out.println("Invalid input.Please enter a valid number.");
            }

        }
        return userInput;

    }
    /*
    public String getChoice(ArrayList<String> options, String msg){
        System.out.println(msg);
        //  displayMenu(options);
        String input = getInput("");

        //tjek om input findes i listen, hvis ikke, smid en exception
        if(!options.contains(input)){
            System.out.println("findes ikke på listen");
            input = getChoice(options, msg);
        }

        return input;
    } */
}
