package Shared;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import Constants.Constants;
import BankingSystem.BankOperations;
import BankingSystem.UserData;
import DBHandler.JsonHandler;
import Home.StartScreen;
import Login.LoginGui;

public class Services {


    public static void registerUser(Map<String , Object> userData) {
        JsonHandler.writeToJson(userData, Constants.usersJsonFilePath);
    }
    
    public static int generateRandomID() {
        Random random = new Random();
        int max = Integer.MAX_VALUE;
        int min = 100000;
        int randomInt = random.nextInt(max - min + 1) + min;
        return randomInt;
    }



    public static ArrayList<Map<String,Object>> users = new ArrayList<>();
    
    public static void initUsers() {
        JsonHandler.readFromJson(users, Constants.usersJsonFilePath);
    }

    static boolean isFound = false;
    public static UserLoginCase userLoginCase(String username , String password) {
        for (Map<String,Object> user : users) {
            if(user.get("username").equals(username)) {
                isFound = true;
                if (user.get("password").equals(password)) {
                    login(user);
                    return UserLoginCase.LOGGEDIN;
                }else {
                    return UserLoginCase.INCORECT_PASSWORD;
                }
            }   
        }
        if (!isFound) {
            return UserLoginCase.UNREGISTERED_USER;
        }        
        return UserLoginCase.ERROR; 
    }
    public static void login(Map<String , Object> from) {
        DBHandler.JsonHandler.writeToJson(from, Constants.currentUserJsonFilePath);
    }

    public static UserData intilUserData( ArrayList<Map<String , Object>> currentUserData) {
        UserData userData;
        String username = currentUserData.get(0).get("username").toString();
        String email = currentUserData.get(0).get("email").toString();
        String fullname = currentUserData.get(0).get("fullname").toString();
        String password = currentUserData.get(0).get("password").toString();
        String nationality = currentUserData.get(0).get("nationality").toString();
        String gender = currentUserData.get(0).get("gender").toString();
        int id = Integer.parseInt(currentUserData.get(0).get("id").toString());
        int balance = Integer.parseInt(currentUserData.get(0).get("balance").toString());

        userData = new UserData(username, email, password, fullname, id, balance, nationality, gender);
        return userData;
    }

    public static void userEnterance(LoginGui loginData) {
        ArrayList<Map<String , Object>> currentUserData = new ArrayList<>();
        JsonHandler.readFromJson(currentUserData,Constants.currentUserJsonFilePath);
        BankOperations bankOperations;    
        bankOperations = new BankOperations(Services.intilUserData(currentUserData));
        StartScreen homeScreen = new StartScreen(bankOperations);
        homeScreen.setBankOperations(bankOperations);
        homeScreen.setUserData(Services.intilUserData(currentUserData));
        homeScreen.getFrame().setLocation(loginData.getFrame().getLocation());
        homeScreen.createUi();
        loginData.getFrame().setVisible(false);
    }  

    public static void restUserPassword(String newPassword , Map<String , Object> newUserData) {
        newUserData.put("password", newPassword);        
        JsonHandler.writeToJsonWithReplacement(newUserData, Constants.usersJsonFilePath);
    }


    public static boolean checkUsernameWithId(String username , String password ,int id) {
        for (Map<String,Object> user : users) {
            if(user.get("username").equals(username)) {
                isFound = true;
                if (user.get("id").toString().equals(Integer.toString(id))) {
                    restUserPassword(password ,user);
                    return true;
                }else {
                    return false;
                }
            }   
        }
        if (!isFound) {
            return false;
        }        
        return false; 
    }


    // read logged in user data
    public static void readCurrentUserData(ArrayList<Map<String , Object>> data) {
        JsonHandler.readFromJson(data, Constants.currentUserJsonFilePath);
        for (Map<String,Object> map : data) {
            System.out.println(map);
        }
    }

    public static String transfer(BankOperations bank, int receiverId , int amount) {
        return bank.transfer(receiverId, amount);
    }


    // used to log user out 
    public static void logout() {
        // empty current user data
        JsonHandler.emptyJson(Constants.currentUserJsonFilePath);
    }
    
}


enum RestPasswordCase {

}