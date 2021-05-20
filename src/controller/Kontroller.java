package controller;
import java.util.ArrayList;
import java.util.List;

import constant.GAME_MODE;
import model.Card;
import model.CardUI;
import model.Deck;


public class Kontroller {

    private int max_score = 100;

    private List<CardUI> play_list;

    private User userA;
    private Computer compB, compC, compD;

    private Boolean is_heart_broken;
    private Boolean is_first_round;

    private int pass_card_to; // 0 left, 1 right,  2 across,  3 nothing
    private String who_next; // A B C D

    private GAME_MODE pick_card_mode;

    private int scoreA, totalA, scoreB, totalB, scoreC, totalC, scoreD, totalD;

    public Boolean waiting_user() {
        if (who_next.equals(userA.name())) {
            return true;
        } else {
            return false;
        }

    }

    public GAME_MODE pick_card_mode() {
        return pick_card_mode;
    }

    public List<Integer> get_all_score() {
        List<Integer> list_result = new ArrayList<Integer>();
        list_result.add(scoreA);
        list_result.add(totalA);
        list_result.add(scoreB);
        list_result.add(totalB);
        list_result.add(scoreC);
        list_result.add(totalC);
        list_result.add(scoreD);
        list_result.add(totalD);

        return list_result;
    }

    public List<CardUI> get_play_list() {
        return play_list;
    }

    public List<CardUI> get_user_list() {
        if (pick_card_mode() == GAME_MODE.PASS_3_CARD) {
            userA.set_all_pickable();
        } else {
            userA.update_rule(is_first_round, is_heart_broken);
        }

        return userA.get_my_list();
    }

    public String who_next() {
        return this.who_next;
    }

    public String pass_card_to() {
        if (pass_card_to == 0) {
            return "left";
        } else if (pass_card_to == 1) {
            return "right";
        } else if (pass_card_to == 2) {
            return "across";
        } else {
            return "";
        }
    }

    public Boolean complete_round() {

        if (play_list.size() > 4) {
            System.out.println(" " + this.getClass().getName() + " complete_round play_list > 4");
        }

        if (play_list.size() == 4) {
            // is first round
            if (is_first_round) {
                is_first_round = false;
            }
            return true;
        } else {
            return false;
        }
    }

    public void calc_point() {

        // calculate ... point ...
        int point = 0;
        for (CardUI cardUI : play_list) {
            if (cardUI.isHearts()) {
                point += 1;
            }

            if (cardUI.isQueenOfSpades()) {
                point += 13;
            }
        }

        // who get point ?
        // temp = who start
        CardUI max_card = play_list.get(0);
        for (CardUI cardUI : play_list) {
            if (max_card.isSmaller(cardUI)) {
                max_card = cardUI;
            }
        }

        // check broken heart
        if (is_heart_broken) {

        } else {
            for (CardUI cardUI : play_list) {
                if (cardUI.isHearts()) {
                    is_heart_broken = true;
                }
            }
        }

        // set the value
        if (max_card.getOwner().equals(compB.name())) {
            scoreB += point;
        } else if (max_card.getOwner().equals(compC.name())) {
            scoreC += point;
        } else if (max_card.getOwner().equals(compD.name())) {
            scoreD += point;
        } else {
            scoreA += point;
        }

        // set who's next
        who_next = max_card.getOwner();
        System.out.println("start new round = " + who_next);

        // reset play_list
        // call new_round()
    }

    public void init_kontroller() {

        play_list = new ArrayList<CardUI>();

        userA = new User();
        compB = new Computer();
        compC = new Computer();
        compD= new Computer();

        userA.name("A");
        compB.name("B");
        compC.name("C");
        compD.name("D");

        totalA = 0;
        totalB = 0;
        totalC = 0;
        totalD = 0;

        // pass to left
        pass_card_to = 0;
    }

    public void new_game() {
        is_heart_broken = false;
        is_first_round = true;

        //
        if (pass_card_to != 3) {
            pick_card_mode = GAME_MODE.PASS_3_CARD;
        } else {
            pick_card_mode = GAME_MODE.PASS_1_CARD;
        }

        scoreA = 0;
        scoreB = 0;
        scoreC = 0;
        scoreD = 0;

        Deck deck52 = new Deck();
        deck52.init52card();

        List<Card> tempList = new ArrayList<Card>();
        List<CardUI> tempListUI = new ArrayList<CardUI>();

        // user
        tempList.clear();
        tempListUI.clear();
        tempList.addAll(deck52.getDeck1());
        for (Card item : tempList) {
            tempListUI.add(new CardUI(item, "A"));
        }
        userA.add_my_list(tempListUI);

        // computerB
        tempList.clear();
        tempListUI.clear();
        tempList.addAll(deck52.getDeck2());
        for (Card item : tempList) {
            tempListUI.add(new CardUI(item, "B"));
        }
        compB.add_my_list(tempListUI);

        // computerC
        tempList.clear();
        tempListUI.clear();
        tempList.addAll(deck52.getDeck3());
        for (Card item : tempList) {
            tempListUI.add(new CardUI(item, "C"));
        }
        compC.add_my_list(tempListUI);

        // computerD
        tempList.clear();
        tempListUI.clear();
        tempList.addAll(deck52.getDeck4());
        for (Card item : tempList) {
            tempListUI.add(new CardUI(item, "D"));
        }
        compD.add_my_list(tempListUI);
    }

    public void new_round() {
        play_list.clear();
        userA.reset_play_list();
        compB.reset_play_list();
        compC.reset_play_list();
        compD.reset_play_list();
    }

