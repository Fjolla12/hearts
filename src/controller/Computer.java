package controller;
import java.util.ArrayList;
import java.util.List;

import model.CardUI;


public class Computer extends Player {
	
    public CardUI pass_1_card() {
        
        List<CardUI> my_list = get_my_list();
        for (int i = 0; i < my_list.size(); i++) {
            if (my_list.get(i).getPickable()) {
                CardUI temp = new CardUI(get_my_list_item(i));

                remove_my_list_item(i);

                return temp;
            }
        }

       
        System.out.println("ERROR COMPUTER " + name() + " pass NULL CARD");
        return null;
    }

    public List<CardUI> pass_3_card() {
        List<CardUI> pass_list = new ArrayList<CardUI>();
        pass_list.add(get_my_list_item(0));
        pass_list.add(get_my_list_item(1));
        pass_list.add(get_my_list_item(2));

        System.out.println("Computer " + name() + " passed 3 cards:");
        for (CardUI cardUI : pass_list) {
            System.out.println("  " + cardUI.toString());
        }

        remove_my_list_item(0);
        remove_my_list_item(0);
        remove_my_list_item(0);

        return pass_list;
    }
}
