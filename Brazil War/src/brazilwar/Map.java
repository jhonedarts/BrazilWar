/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.ArrayList;

/**
 *
 * @author darts
 */
public class Map {
    private ArrayList<State> states;

    public Map() {
        //inicializa com todos estados definidos em STATES 
        for (int i = 0; i < Parameters.STATES.length; i++) {
            State state = new State(Parameters.STATES[i]); //inicializa com a sigla
            this.states.add(state);
        }
    }
    
    //distribui os soldados para começar o jogo
    public void startMap(int playersQuantity){
        /** parte de sortear um distribuidor e o primeiro a jogar ser o proximo 
        * do ultimo que recebeu a carta foi resumido pra sortear a ordem dos
        * jogadores e sortear e setar o mapa
        * 
        */
    }
    
    
}
