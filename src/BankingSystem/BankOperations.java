package BankingSystem;

import java.util.Map;
import Constants.Constants;
import DBHandler.JsonHandler;

/*
 * BankOperations Class
 * class contains operations that can be beformed by the bank,
 * such as deposit, withdrawal, and transfer.
 */

public class BankOperations {
    private UserData user;

    public UserData getUser() {
        return user;
    }

    public BankOperations(UserData user) {
        this.user = user;
    }
    
    public String transfer(int id , int amount) {
        if (user != null) {
            int userBalance = user.getBalance();
            Map<String , Object> receiverUserData = DBHandler.JsonHandler.findByKey(Constants.usersJsonFilePath, "id", id);
                        
            Map<String , Object> senderUserData;

            if (receiverUserData != null) {
                if (userBalance >= amount) {
                    // update sender user balance
                    int userNewBalance = user.getBalance() - amount; 
                    user.setBalance(userNewBalance);
                    senderUserData  = Map.of("username", user.getUsername(), "fullname", user.getFullname(), "email", user.getEmail(), "password", user.getPassword(), "id", user.getId(), "balance", user.getBalance(), "nationality", user.getNationality(), "gender", user.getGender());
                    JsonHandler.writeToJsonWithReplacement(senderUserData, Constants.usersJsonFilePath);
                    JsonHandler.writeToJsonWithReplacement(senderUserData, Constants.currentUserJsonFilePath);
                    
                    // update receiver user balance
                    int recevierUserNewBalance = Integer.parseInt(receiverUserData.get("balance").toString()) + amount; 
                    receiverUserData.put("balance", recevierUserNewBalance);

                    // update receiver user data in users json file
                    JsonHandler.writeToJsonWithReplacement(receiverUserData, Constants.usersJsonFilePath);
                    return "Done";
                } else {
                    return "Insuficient Balance";
                }
    
            } else {
                return "user not found";
            }
        }
        else {
            return "User cannot be null";
        }
    }

    public String withdraw(int amount , String password) {
        if (password.equals(user.getPassword())) {
            if(amount <= user.getBalance()) {
                int userNewBalance = user.getBalance() - amount;
                user.setBalance(userNewBalance);
                return "Done";
            }else {
                return "Insuficient Balance!";
            }
        }
        else {
            return "Wrong password!";
        }
    }

    public String deposite(int amount) {
        int userNewBalance = user.getBalance() + amount;
        user.setBalance(userNewBalance);
        return "Done";
    }


}
