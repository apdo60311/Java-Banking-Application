package Home;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import BankingSystem.BankOperations;
import BankingSystem.UserData;
import Home.HomeGui.HomeGui;
import Home.ProfileScreen.ProfileScreen;
import Home.SettingScreen.SettingScreen;
import Home.TransferScreen.TransferScreen;
import Shared.Components;


public class StartScreen {
    private BankOperations bankOperations;
    private UserData userData;
    private StartScreen homeScreen = this;
    private JFrame frame = new JFrame("Home");

    public JFrame getFrame() {
        return frame;
    }



    public StartScreen(BankOperations bankOperations, UserData userData) {
        this.bankOperations = bankOperations;
        this.userData = userData;
    }

    // default constructor
    public StartScreen() {}
    
    public StartScreen(BankOperations bankOperations) {
        this.bankOperations = bankOperations;
    }

    public void createUi() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.Constants.width,Constants.Constants.height);    
        frame.getContentPane().setBackground(Color.WHITE);

        GridLayout gridLayout = new GridLayout(2, 2);
        gridLayout.setHgap(15);
        gridLayout.setVgap(15);

        JPanel panel = new JPanel(gridLayout);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // add padding to the panel
        
        // All boxes' font
        Font boxesTextFont = new Font("Arial", Font.PLAIN, 25);

        // create the first box
        JPanel box1 = new Components.RoundedPanel(15 , Color.decode("#2662FF"));
        box1.setPreferredSize(new Dimension(100, 100)); // set the size
        box1.setBorder(BorderFactory.createCompoundBorder()); // set the border radius
        box1.setLayout(new GridBagLayout());
        JLabel label1 = new JLabel("Profile");
        ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("assets/images/me.png").getImage().getScaledInstance(150, 150, Image.SCALE_FAST));
        label1.setFont(boxesTextFont);
        label1.setForeground(Color.white);
        label1.setHorizontalAlignment(JLabel.CENTER);
        box1.add(label1);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(new JLabel(imageIcon1));
        box1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box1.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                ProfileScreen profileScreen = new ProfileScreen(userData , homeScreen);
                profileScreen.createUi();
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

        panel.add(box1);

        // create the second box
        JPanel box2 = new Components.RoundedPanel(15 , Color.decode("#2662FF"));
        box2.setPreferredSize(new Dimension(100, 100));
        box2.setBorder(BorderFactory.createCompoundBorder());
        box2.setLayout(new GridBagLayout());
        ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("assets/images/transfer.png").getImage().getScaledInstance(150, 150, Image.SCALE_FAST));
        JLabel label2 = new JLabel("Transfer");
        label2.setFont(boxesTextFont);
        label2.setForeground(Color.white);
        label2.setHorizontalAlignment(JLabel.CENTER);
        box2.add(label2);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(new JLabel(imageIcon2));

        box2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box2.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                TransferScreen transferScreen = new TransferScreen(homeScreen);
                transferScreen.createUi();
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


        panel.add(box2);

        // create the third box
        JPanel box3 = new Components.RoundedPanel(15 , Color.decode("#2662FF"));
        box3.setPreferredSize(new Dimension(100, 100));
        box3.setBorder(BorderFactory.createCompoundBorder());
        box3.setLayout(new GridBagLayout());
        
        JLabel label3 = new JLabel("Home");
        ImageIcon imageIcon3 = new ImageIcon(new ImageIcon("assets/images/home.png").getImage().getScaledInstance(150, 150, Image.SCALE_FAST));
        

        label3.setFont(boxesTextFont);
        label3.setForeground(Color.white);
        label3.setHorizontalAlignment(JLabel.CENTER);
        box3.add(label3);
        box3.add(Box.createHorizontalStrut(20));
        box3.add(new JLabel(imageIcon3));

        box3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box3.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                HomeGui homeGui = new HomeGui(homeScreen);
                homeGui.createUi();
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


        panel.add(box3);

        // create the fourth box
        JPanel box4 = new Components.RoundedPanel(15 , Color.decode("#2662FF"));
        box4.setPreferredSize(new Dimension(100, 100));
        box4.setBorder(BorderFactory.createCompoundBorder());
        box4.setLayout(new GridBagLayout());
        ImageIcon imageIcon4 = new ImageIcon(new ImageIcon("assets/images/setting .png").getImage().getScaledInstance(150, 150, Image.SCALE_FAST));
        JLabel label4 = new JLabel("Settings");

        label4.setFont(boxesTextFont);
        label4.setForeground(Color.white);
        label4.setHorizontalAlignment(JLabel.CENTER);
        
        box4.add(label4);
        box4.add(Box.createHorizontalStrut(20));
        box4.add(new JLabel(imageIcon4));

        box4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box4.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                SettingScreen settingScreen = new SettingScreen(homeScreen);
                settingScreen.createUi();
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


        panel.add(box4);
        Components.handleMouseMovements(frame, panel); // handle mouse movements
        frame.add(panel);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }


    public BankOperations getBankOperations() {
        return bankOperations;
    }


    public void setBankOperations(BankOperations bankOperations) {
        this.bankOperations = bankOperations;
    }


    public UserData getUserData() {
        return userData;
    }


    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}


