/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author darts
 */
public class Battlefield {
    
    private Random numberGenerator = new Random();

    public Battlefield() {
    }
    
    public int[] combat(State attacker, State defender){
        int soldiersDefeat[] = {0, 0};
        int redDices[] = {0, 0, 0}; //vetor com os valores de 3 dados
        int yellowDices[] = {0, 0, 0}; //inicializo com 0 pq é um valor fora do range
        
        //o atacante tem direito ao 1º dado, para isso deve ter no minino 2 soldados
        if (attacker.getArmyQuantity() > 1){
            redDices[0] = numberGenerator.nextInt(6)+1;
        }
        //o atacante tem direito ao 2º dado, para isso deve ter no minino 3 soldados
        if (attacker.getArmyQuantity() > 2){
            redDices[1] = numberGenerator.nextInt(6)+1;
        }
        //o atacante tem direito ao 3º dado, para isso deve ter no minino 4 soldados
        if (attacker.getArmyQuantity() > 3){
            redDices[2] = numberGenerator.nextInt(6)+1;
        }
        
        //o defensor tem direito ao 1º dado, para isso deve ter no minino 1 soldado
        if (attacker.getArmyQuantity() >= 1){
            redDices[0] = numberGenerator.nextInt(6)+1;
        }
        //o defensor tem direito ao 2º dado, para isso deve ter no minino 2 soldados
        if (attacker.getArmyQuantity() >= 2){
            redDices[1] = numberGenerator.nextInt(6)+1;
        }
        //o defensor tem direito ao 3º dado, para isso deve ter no minino 3 soldados
        if (attacker.getArmyQuantity() >= 3){
            redDices[2] = numberGenerator.nextInt(6)+1;
        }
        
        Arrays.sort(redDices);
        Arrays.sort(yellowDices);
        
        for (int i = 0; i < redDices.length; i++) {
            if (redDices[i] > yellowDices[i]){
                soldiersDefeat[0]++;//yellow defeat
            }
        }
        soldiersDefeat[1] = 3 - soldiersDefeat[0];
        
        defender.removeSoldiers(soldiersDefeat[0]);
        attacker.removeSoldiers(soldiersDefeat[1]);
        
        return soldiersDefeat;
    }
}
