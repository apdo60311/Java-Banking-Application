package Home.HomeGui.Withdraw;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BankingSystem.BankOperations;
import DBHandler.JsonHandler;
import Home.StartScreen;
import Home.HomeGui.HomeGui;
import Shared.Components;
import Shared.Services;
import Shared.Validation;

/**
 * WithdrawScreen
 */
public class WithdrawScreen {

    private ArrayList<Map<String , Object>> data = new ArrayList<>();
    private BankOperations bankOperations;
    private StartScreen homeData;


    public WithdrawScreen(BankOperations bankOperations, StartScreen homeData) {
        this.bankOperations = bankOperations;
        this.homeData = homeData;
    }

    public void setBankOperations(BankOperations bankOperations) {
        this.bankOperations = bankOperations;
    }

    public WithdrawScreen() {
    }

    public WithdrawScreen(StartScreen homeData){
        this.homeData = homeData;
        this.bankOperations = homeData.getBankOperations();
    }
    JFrame frame = new JFrame("Transfer Screen");

    public JFrame getFrame() {
        return frame;
    }

    public void createUi(){
        Services.readCurrentUserData(data);
        Map<String , Object> userData = data.get(0);
        // Create a new JFrame with a title

        // Set the default close operation and size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.Constants.width,Constants.Constants.height);    
        frame.setUndecorated(true);
        
        Font topPanelFont = new Font("Arial", Font.BOLD, 25);

        // Create a new JPanel to hold the labels
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(Box.createVerticalStrut(15) , BorderLayout.NORTH);
        // Create a JLabel with a name and add it to the top left of the panel
        JLabel nameLabel = new JLabel(" Welcome " + userData.get("fullname") + "!");
        nameLabel.setFont(topPanelFont);
        nameLabel.setForeground(Color.white);
        panel.add(nameLabel, BorderLayout.LINE_START);

        Components.handleMouseMovements(frame, panel);

        JLabel balanceLabel = new JLabel("Balance: $" + userData.get("balance") + " ");
        balanceLabel.setFont(topPanelFont);
        balanceLabel.setForeground(Color.white);
        panel.add(balanceLabel, BorderLayout.LINE_END);
        panel.add(Box.createVerticalStrut(15) , BorderLayout.SOUTH);
        panel.setBackground(Color.decode("#2662FF"));

        JPanel innerPanel = new JPanel(new GridBagLayout());
        innerPanel.setBackground(Color.white);

        JLabel transferTextLabel = new JLabel("Deposite Money");
        Font welcomeTextFont = new Font("Arial", Font.BOLD, 35);
        transferTextLabel.setFont(welcomeTextFont);
        Insets welcomeTextInsets = new Insets(15, 0, 0, 0);
        transferTextLabel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(welcomeTextInsets), transferTextLabel.getBorder()));
    
        Font transferDataFont = new Font("Arial", Font.PLAIN, 20);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setText("Enter password");
        passwordField.setForeground(Color.decode("#A1B3D2"));
        passwordField.setFont(transferDataFont);
        passwordField.setColumns(17);
        passwordField.setEchoChar((char)0);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Enter password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('\u2022');
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("Enter password");
                    passwordField.setEchoChar((char)0);
                    passwordField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });

        Font amountDataFont = new Font("Arial", Font.PLAIN, 20);
        JTextField amountField = new JTextField();
        amountField.setBorder(BorderFactory.createEmptyBorder());
        amountField.setText("Enter amount");
        amountField.setForeground(Color.decode("#A1B3D2"));
        amountField.setFont(amountDataFont);
        amountField.setColumns(17);
        amountField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (amountField.getText().equals("Enter amount")) {
                    amountField.setText("");
                    amountField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (amountField.getText().isEmpty()) {
                    amountField.setText("Enter amount");
                    amountField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
        
        JLabel hintTextLabel = new JLabel("");
        Font hinTextFont = new Font("Arial", Font.PLAIN, 12);
        hintTextLabel.setForeground(Color.red);
        hintTextLabel.setFont(hinTextFont);        

        Font buttonFont = new Font("Arial", Font.PLAIN, 15);
        Components.RoundedButton button = new Components.RoundedButton("Withdraw");
        button.setPreferredSize(new Dimension(350, 50));
        button.setBackground(Color.decode("#2662FF"));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(buttonFont);
        button.setBorder(new EmptyBorder(0,0,0,0));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String password = new String(passwordField.getPassword());
                boolean isAmountValid = Validation.validateAmount(amountField.getText());
                // boolean isPasswordValid = bankOperations.getUser().getPassword()
                //         .equals(password);
                if (isAmountValid) {
                    int amount = Integer.parseInt(amountField.getText().toString());
                    String result = bankOperations.withdraw(amount, password);
                    if(result.equals("Done")) {
                        Map<String , Object> newUserData = bankOperations.getUser().getAsMap();
                        JsonHandler.writeToJsonWithReplacement(newUserData, Constants.Constants.usersJsonFilePath);
                        JsonHandler.writeToJsonWithReplacement(newUserData, Constants.Constants.currentUserJsonFilePath);
                        hintTextLabel.setText("");
                        Components.showMessageDialog("Congratulations! Your money has been successfully withdrawn.",
                        "message", "assets/images/checked.png");
                        homeData.setBankOperations(bankOperations);
                        homeData.setUserData(bankOperations.getUser());    
                        frame.repaint();

                    } else {    
                        hintTextLabel.setText(result);
                    }
                } else {
                    hintTextLabel.setText("Insufficient amount!");
                }   


            }            
        });


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        innerPanel.add(transferTextLabel , gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        innerPanel.add(new JLabel("<html><br><br></html>") , gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        innerPanel.add(amountField , gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        innerPanel.add(new JLabel("<html><br></html>") , gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        innerPanel.add(passwordField , gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        innerPanel.add(new JLabel("<html><br></html>") , gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        innerPanel.add(hintTextLabel , gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        innerPanel.add(new JLabel("<html><br></html>") , gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        innerPanel.add(button , gbc);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.white);
        ImageIcon backIcon = new ImageIcon(new ImageIcon("assets/images/back-arrow.png").getImage().getScaledInstance(70, 70, Image.SCALE_FAST));
        JLabel backButton = new JLabel(backIcon);
        backButton.setHorizontalAlignment(SwingConstants.RIGHT);
        backButton.setVerticalAlignment(SwingConstants.BOTTOM);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addMouseListener(new MouseListener() {
        
            @Override
            public void mouseClicked(MouseEvent arg0) {
                HomeGui home = new HomeGui(homeData);
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


        bottomPanel.add(backButton);

        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 20));

        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(innerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        if (homeData != null) {
            frame.setLocation(homeData.getFrame().getLocation());
        }
        frame.setVisible(true);
    }

}