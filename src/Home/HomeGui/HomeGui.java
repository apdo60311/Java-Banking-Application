package Home.HomeGui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import BankingSystem.BankOperations;
import BankingSystem.UserData;
import Home.StartScreen;
import Home.HomeGui.Deposite.DepositeScreen;
import Home.HomeGui.Withdraw.WithdrawScreen;
import Shared.Components;

public class HomeGui {
    
    private BankOperations bankOperations;
    private UserData userData;
    private HomeGui homeGui = this;
    private StartScreen homeScreen;
    private JFrame frame = new JFrame("Home");

    public JFrame getFrame() {
        return frame;
    }


    public HomeGui(BankOperations bankOperations, UserData userData) {
        this.bankOperations = bankOperations;
        this.userData = userData;
    }


    public HomeGui() {
    }


    public HomeGui(BankOperations bankOperations) {
        this.bankOperations = bankOperations;
        this.userData = bankOperations.getUser();
    }

    public HomeGui(StartScreen homeScreen) {
        this.homeScreen = homeScreen;
        this.bankOperations = homeScreen.getBankOperations();
        this.userData = homeScreen.getUserData();
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

        Font boxesTextFont = new Font("Arial", Font.PLAIN, 40);
        // create the first box
        JPanel box1 = new Components.RoundedPanel(15 , Color.decode("#2662FF"));
        box1.setPreferredSize(new Dimension(100, 100)); // set the size
        box1.setBorder(BorderFactory.createCompoundBorder()); // set the border radius
        box1.setLayout(new GridBagLayout());
        JLabel label1 = new JLabel("Withdraw");
        ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("assets/images/withdraw.png").getImage().getScaledInstance(150, 150, Image.SCALE_FAST));
        label1.setFont(boxesTextFont);
        label1.setForeground(Color.white);
        label1.setHorizontalAlignment(JLabel.CENTER);

        box1.add(label1);
        box1.add(Box.createHorizontalStrut(30));
        box1.add(new JLabel(imageIcon1));

        box1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                WithdrawScreen withdrawScreen = new WithdrawScreen(homeScreen);
                withdrawScreen.createUi();
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
        ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("assets/images/deposite.png").getImage().getScaledInstance(150, 150, Image.SCALE_FAST));
        JLabel label2 = new JLabel("Deposite");
        label2.setFont(boxesTextFont);
        label2.setForeground(Color.white);
        label2.setHorizontalAlignment(JLabel.CENTER);
        box2.add(label2);
        box2.add(Box.createHorizontalStrut(30));
        box2.add(new JLabel(imageIcon2));

        box2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        box2.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                DepositeScreen depositeScreen = new DepositeScreen(homeScreen);
                depositeScreen.createUi();
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
                StartScreen home = new StartScreen(homeGui.getBankOperations(), homeGui.getUserData());
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
        // create the third box
        Components.handleMouseMovements(frame, panel); // handle mouse movements
        

        JPanel outterPanel = new JPanel(new BorderLayout());
        outterPanel.setBackground(Color.white);
        outterPanel.add(panel , BorderLayout.CENTER);
        outterPanel.add(bottomPanel , BorderLayout.AFTER_LAST_LINE);
        frame.add(outterPanel);
        if(homeScreen != null) {
            frame.setLocation(homeScreen.getFrame().getLocation());
        }
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
