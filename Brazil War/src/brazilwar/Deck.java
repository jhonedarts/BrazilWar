/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author jhone
 */
public class Deck {
    
    //o monte de cards embaralhados 
    private Random gerador = new Random();
    
    private ArrayList<Card> cards;
    private int iteratorIndex = 0;

    public Deck() {
        //inicializar
        init();
    }
    
    private void init(){
        Card card;
        int i = 0, j = 0;
        
        while (i < Parameters.STATES.length) { //percorre todos os estados
            if(j >= Parameters.SYMBOLS.length){ //reseta o contador do simbolo
                j = 0;
            }
            //cria um card com simbolo e estado
            card = new Card(Parameters.SYMBOLS[j], Parameters.STATES[i]);
            this.cards.add(card);
            
            i++;j++;
        }
        // + 2 coringas
        card = new Card(Parameters.JOKER_SYMBOL);
        this.cards.add(card);
        card = new Card(Parameters.JOKER_SYMBOL);
        this.cards.add(card);
        
        Collections.shuffle(cards); 
    }
    
    public Card nextCard(){
        if (iteratorIndex == cards.size()){//quando o deck acabar, ele será embaralhado
            Collections.shuffle(cards); 
            iteratorIndex = 0; //reset
        }
        return cards.get(++iteratorIndex);
    }
}
