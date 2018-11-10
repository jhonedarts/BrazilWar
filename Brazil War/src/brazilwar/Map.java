/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import com.sun.corba.se.spi.protocol.InitialServerRequestDispatcher;
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
     * @param players vetor correspondente as cores dos jogadores, ex: index 1
    */
    public void raffleMap(int players[]){
        //rodeia os estados
        ArrayList<State> statesShuffle = new ArrayList<>();
        statesShuffle.addAll(this.states.values());
        Collections.shuffle(statesShuffle); 
        
        //distribui entre os presentes
        int j = 0;
        for (State state : statesShuffle){
            if (j == players.length){
                j = 0;
            }
            state.addSoldiers(1);
            state.setArmyColor(Parameters.COLOR[j]);
            j++;
        }
    }
    
    public void putSoldier(String intialsState, int amount){
        this.states.get(intialsState).addSoldiers(amount);
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
    
    
}
