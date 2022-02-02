package ui;

import model.Portfolio;

import java.util.Scanner;

// Property Management Application

public class PropertyManagementApp {

// User Story #1 - As a user, I want to be able to add a new property to my portfolio.
// User Story #2 - As a user, I want to be able to remove an existing property from my portfolio.
// User Story #3 - As a user, I want to be able to view all properties in my portfolio.

    private Portfolio portfolio;
    private Scanner input;


    public PropertyManagementApp() {
        runApp();
    }

    private void runApp() {
        boolean keepRunning = true;
        String command = null;

        initialize();

        while (keepRunning) {


        }



    }

    private void initialize() {
        portfolio = new Portfolio();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }




}
