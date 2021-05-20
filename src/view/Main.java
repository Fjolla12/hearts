package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Main {

    private JFrame frame;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Main() {
        initialize();
    }

    private void initialize() {
    	frame = new JFrame();
    	frame.getContentPane().setBackground(Color.green);
    	frame.setTitle("Hearts Game");
    	frame.setBounds(100, 100, 800, 650);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setResizable(false);
        SpringLayout springLayout = new SpringLayout();
        frame.getContentPane().setLayout(springLayout);

        JLabel GameName = new JLabel("HEARTS");
        springLayout.putConstraint(SpringLayout.WEST, GameName, 80, SpringLayout.WEST,
        		frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, GameName, -80, SpringLayout.EAST,
        		frame.getContentPane());
        GameName.setHorizontalAlignment(SwingConstants.CENTER);
        GameName.setAlignmentX(Component.CENTER_ALIGNMENT);
        GameName.setForeground(Color.red);
        springLayout.putConstraint(SpringLayout.NORTH, GameName, 10, SpringLayout.NORTH,
        		frame.getContentPane());
        GameName.setFont(new Font("Segoe Script", Font.PLAIN, 80));
        frame.getContentPane().add(GameName);

        JButton btnOffline = new JButton("Play");
        btnOffline.setFocusable(false);
        springLayout.putConstraint(SpringLayout.NORTH, btnOffline, 30, SpringLayout.SOUTH, GameName);
        springLayout.putConstraint(SpringLayout.WEST, btnOffline, 80, SpringLayout.WEST,
        		frame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnOffline, -80, SpringLayout.EAST,
        		frame.getContentPane());
        btnOffline.setForeground(Color.black);
        btnOffline.setOpaque(false);
        btnOffline.setContentAreaFilled(false);
        btnOffline.setBorderPainted(false);
        btnOffline.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOffline.setFont(new Font("Segoe Script", Font.PLAIN, 30));
        frame.getContentPane().add(btnOffline);

        

        

        btnOffline.addActionListener(new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
            	Play.main(null);
            	frame.setVisible(false);
            }
        });
        
    }
    }
