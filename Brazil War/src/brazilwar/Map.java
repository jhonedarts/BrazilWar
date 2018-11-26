/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author darts
 */
public class Map {
    private HashMap<String, State> states;

    public Map() {
        this.states = new HashMap<>();
        //inicializa com todos estados definidos em STATES 
        for (int i = 0; i < Parameters.STATES.length; i++) {
            State state = new State(Parameters.STATES[i]); //inicializa com a sigla
            this.states.put(state.getInitials(), state);
        }
        
    }
    
    /** parte de sortear um distribuidor e o primeiro a jogar ser o proximo 
    * do ultimo que recebeu a carta foi resumido pra sortear a ordem dos
    * jogadores e sortear e setar o mapa
    * 
    * distribui os soldados para começar o jogo
    * representa o verde, se o vlaor for 0, não temos o jogador verde, 1 temos.
     * @param players correspondente a quatidade de jogadores
    */
    public void shuffleMap(int players){
        //rodeia os estados
        ArrayList<State> statesShuffle = new ArrayList<>();
        statesShuffle.addAll(this.states.values());
        Collections.shuffle(statesShuffle); 
        
        //distribui entre os presentes
        int j = 0;
        for (State state : statesShuffle){
            if (j == players){
                j = 0;
            }
            state.addBattleUnit(new Soldier());
            state.setArmyColor(Parameters.COLOR[j]);
            j++;
        }
    }
    
    public void putSoldier(String intialsState, ArrayList<BattleUnit> battleUnits){
        this.states.get(intialsState).addBattleUnits(battleUnits);
    }
    
    /**
     * retorna a cor do player tem posse do estado
     * @param initials é a sigla do estado
     * @return color cor do player que tem posse do estado
     */
    public String stateBattleUnitsColor(String initials){
        return this.states.get(initials).getArmyColor();
    }
    
    public String[] getFrontiers(String initials){
        return Parameters.Frontiers(initials);
    }
    
    public boolean hasFrontier(String stateA, String stateB){
        String[] frontiersA = Parameters.Frontiers(stateA);
        for (String state : frontiersA) {
            if (state.equals(stateB)){
                return true;
            }           
        }
        return false;
    }
    /**
     * [talvez isso não devesse estar aqui...]
     * Checa a condição de fim de jogo
     * @return verdadeiro se todos territorios pertencem a somente um jogador
     */
    public boolean isGameOver(){
        String colorReference = null;
        for (String key : this.states.keySet()) {
            if (colorReference == null) {
                colorReference = this.states.get(key).getArmyColor();
            } 
            else if (!colorReference.equals(this.states.get(key).getArmyColor())){
                return false;
            }
        }
        return true;
    }
    
    //conhece ArrayList<Player>, isso pode ser um problema
    public void generateBattleUnitsPerRound(ArrayList<Player> players){
        HashMap<String, ArrayList<BattleUnit>> counters = new HashMap<>();
        for (String key : Parameters.COLOR){
            counters.put(key, new ArrayList<>());
        }
        
        String playerColor = this.states.get(Parameters.NORTH[0]).getArmyColor();
        boolean regionHasOwner = true;
        
        String color = null;
        for (String state : Parameters.NORTH){
            color = this.states.get(state).getArmyColor();
            counters.get(color).add(new BattleUnit());
            if(!playerColor.equals(color)){
                regionHasOwner = false;
            }
        }
        if (regionHasOwner){
            ArrayList<BattleUnit> units = new ArrayList<>();
            units.add(new BattleUnit());
            units.add(new BattleUnit());
            units.add(new BattleUnit());
            for (Player player : players){
                if(color.equals(player.getColor())){
                    player.addBattleUnitsPerRegion(Parameters.REGIONS[0], units);
                }
            }
        }
        
        playerColor = this.states.get(Parameters.NORTHEAST[0]).getArmyColor();
        regionHasOwner = true;
        for (String state : Parameters.NORTHEAST){
            color = this.states.get(state).getArmyColor();
            counters.get(color).add(new BattleUnit());
            if(!playerColor.equals(color)){
                regionHasOwner = false;
            }
        }
        if (regionHasOwner){
            ArrayList<BattleUnit> units = new ArrayList<>();
            units.add(new BattleUnit());
            units.add(new BattleUnit());
            units.add(new BattleUnit());
            for (Player player : players){
                if(color.equals(player.getColor())){
                    player.addBattleUnitsPerRegion(Parameters.REGIONS[0], units);
                }
            }
        }
        
        playerColor = this.states.get(Parameters.MIDWEST[0]).getArmyColor();
        regionHasOwner = true;
        for (String state : Parameters.MIDWEST){
            color = this.states.get(state).getArmyColor();
            counters.get(color).add(new BattleUnit());
            if(!playerColor.equals(color)){
                regionHasOwner = false;
            }
        }
        if (regionHasOwner){
            ArrayList<BattleUnit> units = new ArrayList<>();
            units.add(new BattleUnit());
            units.add(new BattleUnit());
            for (Player player : players){
                if(color.equals(player.getColor())){
                    player.addBattleUnitsPerRegion(Parameters.REGIONS[0], units);
                }
            }
        }
        
        playerColor = this.states.get(Parameters.SOUTHEAST[0]).getArmyColor();
        regionHasOwner = true;
        
        for (String state : Parameters.SOUTHEAST){
            color = this.states.get(state).getArmyColor();
            counters.get(color).add(new BattleUnit());
            if(!playerColor.equals(color)){
                regionHasOwner = false;
            }
        }
        if (regionHasOwner){
            ArrayList<BattleUnit> units = new ArrayList<>();
            units.add(new BattleUnit());
            units.add(new BattleUnit());
            for (Player player : players){
                if(color.equals(player.getColor())){
                    player.addBattleUnitsPerRegion(Parameters.REGIONS[0], units);
                }
            }
        }
        
        playerColor = this.states.get(Parameters.SOUTH[0]).getArmyColor();
        regionHasOwner = true;
        for (String state : Parameters.SOUTH){
            color = this.states.get(state).getArmyColor();
            counters.get(color).add(new BattleUnit());
            if(!playerColor.equals(color)){
                regionHasOwner = false;
            }
        }
        if (regionHasOwner){
            ArrayList<BattleUnit> units = new ArrayList<>();
            units.add(new BattleUnit());
            units.add(new BattleUnit());
            for (Player player : players){
                if(color.equals(player.getColor())){
                    player.addBattleUnitsPerRegion(Parameters.REGIONS[0], units);
                }
            }
        }
        
        for (String key : Parameters.COLOR){
            if (counters.get(key) == null){
                continue;
            }
            //minimo de 3 unidades por rodada
            if (counters.get(key).size()<6){
                for (int i=counters.get(key).size()%2; i<3; i++){
                    counters.get(key).add(new BattleUnit());
                }
                continue;
            }
            int prefix = counters.get(key).size()%2;
            int deleteRows = prefix - counters.get(key).size();
            for (int i=0; i<deleteRows; i++){
                counters.get(key).remove(prefix + i);
            }
        }
        for (int i=0; i<players.size(); i++){
            players.get(i).addBattleUnits(counters.get(players.get(i).getColor()));
        }
    }
    
}
