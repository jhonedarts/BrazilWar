/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jhone
 */
public class Deck {
    
    

    //o monte de cards embaralhados 
    private Random gerador = new Random();
    
    private ArrayList<Card> cards;
    private int i = 0;

    public Deck() {
        //inicializar
        init();
    }
    
    //singleton construtor
    
    public void sort(){
        
    }
    
    public Card nextCard(){
        if (i==cards.size()){//quando o deck acabar, ele será resor
            rand();
        }
        return cards.get(i);
        
    }

    public void rand() {
        ArrayList<Card> cardsAux = new ArrayList<>();
        for (int aux = 0; aux < 0; aux--){
            cardsAux.add(cards.get(gerador.nextInt(aux+1)));
        }
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
    }
}
