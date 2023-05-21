package Shared;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Components {
    private static Point offset; // The offset from the top-left corner of the frame to the mouse click
    public static void handleMouseMovements(JFrame frame, JPanel panel) {

        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                // Save the offset when the mouse is pressed
                offset = e.getPoint();
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Get the current location of the frame
                Point currentLocation = frame.getLocation();
                // Calculate the new location of the frame based on the mouse drag
                int newX = currentLocation.x + e.getX() - offset.x;
                int newY = currentLocation.y + e.getY() - offset.y;

                // Set the new location of the frame
                frame.setLocation(newX, newY);
            }
        });
    }
    public static class RoundedButton extends JButton {
    
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
    
    public static void showMessageDialog(String message , String title , String iconPath) {
        ImageIcon checkedIcon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(90, 90, Image.SCALE_FAST));
        JOptionPane.showMessageDialog(null, message , title , JOptionPane.OK_CANCEL_OPTION , checkedIcon);
    }

    public static class RoundedPanel extends JPanel {

        private Color backgroundColor;
        private int cornerRadius = 15;
    
        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }
    
        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }
    
        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;
        }
    
        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
            graphics.setColor(backgroundColor);
            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
        }
    }

    
    public static void hintTextLabel(JLabel hintTextLabel) {
        Font hinTextFont = new Font("Arial", Font.PLAIN, 12);
        hintTextLabel.setForeground(Color.red);
        hintTextLabel.setFont(hinTextFont);
    }

    public static void inputField(Font userDataFont, JTextField usernameField , String text) {
        usernameField.setBorder(BorderFactory.createEmptyBorder());
        usernameField.setText(text);
        usernameField.setForeground(Color.decode("#A1B3D2"));
        usernameField.setFont(userDataFont);
        usernameField.setColumns(17);
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals(text)) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText(text);
                    usernameField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    public static void passwordField(Font userDataFont, JPasswordField passwordField , String text) {
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setText(text);
        passwordField.setForeground(Color.decode("#A1B3D2"));
        passwordField.setFont(userDataFont);
        passwordField.setColumns(17);
        passwordField.setEchoChar((char) 0);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(text)) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar('\u2022');
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText(text);
                    passwordField.setEchoChar((char)0);
                    passwordField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

}

