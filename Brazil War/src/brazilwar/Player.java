/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author darts
 */
public class Player {
    private ArrayList<Card> cards;//possivel uso de herança com Cards(pai) e StateCard e JokerCard (filhos)
                               // o proposito é saber a quatidade pra dar a condição de vitoria
    private String color;
    private ArrayList<BattleUnit> battleUnitToDistribute;
    private HashMap<String,ArrayList<BattleUnit>> battleUnitToDistributeRegion;
    private Parameters parameters = Parameters.getInstance();
    
    public Player(String color) {
        this.battleUnitToDistribute = new ArrayList<>();
        this.battleUnitToDistributeRegion = new HashMap<>();
        for(String key : parameters.getRegions()){
            this.battleUnitToDistributeRegion.put(key, new ArrayList<>());
        }
        this.cards = new ArrayList<>();
        this.color = color;
    }
    
    public boolean canTradeCards(){
        //será contado as ocorrencias de cada simbolo
        int occurrences[] = new int[Parameters.SYMBOLS.length]; 
        
        //impossivel ter uma combinação
        if (this.cards.size() < Parameters.SYMBOLS.length){
            return false;
        }
        else if(hasJoker()){//tem 3 ou mais cartas e um coringa entre elas
            return true;
        }
        //impossivel não ter uma combinação
        else if(this.cards.size() > (Parameters.SYMBOLS.length-1)*Parameters.SYMBOLS.length-1){
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
    
    public void removeCard(Card card) {
        this.cards.remove(card);
    }    
    
    public boolean canAddCard() {
        return this.cards.size() < Parameters.LIMIT_CARDS_IN_HANDS; 
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }
    
    public String getColor() {
        return color;
    }
    
    private boolean hasJoker(){
        for(Card card : this.cards){
            if (card.getSymbol().equals(Parameters.JOKER_SYMBOL)){
                return true;
            }
        }
        return false;
    }
    
    public void addBattleUnits(ArrayList<BattleUnit> units){
        this.battleUnitToDistribute.addAll(units);
    }
    
    public void addBattleUnitsPerRegion(String state, ArrayList<BattleUnit> units){
        this.battleUnitToDistributeRegion.get(state).addAll(units);
    }
}
