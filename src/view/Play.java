package view;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import constant.GAME_MODE;
import controller.Kontroller;
import model.CardUI;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;



public class Play {

    private int card_width = 75;
    private int card_height = 108;

    private List<JButton> listButton = new ArrayList<JButton>();

    private SpringLayout springLayout;
    private JFrame frmHeartsGame;
    private JButton btnUserA;
    private JLabel lNameA;
    private JButton btnCardA;
    private JLabel lScoreA;
    private JLabel lNameB;
    private JButton btnCardB;
    private JLabel lScoreB;
    private JLabel lNameC;
    private JButton btnCardC;
    private JLabel lScoreC;
    private JLabel lNameD;
    private JButton btnCardD;
    private JLabel lScoreD;
    private JLabel l;

  
    private int picked_card_count;
    private Boolean waiting_user;
    private String notification;

    private Kontroller my_boss;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Play window = new Play();
                    window.frmHeartsGame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Play() {
        initialize();
    }

    private void initialize() {
        frmHeartsGame = new JFrame();
        frmHeartsGame.setTitle("Hearts Game ");
        frmHeartsGame.getContentPane().setBackground(Color.green);
        frmHeartsGame.setResizable(false);
        frmHeartsGame.setBounds(100, 100, 800, 650);
        frmHeartsGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        springLayout = new SpringLayout();
        frmHeartsGame.getContentPane().setLayout(springLayout);


        initA();
        initB();
        initC();
        initD();

        initNoti();
        drawNoti(null);

        my_boss = new Kontroller();
        my_boss.init_kontroller();

        btnUserA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notification = "";
                newGame();
            }
        });

    }

    private void initA() {

    	btnUserA = new JButton();
        springLayout.putConstraint(SpringLayout.NORTH, btnUserA, 400, SpringLayout.NORTH,
                frmHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, btnUserA, 10, SpringLayout.WEST,
                frmHeartsGame.getContentPane());
        btnUserA.setFocusPainted(false);
        try {
            ImageIcon img = new ImageIcon(Play.class.getResource("/resource/computer.png"));
            btnUserA.setIcon(new ImageIcon(img.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        } catch (NullPointerException e) {
            System.out.println("Image not found");
        }

        frmHeartsGame.getContentPane().add(btnUserA);

        lNameA = new JLabel("You");
        springLayout.putConstraint(SpringLayout.NORTH, lNameA, 5, SpringLayout.SOUTH, btnUserA);
        springLayout.putConstraint(SpringLayout.WEST, lNameA, 0, SpringLayout.WEST, btnUserA);
        springLayout.putConstraint(SpringLayout.EAST, lNameA, 0, SpringLayout.EAST, btnUserA);
        lNameA.setHorizontalAlignment(SwingConstants.CENTER);
        lNameA.setAlignmentX(Component.CENTER_ALIGNMENT);

        lNameA.setForeground(Color.black);
        lNameA.setFont(new Font("Tahoma", Font.PLAIN, 15));
        frmHeartsGame.getContentPane().add(lNameA);

        lScoreA = new JLabel();
        lScoreA.setText("0/0");
        lScoreA.setHorizontalAlignment(SwingConstants.CENTER);
        lScoreA.setBorder(new LineBorder(Color.red, 3, true));
        lScoreA.setForeground(Color.black);
        lScoreA.setFont(new Font("Tahoma", Font.PLAIN, 17));
  
        springLayout.putConstraint(SpringLayout.WEST, lScoreA, 0, SpringLayout.WEST, btnUserA);
        springLayout.putConstraint(SpringLayout.NORTH, lScoreA, 5, SpringLayout.SOUTH, lNameA);
        springLayout.putConstraint(SpringLayout.EAST, lScoreA, 0, SpringLayout.EAST, btnUserA);
        frmHeartsGame.getContentPane().add(lScoreA);

        btnCardA = new JButton();

        int cardA_top = 280;
        int cardA_left = 350;

        springLayout.putConstraint(SpringLayout.WEST, btnCardA, cardA_left, SpringLayout.WEST,
                frmHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, btnCardA, cardA_left + card_width, SpringLayout.WEST,
                frmHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, btnCardA, cardA_top, SpringLayout.NORTH,
                frmHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, btnCardA, cardA_top + card_height, SpringLayout.NORTH,
                frmHeartsGame.getContentPane());

        btnCardA.setVisible(false);

        frmHeartsGame.getContentPane().add(btnCardA);
    }

    private void initB() {

        JButton bcompB = new JButton();
        springLayout.putConstraint(SpringLayout.NORTH, bcompB, 170, SpringLayout.NORTH,
                frmHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, bcompB, 150, SpringLayout.WEST,
                frmHeartsGame.getContentPane());
        bcompB.setFocusPainted(false);
        try {
            ImageIcon img = new ImageIcon(Play.class.getResource("/resource/computer.png"));
            bcompB.setIcon(new ImageIcon(img.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        } catch (NullPointerException e) {
            System.out.println("Image not found");

        }

        frmHeartsGame.getContentPane().add(bcompB);

        lNameB = new JLabel("Computer B");
        springLayout.putConstraint(SpringLayout.EAST, lNameB, 0, SpringLayout.EAST, bcompB);
        springLayout.putConstraint(SpringLayout.WEST, lNameB, 0, SpringLayout.WEST, bcompB);
        springLayout.putConstraint(SpringLayout.NORTH, lNameB, 5, SpringLayout.SOUTH, bcompB);
        lNameB.setHorizontalAlignment(SwingConstants.CENTER);
        lNameB.setAlignmentX(Component.CENTER_ALIGNMENT);
        lNameB.setForeground(Color.black);
        lNameB.setFont(new Font("Tahoma", Font.PLAIN, 15));
        frmHeartsGame.getContentPane().add(lNameB);

        lScoreB = new JLabel();
        lScoreB.setHorizontalAlignment(SwingConstants.CENTER);
        lScoreB.setBorder(new LineBorder(Color.red, 3, true));
        lScoreB.setForeground(Color.black);
        lScoreB.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lScoreB.setText("0/0");
       
        springLayout.putConstraint(SpringLayout.WEST, lScoreB, 0, SpringLayout.WEST, bcompB);
        springLayout.putConstraint(SpringLayout.EAST, lScoreB, 0, SpringLayout.EAST, bcompB);
        springLayout.putConstraint(SpringLayout.NORTH, lScoreB, 5, SpringLayout.SOUTH, lNameB);
        frmHeartsGame.getContentPane().add(lScoreB);

        btnCardB = new JButton();

        int cardB_top = 5;
        int cardB_left = 20;

        springLayout.putConstraint(SpringLayout.WEST, btnCardB, cardB_left, SpringLayout.EAST, bcompB);
        springLayout.putConstraint(SpringLayout.EAST, btnCardB, cardB_left + card_width, SpringLayout.EAST, bcompB);
        springLayout.putConstraint(SpringLayout.NORTH, btnCardB, cardB_top, SpringLayout.NORTH, bcompB);
        springLayout.putConstraint(SpringLayout.SOUTH, btnCardB, cardB_top + card_height, SpringLayout.NORTH,
        		bcompB);

        btnCardB.setVisible(false);

        frmHeartsGame.getContentPane().add(btnCardB);
    }

    private void initC() {

        JButton bcompC = new JButton();
        springLayout.putConstraint(SpringLayout.NORTH, bcompC, 10, SpringLayout.NORTH,
                frmHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, bcompC, 300, SpringLayout.WEST,
                frmHeartsGame.getContentPane());
        bcompC.setFocusPainted(false);
        try {
            ImageIcon img = new ImageIcon(Play.class.getResource("/resource/computer.png"));
            bcompC.setIcon(new ImageIcon(img.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        } catch (NullPointerException e) {
            System.out.println("Image not found");

        }

        frmHeartsGame.getContentPane().add(bcompC);
        lScoreC = new JLabel();
        lScoreC.setHorizontalAlignment(SwingConstants.CENTER);
        lScoreC.setBorder(new LineBorder(Color.red, 3, true));
        lScoreC.setForeground(Color.black);
        lScoreC.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lScoreC.setText("0/0");
        springLayout.putConstraint(SpringLayout.WEST, lScoreC, 10, SpringLayout.EAST, bcompC);
        springLayout.putConstraint(SpringLayout.SOUTH, lScoreC, 0, SpringLayout.SOUTH, bcompC);
        springLayout.putConstraint(SpringLayout.EAST, lScoreC, 100, SpringLayout.EAST, bcompC);
        frmHeartsGame.getContentPane().add(lScoreC);

        lNameC = new JLabel("Computer C");
        springLayout.putConstraint(SpringLayout.NORTH, lScoreC, 5, SpringLayout.SOUTH, lNameC);
        springLayout.putConstraint(SpringLayout.NORTH, lNameC, 5, SpringLayout.NORTH, bcompC);
        springLayout.putConstraint(SpringLayout.WEST, lNameC, 0, SpringLayout.WEST, lScoreC);
        lNameC.setHorizontalAlignment(SwingConstants.CENTER);
        lNameC.setAlignmentX(Component.CENTER_ALIGNMENT);
        springLayout.putConstraint(SpringLayout.EAST, lNameC, 0, SpringLayout.EAST, lScoreC);
        lNameC.setForeground(Color.black);
        lNameC.setFont(new Font("Tahoma", Font.PLAIN, 15));
        frmHeartsGame.getContentPane().add(lNameC);

        btnCardC = new JButton();

        int cardC_top = 10;
        int cardC_left = 50;

        springLayout.putConstraint(SpringLayout.WEST, btnCardC, cardC_left, SpringLayout.WEST, bcompC);
        springLayout.putConstraint(SpringLayout.EAST, btnCardC, cardC_left + card_width, SpringLayout.WEST, bcompC);
        springLayout.putConstraint(SpringLayout.NORTH, btnCardC, cardC_top, SpringLayout.SOUTH, bcompC);
        springLayout.putConstraint(SpringLayout.SOUTH, btnCardC, cardC_top + card_height, SpringLayout.SOUTH,
        		bcompC);
        btnCardC.setVisible(false);

        frmHeartsGame.getContentPane().add(btnCardC);
    }

    private void initD() {

        btnCardD = new JButton();

        int cardD_top = 175;
        int cardD_left = 450;

        springLayout.putConstraint(SpringLayout.NORTH, btnCardD, cardD_top, SpringLayout.NORTH,
                frmHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, btnCardD, cardD_left, SpringLayout.WEST,
                frmHeartsGame.getContentPane());

        springLayout.putConstraint(SpringLayout.EAST, btnCardD, cardD_left + card_width, SpringLayout.WEST,
                frmHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, btnCardD, cardD_top + card_height, SpringLayout.NORTH,
                frmHeartsGame.getContentPane());

        btnCardD.setVisible(false);

        frmHeartsGame.getContentPane().add(btnCardD);

        JButton bcompD = new JButton();
        springLayout.putConstraint(SpringLayout.NORTH, bcompD, -5, SpringLayout.NORTH, btnCardD);
        springLayout.putConstraint(SpringLayout.WEST, bcompD, 20, SpringLayout.EAST, btnCardD);
        bcompD.setFocusPainted(false);
        try {
            ImageIcon img = new ImageIcon(Play.class.getResource("/resource/computer.png"));
            bcompD.setIcon(new ImageIcon(img.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        } catch (NullPointerException e) {
            System.out.println("Image not found");

        }

        frmHeartsGame.getContentPane().add(bcompD);

        lNameD = new JLabel("Computer D");
        springLayout.putConstraint(SpringLayout.NORTH, lNameD, 5, SpringLayout.SOUTH, bcompD);
        springLayout.putConstraint(SpringLayout.WEST, lNameD, 0, SpringLayout.WEST, bcompD);
        springLayout.putConstraint(SpringLayout.EAST, lNameD, 0, SpringLayout.EAST, bcompD);
        lNameD.setHorizontalAlignment(SwingConstants.CENTER);
        lNameD.setAlignmentX(Component.CENTER_ALIGNMENT);
        lNameD.setForeground(Color.black);
        lNameD.setFont(new Font("Tahoma", Font.PLAIN, 15));
        frmHeartsGame.getContentPane().add(lNameD);

        lScoreD = new JLabel();
        lScoreD.setHorizontalAlignment(SwingConstants.CENTER);
        lScoreD.setBorder(new LineBorder(Color.red, 3, true));
        lScoreD.setForeground(Color.black);
        lScoreD.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lScoreD.setText("0/0");
        springLayout.putConstraint(SpringLayout.NORTH, lScoreD, 5, SpringLayout.SOUTH, lNameD);
        springLayout.putConstraint(SpringLayout.WEST, lScoreD, 0, SpringLayout.WEST, bcompD);
        springLayout.putConstraint(SpringLayout.EAST, lScoreD, 0, SpringLayout.EAST, bcompD);
        frmHeartsGame.getContentPane().add(lScoreD);
    }

    private void initNoti() {
        l = new JLabel("");
        notification = "Click figure to start ";
        springLayout.putConstraint(SpringLayout.WEST, l, 15, SpringLayout.WEST, frmHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, l, -10, SpringLayout.NORTH, btnUserA);
        l.setForeground(Color.black);
        l.setFont(new Font("Tahoma", Font.PLAIN, 15));
        frmHeartsGame.getContentPane().add(l);
    }

  

    private void draw() {

        drawNoti(null);

        List<Integer> list_score = my_boss.get_all_score();

        lScoreA.setText(list_score.get(0) + "/" + list_score.get(1));
        lScoreB.setText(list_score.get(2) + "/" + list_score.get(3));
        lScoreC.setText(list_score.get(4) + "/" + list_score.get(5));
        lScoreD.setText(list_score.get(6) + "/" + list_score.get(7));

        btnCardA.setVisible(false);
        btnCardB.setVisible(false);
        btnCardC.setVisible(false);
        btnCardD.setVisible(false);

        List<CardUI> play_list = my_boss.get_play_list();
        // System.out.println("play_list.size " + play_list.size());

        try {
            for (int i = 0; i < play_list.size(); i++) {
                CardUI curCardUI = play_list.get(i);


                if (curCardUI.getOwner().equals("A")) {
                    // card A
                    try {
                        ImageIcon img = new ImageIcon(Play.class.getResource(
                                "/resource/card/" + curCardUI.getValue() + "_" + curCardUI.getSuit() + ".png"));
                        btnCardA.setIcon(new ImageIcon(img.getImage().getScaledInstance(card_width, card_height,
                                java.awt.Image.SCALE_SMOOTH)));
                    } catch (NullPointerException e) {
                        System.out.println("Image not found");
                    }
                    btnCardA.setVisible(true);
                } else if (curCardUI.getOwner().equals("B")) {
                    // card B
                    try {
                        ImageIcon img = new ImageIcon(Play.class.getResource(
                                "/resource/card/" + curCardUI.getValue() + "_" + curCardUI.getSuit() + ".png"));
                        btnCardB.setIcon(new ImageIcon(img.getImage().getScaledInstance(card_width, card_height,
                                java.awt.Image.SCALE_SMOOTH)));
                    } catch (NullPointerException e) {
                        System.out.println("Image not found");
                    }
                    btnCardB.setVisible(true);
                } else if (curCardUI.getOwner().equals("C")) {
                    // card B
                    try {
                        ImageIcon img = new ImageIcon(Play.class.getResource(
                                "/resource/card/" + curCardUI.getValue() + "_" + curCardUI.getSuit() + ".png"));
                        btnCardC.setIcon(new ImageIcon(img.getImage().getScaledInstance(card_width, card_height,
                                java.awt.Image.SCALE_SMOOTH)));
                    } catch (NullPointerException e) {
                        System.out.println("Image not found");
                    }
                    btnCardC.setVisible(true);
                } else {
                    // card D
                    try {
                        ImageIcon img = new ImageIcon(Play.class.getResource(
                                "/resource/card/" + curCardUI.getValue() + "_" + curCardUI.getSuit() + ".png"));
                        btnCardD.setIcon(new ImageIcon(img.getImage().getScaledInstance(card_width, card_height,
                                java.awt.Image.SCALE_SMOOTH)));
                    } catch (NullPointerException e) {
                        System.out.println("Image not found");
                    }
                    btnCardD.setVisible(true);
                }
            }
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
    }

    private void drawCard() {
        int card_left = 120;
        int card_top = 400;

        float card_index = -1;

        for (JButton item : listButton) {
            frmHeartsGame.getContentPane().remove(item);
        }
        listButton.clear();

        List<CardUI> listCardUI = my_boss.get_user_list();

        for (int i = 0; i < listCardUI.size(); i++) {

            card_index++;
            if (i == 7) {
                card_top = card_top + card_height;
                card_index = 0.5f;
            }

            CardUI curCardUI = listCardUI.get(i);

            JButton btnCard = new JButton();
            btnCard.setFocusPainted(false);
            btnCard.setName(i + "_" + "font");

            springLayout.putConstraint(SpringLayout.WEST, btnCard, (int) (card_left + card_index * card_width),
                    SpringLayout.WEST, frmHeartsGame.getContentPane());
            springLayout.putConstraint(SpringLayout.EAST, btnCard, (int) (card_left + (card_index + 1) * card_width),
                    SpringLayout.WEST, frmHeartsGame.getContentPane());
            springLayout.putConstraint(SpringLayout.NORTH, btnCard, card_top, SpringLayout.NORTH,
                    frmHeartsGame.getContentPane());
            springLayout.putConstraint(SpringLayout.SOUTH, btnCard, card_top + card_height, SpringLayout.NORTH,
                    frmHeartsGame.getContentPane());
            try {
                ImageIcon img = new ImageIcon(Play.class
                        .getResource("/resource/card/" + curCardUI.getValue() + "_" + curCardUI.getSuit() + ".png"));
                btnCard.setIcon(new ImageIcon(
                        img.getImage().getScaledInstance(card_width, card_height, java.awt.Image.SCALE_SMOOTH)));

            } catch (NullPointerException e) {
                System.out.println("Image not found");
                btnCard.setText("Not found");
            }

            if (curCardUI.getPickable() != true) {
                btnCard.setEnabled(false);
            }

            btnCard.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   
                    if (waiting_user) {
                        String[] data = btnCard.getName().split("_");
                        int index = Integer.parseInt(data[0]);
                        String face = data[1];

                        if (my_boss.pick_card_mode() == GAME_MODE.PASS_3_CARD) {

                            if (face.equals("font")) {
                                btnCard.setName(index + "_back");
                                picked_card_count++;
                                ImageIcon _img = new ImageIcon(Play.class.getResource("/resource/card_back.png"));
                                btnCard.setIcon(new ImageIcon(_img.getImage().getScaledInstance(card_width, card_height,
                                        java.awt.Image.SCALE_SMOOTH)));

                                if (picked_card_count == 3) {
                                    passThreeCard();
                                }

                            } else {
                                btnCard.setName(index + "_font");
                                picked_card_count--;

                                CardUI changCardUI = listCardUI.get(index);

                                ImageIcon _img = new ImageIcon(Play.class.getResource("/resource/card/"
                                        + changCardUI.getValue() + "_" + changCardUI.getSuit() + ".png"));
                                btnCard.setIcon(new ImageIcon(_img.getImage().getScaledInstance(card_width, card_height,
                                        java.awt.Image.SCALE_SMOOTH)));
                            }
                        } else {
                           
                            passOneCard(index);
                        }
                    }
                }
            });

            listButton.add(btnCard);
            frmHeartsGame.getContentPane().add(btnCard);
        }

        frmHeartsGame.validate();
        frmHeartsGame.repaint();
    } 

    private void drawNoti(String noti) {
        if (noti != null) {
            notification = noti;
        }
        l.setText(notification);
    }

    private void passThreeCard() {
        List<Integer> picked_card_index_list = new ArrayList<Integer>();

        // get data from UI name
        int size = listButton.size();
        for (int i = 0; i < size; i++) {
            if (listButton.get(i).getName().contains("_back")) {
                picked_card_index_list.add(i);
            }
        }

        // pass card
        my_boss.pass_3_card(picked_card_index_list);

        // create game thread
        startGame();
    }

    private void passOneCard(int index) {

        System.out.println("Pass " + index);

        waiting_user = false;
        my_boss.play_round(index);

        todoThread();
    }

    private void newGame() {

        my_boss.new_game();
        my_boss.new_round();

       
        drawCard();

        if (my_boss.pick_card_mode() == GAME_MODE.PASS_1_CARD) {
         
            startGame();
        } else {
         
            picked_card_count = 0;

            drawNoti("Pass 3 card to " + my_boss.pass_card_to());
            waiting_user = true;
        }
    }

    private void startGame() {

        my_boss.start_game();

        drawCard();

        waiting_user = false;

        if (my_boss.waiting_user()) {
            waiting_user = true;
        }

        todoThread();
    }

    private void todoThread() {
        while (true) {

            if (my_boss.complete_round()) {
                System.out.println("Complete Round");
                my_boss.calc_point();

                draw();
                drawCard();

                

                if (my_boss.is_end_game()) {
                    my_boss.end_game();

                    if (my_boss.is_end_game()) {
                        JOptionPane.showMessageDialog(new JFrame(), my_boss.who_won_game(), "Info",
                                JOptionPane.INFORMATION_MESSAGE);

                        my_boss.init_kontroller();
                        drawNoti("Click figure to start");
                        return;
                    }

                    drawNoti("Click figure to start");
                    drawCard();
                    draw();

                    return;
                } else {
                    NewRoundProcess newRoundProcess = new NewRoundProcess();
                    newRoundProcess.execute();
                }

                return;
            }

            if (waiting_user) {
               
                drawNoti("Your turn");
                break;
            } else {
                drawNoti("...");
                my_boss.play_round(-1);

                if (my_boss.waiting_user()) {
                    draw();
                    drawCard();
                    waiting_user = true;
                }
            }

        }
    }

    class NewRoundProcess extends SwingWorker<Void, Void> {

        @Override
        protected void done() {
            super.done();
            System.out.println("NewRoundProcess is done");
            my_boss.new_round();

            waiting_user = false;

            if (my_boss.waiting_user()) {
                draw();
                drawCard();
                waiting_user = true;
            }

            todoThread();
        }

        
        protected Void doInBackground() throws Exception {
            Thread.sleep(3000);
            return null;
        }

    }

   

}
