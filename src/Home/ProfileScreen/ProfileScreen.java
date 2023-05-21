package Home.ProfileScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BankingSystem.UserData;
import Home.StartScreen;
import Login.LoginGui;
import Shared.Components;
import Shared.Services;
import Shared.Components.*;
public class ProfileScreen {
    private JLabel usernameField;
    private JLabel fullNameField;
    private JLabel idField;
    private JLabel balanceField;
    private JLabel genderField;
    private JLabel nationalityField;
    private JFrame frame = new JFrame();
    
    private UserData userData;
    private StartScreen homeData;
    public ProfileScreen profileData = this;
    
    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public JFrame getFrame() {
        return frame;
    }

    public ProfileScreen(UserData userData , StartScreen homeData) {
        this.userData = userData;
        this.homeData = homeData;
    }

    public ProfileScreen(StartScreen homeData) {
        this.homeData = homeData;
        this.userData = homeData.getUserData();
    }


    public void createUi(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.Constants.width,Constants.Constants.height);    

        Font font = new Font("Arial", Font.BOLD, 25);

        // Create the JPanel and layout manager
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);
        // Add the username field
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Username: "), constraints);
        constraints.gridx = 1;
        usernameField = new JLabel();
        usernameField.setFont(font);
        usernameField.setText(userData.getUsername());
        panel.add(usernameField, constraints);
        // Add the full name field
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Full Name:"), constraints);
        constraints.gridx = 1;
        fullNameField = new JLabel();
        fullNameField.setFont(font);
        fullNameField.setText(userData.getFullname());
        panel.add(fullNameField, constraints);
        // Add the ID field
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("ID:"), constraints);
        constraints.gridx = 1;
        idField = new JLabel();
        idField.setFont(font);
        idField.setText(Integer.toString(userData.getId()));
        panel.add(idField, constraints); 
        // Add the balance field
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Balance:"), constraints);
        constraints.gridx = 1;
        balanceField = new JLabel();
        balanceField.setFont(font);
        balanceField.setText(Integer.toString(userData.getBalance()));
        panel.add(balanceField, constraints);
        // Add the gender field
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("Gender:"), constraints);
        constraints.gridx = 1;
        genderField = new JLabel();
        genderField.setFont(font);
        genderField.setText(userData.getGender());
        panel.add(genderField, constraints);
 
        // Add the nationality field
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(new JLabel("Nationality:"), constraints);
        constraints.gridx = 1;
        nationalityField = new JLabel();
        nationalityField.setFont(font);
        nationalityField.setText(userData.getNationality());
        panel.add(nationalityField, constraints);
        // Add the save button
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        Font buttonFont = new Font("Arial", Font.PLAIN, 15);
        JButton logoutButton = new RoundedButton("Logout");
        logoutButton.setPreferredSize(new Dimension(350, 50));
        logoutButton.setBackground(Color.decode(Constants.Constants.buttonBackgroundColorCode));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setFont(buttonFont);
        logoutButton.setBorder(new EmptyBorder(0,0,0,0));

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Services.logout();
                LoginGui loginGui = new LoginGui(profileData);
                loginGui.CreateUi();
                frame.setVisible(false);
            }
        });

        panel.add(logoutButton, constraints);
        
        JPanel newPanel = new JPanel(new BorderLayout());
        
        newPanel.add(panel,BorderLayout.CENTER);

        ImageIcon backIcon = new ImageIcon(new ImageIcon("assets/images/back-arrow.png").getImage().getScaledInstance(70, 70, Image.SCALE_FAST));
        JLabel backButton = new JLabel(backIcon);
        backButton.setHorizontalAlignment(SwingConstants.RIGHT);
        backButton.setVerticalAlignment(SwingConstants.BOTTOM);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                StartScreen home = new StartScreen(homeData.getBankOperations(), homeData.getUserData());
                home.getFrame().setLocation(frame.getLocation());
                home.createUi();
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


        newPanel.add(backButton, BorderLayout.SOUTH);
        Components.handleMouseMovements(frame, newPanel); // handle mouse movements
        newPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(newPanel);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setLocation(homeData.getFrame().getLocation());
        frame.setVisible(true);
    }
}