    // pass_card from user and start game
    public void pass_3_card(List<Integer> pass_card_index) {

        if (pick_card_mode == GAME_MODE.PASS_3_CARD) {
            pick_card_mode = GAME_MODE.PASS_1_CARD;
        }

        List<CardUI> a, b, c, d;
        a = new ArrayList<CardUI>();
        b = new ArrayList<CardUI>();
        c = new ArrayList<CardUI>();
        d = new ArrayList<CardUI>();

        // check input form UI
        if (pass_card_index.size() != 3) {
            System.out.println("ERROR Kontroller start_game pass_card_index size != 3");
        }

        // add all
        for (int index = pass_card_index.size() - 1; index >= 0; index--) {
            a.add(userA.get_my_list_item(pass_card_index.get(index)));
            userA.remove_my_list_item(pass_card_index.get(index));
        }
        b.addAll(compB.pass_3_card());
        c.addAll(compC.pass_3_card());
        d.addAll(compD.pass_3_card());

        // pass
        if (pass_card_to == 0) {
            // left
            userA.receive_3_card(d);
            compB.receive_3_card(a);
            compC.receive_3_card(b);
            compD.receive_3_card(c);

        } else if (pass_card_to == 1) {
            // right
            userA.receive_3_card(b);
            compB.receive_3_card(c);
            compC.receive_3_card(d);
            compD.receive_3_card(a);
        } else if (pass_card_to == 2) {
            userA.receive_3_card(c);
            compB.receive_3_card(a);
            compC.receive_3_card(d);
            compD.receive_3_card(b);
        }
    }

    // start game
    public void start_game() {
        if (compB.is_start_first()) {
            who_next = compB.name();
        } else if (compC.is_start_first()) {
            who_next = compC.name();
        } else if (compD.is_start_first()) {
            who_next = compD.name();
        } else {
            who_next = "A";
        }

        System.out.println("Who_next start = " + who_next);
    }

    public void play_round(int index) {

        if (who_next.equals(userA.name())) {
            who_next = compB.name();
            CardUI cardUI = userA.get_my_list_item(index);
            userA.remove_my_list_item(index);

            play_list.add(cardUI);
            userA.add_play_list(cardUI);
            compB.add_play_list(cardUI);
            compC.add_play_list(cardUI);
            compD.add_play_list(cardUI);
            System.out.println("Who_next = B");
            return;
        }

        if (who_next.equals(compB.name())) {
            who_next = compC.name();
            compB.update_rule(is_first_round, is_heart_broken);
            CardUI cardUI = compB.pass_1_card();

            play_list.add(cardUI);
            userA.add_play_list(cardUI);
            compB.add_play_list(cardUI);
            compC.add_play_list(cardUI);
            compB.add_play_list(cardUI);
            System.out.println("Who_next = C");
            return;
        }

        if (who_next.equals(compC.name())) {
            who_next = compD.name();
            compC.update_rule(is_first_round, is_heart_broken);
            CardUI cardUI = compC.pass_1_card();

            play_list.add(cardUI);
            userA.add_play_list(cardUI);
            compB.add_play_list(cardUI);
            compC.add_play_list(cardUI);
            compD.add_play_list(cardUI);
            System.out.println("Who_next = D");
            return;
        }

        if (who_next.equals(compD.name())) {
            who_next = userA.name();

            compD.update_rule(is_first_round, is_heart_broken);
            CardUI cardUI = compD.pass_1_card();

            play_list.add(cardUI);
            userA.add_play_list(cardUI);
            compB.add_play_list(cardUI);
            compC.add_play_list(cardUI);
            compD.add_play_list(cardUI);

            System.out.println("Who_next = A");
            return;
        }
    }

    public Boolean is_end_game() {
        if (userA.get_my_list().size() == 0) {
            return true;
        }

        return false;
    }

    public void end_game() {
        System.out.println("End game");
        pass_card_to = pass_card_to + 1;
        System.out.println("Pass card to " + pass_card_to);

        // check shoot the moon
        if (scoreA == 26) {
            totalB += 26;
            totalC += 26;
            totalD += 26;
        } else if (scoreB == 26) {
            totalA += 26;
            totalC += 26;
            totalD += 26;
        } else if (scoreC == 26) {
            totalA += 26;
            totalB += 26;
            totalD += 26;
        } else if (scoreD == 26) {
            totalA += 26;
            totalB += 26;
            totalC += 26;
        } else {
            totalA += scoreA;
            totalB += scoreB;
            totalC += scoreC;
            totalD += scoreD;
        }

        scoreA = 0;
        scoreB = 0;
        scoreC = 0;
        scoreD = 0;
    }

    public Boolean is_won_game() {

        if (totalA > max_score || totalB > max_score || totalC > max_score || totalD > max_score) {
            return true;
        }
        return false;
    }

    public String who_won_game() {
        String result = "Result:" + "\n";

        int min_score = 1000;
        if (min_score > totalA) {
            min_score = totalA;
        }
        if (min_score > totalB) {
            min_score = totalB;
        }
        if (min_score > totalC) {
            min_score = totalC;
        }
        if (min_score > totalD) {
            min_score = totalD;
        }

        System.out.println("min_score = " + min_score);

        result = result + "You   : " + totalA + ((totalA == min_score) ? " winner " : "") + "\n";
        result = result + "Computer B : " + totalB + ((totalB == min_score) ? " winner" : "") + "\n";
        result = result + "Computer C : " + totalC + ((totalC == min_score) ? " winner" : "") + "\n";
        result = result + "Computer D : " + totalD + ((totalD == min_score) ? " winner " : "") + "\n";

        return result;
    }
}
