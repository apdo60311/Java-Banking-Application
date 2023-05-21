package Register;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import javax.swing.border.EmptyBorder;
import Login.LoginGui;
import Shared.Components;
import Shared.PasswordStrength;
import Shared.Services;
import Shared.Validation;

public class RegisterGui  {

    private JFrame frame = new JFrame(); // main frame
    private RegisterGui registerGui = this; // get instance of this class
    private LoginGui loginGui;

    public RegisterGui(LoginGui loginGui) {
        this.loginGui = loginGui;
    }

    public JFrame getFrame() {
        return frame;
    }

    public RegisterGui() {
        this.loginGui = null;
    }

    public void CreateUi() {
        
        JPanel panel = new JPanel(); // outter panel 
        JPanel otherPanel = new JPanel(new GridBagLayout()); // inner panel
        JPanel bottomPanel = new JPanel(); // bottom panel
    
        // welcome Label
        JLabel welcomeTextLabel = new JLabel("Register");
        welcomeLabel(welcomeTextLabel);
        
        // sub-text label
        JLabel subTextLabel = new JLabel("Enter Your data to create an account");
        subTextLabel(subTextLabel);

        // hint text label
        JLabel hintTextLabel = new JLabel("");
        Components.hintTextLabel(hintTextLabel);        

        // forgot password label
        JLabel registeredTextLabel = new JLabel("Registered before?");
        forgetPasswordLabel(registeredTextLabel);

        // login now label
        JLabel loginTextLabel = new JLabel("Login now");
        loginNowLabel(loginTextLabel);

        // username , fullname and password fields
        Font userDataFont = new Font("Arial", Font.PLAIN, 20);
        
        // username text field
        JTextField usernameField = new JTextField();
        usernameTextField(usernameField, userDataFont);

        // fullname text field
        JTextField fullnameField = new JTextField();
        fullnameTextField(userDataFont, fullnameField);
        
        // email text field
        JTextField emailField = new JTextField();
        emailTextField(userDataFont, emailField);

        // countries list
        JComboBox<String> countryComboBox = new JComboBox<>(Constants.Constants.countries);
        comboBoxOptions(countryComboBox);

        // gender options
        JComboBox<String> genderComboBox = new JComboBox<>(Constants.Constants.gender);
        comboBoxOptions(genderComboBox);

        // password field
        JPasswordField passwordField = new JPasswordField();
        passwordTextField(userDataFont, passwordField);

        // register button
        RoundedButton button = new RoundedButton("Register");
        registerButton(hintTextLabel, usernameField, fullnameField, emailField, countryComboBox, genderComboBox,
                passwordField, button);


        panel.setBounds(0,0,1100,750);
        panel.setBackground(Color.decode(Constants.Constants.backgroundColorCode));
        otherPanel.setPreferredSize(new Dimension(650, 600));
        otherPanel.setBackground(Color.white);

        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        

        GridBagConstraints otherPanelGrid = new GridBagConstraints();
        
        // position fields in the inner panel
        positionObjectsOnScreen(otherPanel, welcomeTextLabel, subTextLabel, hintTextLabel, usernameField, fullnameField, emailField,
                countryComboBox, genderComboBox, passwordField, button, otherPanelGrid);
        
        bottomPanel.setBackground(Color.decode(Constants.Constants.backgroundColorCode));
        bottomPanel.add(registeredTextLabel);
        bottomPanel.add(loginTextLabel);

        // make panel movable with mouse cursor
        Components.handleMouseMovements(frame, panel);

        panel.add(otherPanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.SOUTH;

        panel.add(bottomPanel , gbc);

        frame.add(new TextField("Register", 0));
        frame.setLayout(new BorderLayout());    
        frame.add(panel , BorderLayout.CENTER);  
        frame.setSize(Constants.Constants.width,Constants.Constants.height);    
        if (loginGui != null) {
            frame.setLocation(this.loginGui.getFrame().getLocation());            
        }
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setVisible(true);            
    }

    private void positionObjectsOnScreen(JPanel otherPanel, JLabel welcomeTextLabel, JLabel subTextLabel, JLabel hintTextLabel,
            JTextField usernameField, JTextField fullnameField, JTextField emailField,
            JComboBox<String> countryComboBox, JComboBox<String> genderComboBox, JPasswordField passwordField,
            RoundedButton button, GridBagConstraints otherPanelGrid) {
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
        otherPanel.add(new JLabel("<html><br><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 8;
        otherPanel.add(fullnameField, otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 9;
        otherPanel.add(new JLabel("<html><br><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 10;
        otherPanel.add(emailField, otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 11;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 12;
        otherPanel.add(passwordField, otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 12;
        otherPanel.add(new JLabel("<html><br><br><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 13;
        otherPanel.add(countryComboBox, otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 14;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 15;
        otherPanel.add(genderComboBox, otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 16;
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 17;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 18;
        otherPanel.add(new JLabel("<html><br></html>") , otherPanelGrid);
        otherPanel.add(hintTextLabel , otherPanelGrid);
        otherPanel.add(new JLabel("<html><br><br></html>") , otherPanelGrid);
        otherPanelGrid.gridx = 0;
        otherPanelGrid.gridy = 19;
        otherPanel.add(button, otherPanelGrid);
    }

    private void registerButton(JLabel hintTextLabel, JTextField usernameField, JTextField fullnameField,
            JTextField emailField, JComboBox<String> countryComboBox, JComboBox<String> genderComboBox,
            JPasswordField passwordField, RoundedButton button) {
        Font buttonFont = new Font("Arial", Font.PLAIN, 15);
        button.setPreferredSize(new Dimension(350, 50));
        button.setBackground(Color.decode(Constants.Constants.buttonBackgroundColorCode));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(buttonFont);
        button.setBorder(new EmptyBorder(0,0,0,0));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                    registeration(hintTextLabel, usernameField, fullnameField, emailField, countryComboBox, genderComboBox,
                            passwordField);
                }

            
        });
    }

    private void passwordTextField(Font userDataFont, JPasswordField passwordField) {
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setText("Enter password");
        passwordField.setForeground(Color.decode("#A1B3D2"));
        passwordField.setFont(userDataFont);
        passwordField.setColumns(17);
        passwordField.setEchoChar((char) 0);
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
                    passwordField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    private void comboBoxOptions(JComboBox<String> countryComboBox) {
        countryComboBox.setBackground(Color.white);
        countryComboBox.setPreferredSize(new Dimension(340, 30));
        countryComboBox.setBorder(BorderFactory.createEmptyBorder());
    }

    private void emailTextField(Font userDataFont, JTextField emailField) {
        emailField.setBorder(BorderFactory.createEmptyBorder());
        emailField.setText("Enter Email");
        emailField.setForeground(Color.decode("#A1B3D2"));
        emailField.setFont(userDataFont);
        emailField.setColumns(17);
        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("Enter Email")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                    emailField.setText("Enter Email");
                    emailField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    private void fullnameTextField(Font userDataFont, JTextField fullnameField) {
        fullnameField.setBorder(BorderFactory.createEmptyBorder());
        fullnameField.setText("Enter Fullname");
        fullnameField.setForeground(Color.decode("#A1B3D2"));
        fullnameField.setFont(userDataFont);
        fullnameField.setColumns(17);
        fullnameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fullnameField.getText().equals("Enter Fullname")) {
                    fullnameField.setText("");
                    fullnameField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (fullnameField.getText().isEmpty()) {
                    fullnameField.setText("Enter Fullname");
                    fullnameField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    private void usernameTextField(JTextField usernameField, Font userDataFont) {
        usernameField.setBorder(BorderFactory.createEmptyBorder());
        usernameField.setText("Enter Username");
        usernameField.setForeground(Color.decode("#A1B3D2"));
        usernameField.setFont(userDataFont);
        usernameField.setColumns(17);
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Enter Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Enter Username");
                    usernameField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    private void loginNowLabel(JLabel loginTextLabel) {
        Font resTextFont = new Font("Arial", Font.PLAIN, 15);
        loginTextLabel.setForeground(Color.decode("#2662FF"));
        loginTextLabel.setFont(resTextFont);
        loginTextLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        loginTextLabel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                LoginGui loginGui = new LoginGui(registerGui);
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
    }

    private void forgetPasswordLabel(JLabel registeredTextLabel) {
        Font forgotTextFont = new Font("Arial", Font.PLAIN, 15);
        registeredTextLabel.setForeground(Color.gray);
        registeredTextLabel.setFont(forgotTextFont);
    }

    private void subTextLabel(JLabel subTextLabel) {
        forgetPasswordLabel(subTextLabel);
        Insets subTextInsets = new Insets(15, 0, 0, 0);
        subTextLabel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(subTextInsets), subTextLabel.getBorder()));
    }

    private void welcomeLabel(JLabel welcomeTextLabel) {
        Font welcomeTextFont = new Font("Arial", Font.BOLD, 35);
        welcomeTextLabel.setFont(welcomeTextFont);
        Insets welcomeTextInsets = new Insets(15, 0, 0, 0);
        welcomeTextLabel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(welcomeTextInsets), welcomeTextLabel.getBorder()));
    }

    private void registeration(JLabel hintTextLabel, JTextField usernameField, JTextField fullnameField,
            JTextField emailField, JComboBox<String> countryComboBox, JComboBox<String> genderComboBox,
            JPasswordField passwordField) {
        String password = new String(passwordField.getPassword());
        if (password.equals(new String("Enter password"))) {
            password = "";
        }
        // handle submit Button Cliked
        boolean usernameValid = Validation.validateUsername(usernameField.getText());
        boolean passwordStrong = Validation.validatePassword(password) == PasswordStrength.STRONG;
        boolean passwordAverage = Validation.validatePassword(password) == PasswordStrength.AVERAGE;
        boolean passwordEmpty = Validation.validatePassword(password) == PasswordStrength.EMPTY;
        boolean passwordInvalid = Validation.validatePassword(password) == PasswordStrength.INVALID;

        boolean valid = usernameValid && (passwordStrong || passwordAverage);
        if (valid) {
            Map<String, Object> userData = Map.of(
                    "username", usernameField.getText(),
                    "fullname", fullnameField.getText(),
                    "email", emailField.getText(),
                    "nationality", countryComboBox.getSelectedItem().toString(),
                    "gender", genderComboBox.getSelectedItem().toString(),
                    "id", Services.generateRandomID(),
                    "balance", 0,
                    "password", new String(passwordField.getPassword()));
            Services.registerUser(userData);
            Components.showMessageDialog("Account created Successfully.", "message",
                    "assets/images/checked.png");
            System.out.println("Submitted");
            hintTextLabel.setText("");
        } else if (!usernameValid) {
            hintTextLabel.setText("Enter Valid username");
        } else if (passwordEmpty) {
            hintTextLabel.setText("You must Enter Password");
        } else if (!passwordStrong || !passwordAverage) {
            hintTextLabel.setText("Enter Strong password");
        } else if (passwordInvalid) {
            hintTextLabel.setText("Invalid password");
        }
    }
    
}



class RoundedButton extends JButton {
    
    public RoundedButton(String text) {
        super(text);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.setColor(Color.WHITE);
        g2.drawString(getText(), getWidth()/2 - g2.getFontMetrics().stringWidth(getText())/2, getHeight()/2 + g2.getFontMetrics().getAscent()/2);
        g2.dispose();
    }
}

