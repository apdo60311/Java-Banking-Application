package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.EmptyBorder;

import Home.ProfileScreen.ProfileScreen;
import Shared.Components;
import Shared.PasswordStrength;
import Shared.Services;
import Shared.Validation;


public class RestPassword {
    public JFrame frame = new JFrame();    
    public JFrame getFrame() {
        return frame;
    }

    private LoginGui loginGui;
    private RestPassword restPassword = this;
    private ProfileScreen profileData;

    // RegisterGui object in case user comes from register screen 
    public RestPassword(LoginGui loginGui) {
        this.loginGui = loginGui;
    }

    // in case the application open for the first time
    public RestPassword() {
        this.profileData = null;
        this.loginGui = null;
    }

    // profileScreen object to get frame location in case user logged out
    public RestPassword(ProfileScreen profileData) {
        this.profileData = profileData;
    }

    public void CreateUi() {
        JPanel panel = new JPanel();    
        JPanel otherPanel = new JPanel(new GridBagLayout());
        
        // welcome Label
        JLabel welcomeTextLabel = new JLabel("Rest Password");
        welcomeText(welcomeTextLabel);
        
        // sub-text label
        JLabel subTextLabel = new JLabel("Enter Your credentials to rest your password");
        subText(subTextLabel);

        // hint text
        JLabel hintTextLabel = new JLabel("");
        Components.hintTextLabel(hintTextLabel);        

        // forgot password label
        JLabel haveAccountTextLabel = new JLabel("have an account?");
        questionText(haveAccountTextLabel);

        // rest password label
        JLabel loginTextLabel = new JLabel("login");
        actionText(loginTextLabel); 
        loginTextLabel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                LoginGui loginGui = new LoginGui(restPassword);
                loginGui.CreateUi();
                frame.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
            
        });

        // username and password fields' font
        Font userDataFont = new Font("Arial", Font.PLAIN, 20);

        // username field
        JTextField usernameField = new JTextField();
        Components.inputField(userDataFont, usernameField ,"Enter Username");

        // id
        JTextField idField = new JTextField();
        Components.inputField(userDataFont, idField ,"Enter id");
        
        // new password field
        JPasswordField newPasswordField = new JPasswordField();
        Components.passwordField(userDataFont, newPasswordField , "Enter new password");

        // rest button
        Font buttonFont = new Font("Arial", Font.PLAIN, 15);
        Components.RoundedButton button = new Components.RoundedButton("Rest");
        button.setPreferredSize(new Dimension(350, 50));
        button.setBackground(Color.decode(Constants.Constants.buttonBackgroundColorCode));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(buttonFont);
        button.setBorder(new EmptyBorder(0,0,0,0));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Services.initUsers();
                String username = usernameField.getText().toString();
                String id = idField.getText().toString();
                String newPassword = new String(newPasswordField.getPassword());
                boolean isIdValid = Validation.validateId(id);
                boolean isUsernameValid = Validation.validateUsername(username);
                if (isIdValid && isUsernameValid) {
                    boolean passwordStrong = Validation.validatePassword(newPassword) == PasswordStrength.STRONG;
                    boolean passwordAverage = Validation.validatePassword(newPassword) == PasswordStrength.AVERAGE;
                    boolean passwordEmpty = Validation.validatePassword(newPassword) == PasswordStrength.EMPTY;
                    boolean passwordInvalid = Validation.validatePassword(newPassword) == PasswordStrength.INVALID;

                    if (passwordStrong || passwordAverage) {
                        boolean isIdAndUsernameValid = Services.checkUsernameWithId(username, newPassword, Integer.parseInt(id));
                        if(!isIdAndUsernameValid){
                            hintTextLabel.setText("Somthing went wrong.");
                        }
                        hintTextLabel.setText("");
                        Components.showMessageDialog("Password changed sucessfully", "message", "assets/images/checked.png");    
                    } else if (passwordEmpty) {
                        hintTextLabel.setText("You must Enter Password");
                    } else if (!passwordStrong || !passwordAverage) {
                        hintTextLabel.setText("Enter Strong password");
                    } else if (passwordInvalid) {
                        hintTextLabel.setText("Invalid password");
                    }
            

                }else {
                    hintTextLabel.setText("Invalid user id or password.");
                }
            }
        });


        panel.setBounds(0,0,1100,750);    
        panel.setBackground(Color.decode(Constants.Constants.backgroundColorCode));
        otherPanel.setPreferredSize(new Dimension(500, 400));
        otherPanel.setBackground(Color.white);

        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        

        GridBagConstraints otherPanelGrid = new GridBagConstraints();
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 0;
        otherPanel.add(welcomeTextLabel , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 1;
        otherPanel.add(subTextLabel , otherPanelGrid);

        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 2;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 3;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 4;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 6;
        otherPanel.add(usernameField, otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 7;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 8;
        otherPanel.add(idField, otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 9;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 10;
        otherPanel.add(newPasswordField, otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 11;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 12;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 13;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanel.add(hintTextLabel , otherPanelGrid);
        otherPanel.add(new JLabel("<html><br><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 14;
        otherPanel.add(button, otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 15;        
        otherPanel.add(new JLabel("<html><br><br><br></html>") , otherPanelGrid);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.decode(Constants.Constants.backgroundColorCode));
        bottomPanel.add(haveAccountTextLabel);
        bottomPanel.add(loginTextLabel);

        panel.add(otherPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.SOUTH;
        panel.add(bottomPanel , gbc);
        frame.add(panel);  
        frame.setSize(Constants.Constants.width,Constants.Constants.height);    
        frame.setLayout(new GridLayout()); 
        if(this.profileData != null){
            frame.setLocation(this.profileData.getFrame().getLocation());
        }
        if (this.loginGui != null) {
            frame.setLocation(this.loginGui.getFrame().getLocation());            
        }

        Components.handleMouseMovements(frame, panel);

        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setVisible(true);            
    }

    private void actionText(JLabel restTextLabel) {
        Font resTextFont = new Font("Arial", Font.PLAIN, 15);
        restTextLabel.setForeground(Color.decode("#2662FF"));
        restTextLabel.setFont(resTextFont);
        restTextLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void questionText(JLabel forgotTextLabel) {
        Font forgotTextFont = new Font("Arial", Font.PLAIN, 15);
        forgotTextLabel.setForeground(Color.gray);
        forgotTextLabel.setFont(forgotTextFont);
    }

    private void subText(JLabel subTextLabel) {
        questionText(subTextLabel);
        Insets subTextInsets = new Insets(15, 0, 0, 0);
        subTextLabel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(subTextInsets), subTextLabel.getBorder()));
    }

    private void welcomeText(JLabel welcomeTextLabel) {
        Font welcomeTextFont = new Font("Arial", Font.BOLD, 35);
        welcomeTextLabel.setFont(welcomeTextFont);
        Insets welcomeTextInsets = new Insets(15, 0, 0, 0);
        welcomeTextLabel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(welcomeTextInsets), welcomeTextLabel.getBorder()));
    }
}


