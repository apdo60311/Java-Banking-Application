package Home.SettingScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BankingSystem.UserData;
import DBHandler.JsonHandler;
import Home.StartScreen;
import Shared.*;


public class SettingScreen {
    private JTextField usernameField;
    private JTextField fullNameField;
    private JTextField idField;
    private JTextField balanceField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> nationalityField;
 
    private JFrame frame = new JFrame();
    
    private UserData userData;
    private StartScreen homeData;
    public SettingScreen settingScreen = this;
    
    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public JFrame getFrame() {
        return frame;
    }

    public SettingScreen(UserData userData , StartScreen homeData) {
        this.userData = userData;
        this.homeData = homeData;
    }

    public SettingScreen(StartScreen homeData) {
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
        usernameField = new JTextField();
        usernameField.setFont(font);
        usernameField.setText(userData.getUsername());
        panel.add(usernameField, constraints);
        // Add the full name field
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Full Name:"), constraints);
        constraints.gridx = 1;
        fullNameField = new JTextField();
        fullNameField.setFont(font);
        fullNameField.setText(userData.getFullname());
        panel.add(fullNameField, constraints);
        // Add the ID field
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("ID:"), constraints);
        constraints.gridx = 1;
        idField = new JTextField();
        idField.setFont(font);
        idField.setText(Integer.toString(userData.getId()));
        idField.setEnabled(false);
        panel.add(idField, constraints); 
        // Add the balance field
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Balance:"), constraints);
        constraints.gridx = 1;
        balanceField = new JTextField();
        balanceField.setFont(font);
        balanceField.setText(Integer.toString(userData.getBalance()));
        balanceField.setEnabled(false);
        panel.add(balanceField, constraints);
        // Add the gender field
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(new JLabel("Gender:"), constraints);
        constraints.gridx = 1;
        genderComboBox = new JComboBox<String>(new String[]{"Male" , "Female"});
        genderComboBox.setFont(font);
        genderComboBox.setSelectedItem(userData.getGender());
        panel.add(genderComboBox, constraints);
 
        // Add the nationality field
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(new JLabel("Nationality:"), constraints);
        constraints.gridx = 1;
        nationalityField = new JComboBox<String>(Constants.Constants.countries);
        nationalityField.setFont(font);
        nationalityField.setSelectedItem(userData.getNationality());
        panel.add(nationalityField, constraints);
        // Add the save button
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        Font buttonFont = new Font("Arial", Font.PLAIN, 15);
        JButton saveButton = new Components.RoundedButton("Save");
        saveButton.setPreferredSize(new Dimension(350, 50));
        saveButton.setBackground(Color.decode(Constants.Constants.buttonBackgroundColorCode));
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveButton.setFont(buttonFont);
        saveButton.setBorder(new EmptyBorder(0,0,0,0));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String newUsername = usernameField.getText().toString();
                String newFullname = fullNameField.getText().toString();
                String newGender = genderComboBox.getSelectedItem().toString();
                String newNationality = nationalityField.getSelectedItem().toString();
                
                userData.setUsername(newUsername);
                userData.setFullname(newFullname);
                userData.setGender(newGender);
                userData.setNationality(newNationality);

                JsonHandler.writeToJsonWithReplacement(userData.getAsMap(), Constants.Constants.usersJsonFilePath);
                JsonHandler.writeToJsonWithReplacement(userData.getAsMap(), Constants.Constants.currentUserJsonFilePath);

                Components.showMessageDialog("Information changed Successfully", "message", "assets/images/checked.png");
            
            }
        });

        panel.add(saveButton, constraints);
        
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
