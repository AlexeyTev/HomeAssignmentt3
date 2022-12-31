import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static RealEstate realEstate = new RealEstate();

    public static void main(String[] args) {
        runMainMenu();
    }

    private static void runMainMenu() { //O(1)
        int usersChoice = Constants.INVALID_TO_LOGIN;
        User loggedInUser;
        do {
            System.out.println("Chose an option \n1.Create new user \n2.Login \n3.Exit");
            usersChoice = scanner.nextInt();
        } while (usersChoice > 3 || usersChoice < 1);
        if (usersChoice == Constants.CREATE_NEW_USER) {
            realEstate.createUser();
            runMainMenu();
        }
        if (usersChoice == Constants.LOGIN) {
            loggedInUser = realEstate.login();
            if (loggedInUser == null) {
                System.out.println("User name or password are wrong");
                runMainMenu();
            } else {
                System.out.println("Welcome, " + loggedInUser.getUserName());
                userOptionsMenu(loggedInUser);
            }
        }
        if (usersChoice == Constants.EXIT) {
            System.out.println("Bye bye see you");
        }

    }

    private static void userOptionsMenu(User user) {//O(1)
        int chosenOption = Constants.NO_CHOICE;

        do {
            System.out.println("Chose an option: \n1.Publish new property \n2.Remove existing property\n3.See all the available properties \n" +
                    "4.See all your published properties\n5.Search by filter\n6.Log out and back to main menu");
            chosenOption = scanner.nextInt();
        } while (chosenOption > Constants.LOGOUT || chosenOption < Constants.PUBLISH_NEW_PROPERTY);
        if (chosenOption == Constants.PUBLISH_NEW_PROPERTY) {
            if (realEstate.publishNewProperty(user)) {
                System.out.println("Your property have been published successfully");
                goBackToOptionMenu(user);

            } else {
                System.out.println("We wasn't able to publish your property");
                userOptionsMenu(user);
            }
        }

        if (chosenOption == Constants.DELETE_EXISTING_PROPERTY) {
            realEstate.deleteProperty(user);
            goBackToOptionMenu(user);
        }
        if (chosenOption == Constants.SEE_ALL_PROPERTIES) {
            realEstate.printAllProperties();
            goBackToOptionMenu(user);
        }
        if (chosenOption == Constants.SEE_YOUR_PROPERTIES) {
            realEstate.printProperties(user);
            goBackToOptionMenu(user);
        }
        if (chosenOption == Constants.SEARCH_BY_FILTER) {
            Property[] searched = realEstate.search();
            if (searched != null) {
                for (int i = 0; i < searched.length; i++) {
                    System.out.println(i + 1 + ") \n" + searched[i]);
                }
            } else System.out.println("There is no properties published");
            goBackToOptionMenu(user);
        }
        if (chosenOption == Constants.LOGOUT) {
            runMainMenu();
        }

    }

    public static void goBackToOptionMenu(User user) {//O(1)
        int goBackOption;
        System.out.println("Back to the Option menu press "+Constants.GO_BACK_TO_OPTIONS);
        goBackOption = scanner.nextInt();
        if (goBackOption == Constants.GO_BACK_TO_OPTIONS) {
            userOptionsMenu(user);
        }
    }
}