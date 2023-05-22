package BankingSystem;

import java.util.Map;

/*
 * UserLogin class
 * Contains user login data 
 *
 * UserData
 * Contains user all data
 */

abstract class UserLogin {
    protected String username;
    protected String email;
    protected String password;
    
    public UserLogin(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

public class UserData extends UserLogin{
    private String fullname;
    private int id;
    private int Balance;
    private String nationality;
    private String gender;
    public UserData(String username, String email, String password, String fullname, int id, int Balance,
            String nationality, String gender) {
        super(username, email, password);
        this.fullname = fullname;
        this.id = id;
        this.Balance = Balance;
        this.nationality = nationality;
        this.gender = gender;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getBalance() {
        return Balance;
    }
    public void setBalance(int Balance) {
        this.Balance = Balance;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Map<String , Object> getAsMap() {
        Map<String , Object> userData = Map.of(
            "username", this.username,
            "fullname", this.fullname,
            "email", this.email,
            "nationality", this.nationality,
            "gender", this.gender,
            "id", this.id,
            "balance",this.Balance,
            "password" , this.password
            );
        return userData;
    }
}

