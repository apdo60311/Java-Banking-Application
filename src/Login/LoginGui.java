package Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.EmptyBorder;

import Home.ProfileScreen.ProfileScreen;
import Register.RegisterGui;
import Shared.Components;
import Shared.Services;
import Shared.UserLoginCase;

public class LoginGui  {

    public JFrame frame = new JFrame();    
    public JFrame getFrame() {
        return frame;
    }

    private RegisterGui registerGui;
    private LoginGui loginGui = this;
    private ProfileScreen profileData;
    private RestPassword restPassword;

    // RestPassword Object in case user come from Rest password screen
    public LoginGui(RestPassword restPassword) {
        this.restPassword = restPassword;
    }

    // RegisterGui object in case user comes from register screen 
    public LoginGui(RegisterGui registerGui) {
        this.registerGui = registerGui;
    }

    // in case the application open for the first time
    public LoginGui() {
        this.profileData = null;
        this.registerGui = null;
    }

    // profileScreen object to get frame location in case user logged out
    public LoginGui(ProfileScreen profileData) {
        this.profileData = profileData;
    }

    public void CreateUi() {
        JPanel panel = new JPanel();    
        JPanel otherPanel = new JPanel(new GridBagLayout());
        
        // welcome Label
        JLabel welcomeTextLabel = new JLabel("Welcome back");
        welcomeText(welcomeTextLabel);
        
        // sub-text label
        JLabel subTextLabel = new JLabel("Enter Your credentials to access your account");
        subText(subTextLabel);

        // hint text
        JLabel hintTextLabel = new JLabel("");
        Components.hintTextLabel(hintTextLabel);        

        // forgot password label
        JLabel forgotTextLabel = new JLabel("Forgot Your Password?");
        questionText(forgotTextLabel);

        // rest password label
        JLabel restTextLabel = new JLabel("Rest Password");
        actionText(restTextLabel);
        restTextLabel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                RestPassword restPassword = new RestPassword(loginGui);
                restPassword.CreateUi();
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
        // don't have account label
        JLabel dontHaveAccountTextLabel = new JLabel("Don't have an account?");
        questionText(dontHaveAccountTextLabel);

        // register label
        JLabel registerTextLabel = new JLabel("Register");
        actionText(registerTextLabel);

        registerTextLabel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                RegisterGui registerGui = new RegisterGui(loginGui);
                registerGui.CreateUi();
                frame.setVisible(false);
            }
            
            @Override
            public void mouseEntered(MouseEvent arg0) {}
            
            @Override
            public void mouseExited(MouseEvent arg0) {}

            @Override
            public void mousePressed(MouseEvent arg0) {}

            @Override
            public void mouseReleased(MouseEvent arg0) {}
            
        });

        // username and password fields' font
        Font userDataFont = new Font("Arial", Font.PLAIN, 20);
        
        // username field
        JTextField usernameField = new JTextField();
        Components.inputField(userDataFont, usernameField ,"Enter Username");
        
        // password field
        JPasswordField passwordField = new JPasswordField();
        Components.passwordField(userDataFont, passwordField , "Enter password");

        // login button
        Font buttonFont = new Font("Arial", Font.PLAIN, 15);
        Components.RoundedButton button = new Components.RoundedButton("Login");
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
                String password = new String(passwordField.getPassword());
                if (username.equals("Enter Username") == false && password.equals("Enter Username") == false) {
                    UserLoginCase loginCase =  Services.userLoginCase(username, password);
                    switch (loginCase) {   
                        case LOGGEDIN:
                            System.out.println("Logged in");
                            hintTextLabel.setText("");
                            Services.userEnterance(loginGui);
                
                            break;
                        case UNREGISTERED_USER:
                            hintTextLabel.setText("Please Login first.");
                            break;
                        case INCORECT_PASSWORD:
                            hintTextLabel.setText("Incorrect Password.");
                            break;
                        case ERROR:
                            hintTextLabel.setText("Error Occured");
                            break;
    
                        default:
                            break;
                    }                        
                }else {
                    hintTextLabel.setText("Empty username or password");
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
        otherPanel.add(passwordField, otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 9;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 10;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 11;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanel.add(hintTextLabel , otherPanelGrid);
        otherPanel.add(new JLabel("<html><br><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 12;
        otherPanel.add(button, otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 13;        
        otherPanel.add(new JLabel("<html><br><br><br></html>") , otherPanelGrid);
        
        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(Color.white);
        registerPanel.add(dontHaveAccountTextLabel);
        registerPanel.add(registerTextLabel);
        otherPanel.add(registerPanel, otherPanelGrid);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.decode(Constants.Constants.backgroundColorCode));
        bottomPanel.add(forgotTextLabel);
        bottomPanel.add(restTextLabel);

        panel.add(otherPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.SOUTH;
        panel.add(bottomPanel , gbc);
        frame.add(panel);  
        frame.setSize(Constants.Constants.width,Constants.Constants.height);    
        frame.setLayout(null); 

        if(this.profileData != null){
            frame.setLocation(this.profileData.getFrame().getLocation());
        }

        if (this.registerGui != null) {
            frame.setLocation(this.registerGui.getFrame().getLocation());            
        }

        if (this.restPassword != null) {
            frame.setLocation(this.restPassword.getFrame().getLocation());            
        }
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


