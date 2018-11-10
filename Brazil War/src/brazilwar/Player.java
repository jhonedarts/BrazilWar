/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author darts
 */
public class Player {
    private ArrayList<Card> cards;//possivel uso de herança com Cards(pai) e StateCard e JokerCard (filhos)
    private int statesAmount; // guarda a quantidades de posses, pode ser a lista de StatesId ou a lista de estados mesmo.
                               // o proposito é saber a quatidade pra dar a condição de vitoria
    private String color;

    public Player(int statesAmount, String color) {
        this.cards = new ArrayList<>();
        this.statesAmount = statesAmount;
        this.color = color;
    }
    
    public boolean canTradeCards(){
        //serámarcado zero para iguais e 1 para diferentes, depois é só contar
        int occurrences[] = new int[Parameters.SYMBOLS.length]; 
        //impossivel ter uma combinação
        if (this.cards.size() < Parameters.SYMBOLS.length){
            return false;
        }
        //impossivel não ter uma combinação
        if(this.cards.size() > (Parameters.SYMBOLS.length-1)*Parameters.SYMBOLS.length-1){
            return true;
        }
        for (int i = 0; i < Parameters.SYMBOLS.length; i++) {
            for (int j = 0; j < this.cards.size(); j++) {
                if(Parameters.SYMBOLS[i].equals(this.cards.get(j).getSymbol())){
                    occurrences[i]++;
                }
            }
        }
        Arrays.sort(occurrences);
        if ((occurrences[0] < Parameters.SYMBOLS.length) || (occurrences[occurrences.length-1] == 0)){
            return false;
        }
        return true;
    }
    
    public void removeTradeCards() {
        
    }
    
    
    public boolean canAddCard() {
        return this.cards.size() < Parameters.LIMIT_CARDS_IN_HANDS; 
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int getStatesAmount() {
        return statesAmount;
    }

    public void setStatesAmount(int statesAmount) {
        this.statesAmount = statesAmount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
}
