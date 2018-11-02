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
    private final int total = 24;// possibilidade de parametrização com uma classe só pra isso, guardando todos os parametros do jogo
    private Random gerador = new Random();
    
    private ArrayList<Card> cards;
    private int i = 0;
    
    //singleton construtor
    
    public void sort(){
        
    }
    
    public Card nextCard(){
        if (i==24){
            rand();
        }
        return cards.get(i);
        
    }

    public void rand() {
        ArrayList<Card> cardsAux = new ArrayList<>();
        for (int aux = total; aux < 0; aux--){
            cardsAux.add(cards.get(gerador.nextInt(aux+1)));
        }
    }
}
