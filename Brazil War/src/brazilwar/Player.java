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
 * Classe que representa a entidade do jogador
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
    
    /**
     * checa se há uma possibilidade de troca de cartas
     * @return
     */
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
    
    /**
     * remove a carta informada da mao do jogador
     * @param card carta a ser removida
     */
    public void removeCard(Card card) {
        this.cards.remove(card);
    } 
    
    /**
     * remove as cartas informadas da mao do jogador
     * @param cards cartas a serem removidas
     */
    public void removeCards(ArrayList<Card> cards) {
        for(Card card : cards){
            this.cards.remove(card);
        }
    }    
    
    /**
     * checa o limite de cartas que jogador pode ter
     * @return
     */
    public boolean canAddCard() {
        return this.cards.size() < Parameters.LIMIT_CARDS_IN_HANDS; 
    }

    /**
     * adiciona uma carta a mao do jogador
     * @param card
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }
    
    /**
     * 
     * @param index posicao
     * @return carta
     */
    public Card getCard(int index){
        return this.cards.get(index);
    }
    
    /**
     * 
     * @return quantidade de cartas que jogador tem
     */
    public int getCardsQtt(){
        return this.cards.size();
    }
    
    /**
     * 
     * @return cor do jogador
     */
    public String getColor() {
        return color;
    }
    
    /**
     * checa de tem um coringa
     * @return 
     */
    private boolean hasJoker(){
        for(Card card : this.cards){
            if (card.getSymbol().equals(Parameters.JOKER_SYMBOL)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * adiciona unidades de batalha para distribuir a mao do jogador
     * @param units 
     */
    public void addBattleUnits(ArrayList<BattleUnit> units){
        this.battleUnitToDistribute.addAll(units);
    }
    
    /**
     * adiciona unidades de batalha para distribuir restritas a uma regiao a mao do jogador
     * @param region regiao  
     * @param units unidades de batalha
     */
    public void addBattleUnitsByRegion(String region, ArrayList<BattleUnit> units){
        this.battleUnitToDistributeRegion.get(region).addAll(units);
    }
    
    /**
     * remove soldados para distribuicao
     * @param units qauntidade
     */
    public void removeSoldiers(int units){
        for (int i = 0; i < units; i++) {
            this.battleUnitToDistribute.remove(0);
        }
    }
    
    /**
     * remove soldados para distribuicao restritos ao uma regiao
     * @param region regiao
     * @param units quantidade
     */
    public void removeSoldiersByRegion(String region, int units){
        for (int i = 0; i < units; i++) {
            this.battleUnitToDistributeRegion.get(region).remove(0);
        }
    }
    
    /**
     * checa se jogador tem unidades para distribuir naquela regiao
     * @param region regiao
     * @return true se tiver
     */
    public boolean hasBattleUnitsByRegion(String region){
        return this.battleUnitToDistributeRegion.get(region).size() > 0;
    }
    
    /**
     * remove unidades para distibuir restritas a uma regiao
     * @param region regiao
     * @param units unidades
     */
    public void removeBattleUnitsByRegion(String region, ArrayList<BattleUnit> units){
        this.battleUnitToDistributeRegion.get(region).addAll(units);
    }

    /**
     * quantidade de unidades para distribuir desconsiderando as restritas as regioes
     * @return quantidade de unidades
     */
    public int getBattleUnitsQtt() {
        return this.battleUnitToDistribute.size();
    }
    
    /**
     * quantidade de unidades para distribuir restritas a regiao
     * @param region regiao
     * @return quantidade de unidades
     */
    public int getBattleUnitsByRegionQtt(String region) {
        return this.battleUnitToDistributeRegion.get(region).size();
    }
    
    /**
     * 
     * @return quantidade total de unidades para distribuir
     */
    public int getBattleUnitsQttTotal() {
        int units = 0;
        for(String region : parameters.getRegions()){
            units += battleUnitToDistributeRegion.get(region).size();
        }
        return this.battleUnitToDistribute.size() + units;
    }
}
