import java.util.ArrayList;
import java.util.Map;

import BankingSystem.BankOperations;
import Constants.Constants;
import DBHandler.JsonHandler;
import Home.*;
import Login.LoginGui;
import Register.RegisterGui;
import Shared.Services;

public class App {
    public static void main(String[] args) throws Exception {

        LoginGui loginGui = new LoginGui();
        StartScreen homeGui = new StartScreen();
        RegisterGui registerGui = new RegisterGui();
        determineStartScreen(loginGui, homeGui, registerGui);

    }

    private static void determineStartScreen(LoginGui loginGui, StartScreen homeGui , RegisterGui registerGui) {
        ArrayList<Map<String , Object>> currentUserData = new ArrayList<>();
        JsonHandler.readFromJson(currentUserData,Constants.currentUserJsonFilePath);
        BankOperations bankOperations;    

        if (currentUserData.size() == 0) {
            loginGui.CreateUi();
        }else {
            System.out.println(currentUserData);

            bankOperations = new BankOperations(Services.intilUserData(currentUserData));
            homeGui.setBankOperations(bankOperations);
            homeGui.setUserData(Services.intilUserData(currentUserData));
            homeGui.createUi();
        }
    }

}
