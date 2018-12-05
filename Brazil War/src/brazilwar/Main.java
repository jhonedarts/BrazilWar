/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

/**
 *
 * @author darts
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Onde a mãe chora e o filho não vê
        GameFacade war = new GameFacade();
        
        war.startGame();
        war.firstRound();
        war.round();
    }
    
}
