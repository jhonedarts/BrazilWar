/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.ArrayList;
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
    
    public int[] combat(ArrayList<BattleUnit> attacker, ArrayList<BattleUnit> defender){//deveria receber so os exercitos ao inves dos estados?
        int battleUnitsDefeat[] = {0, 0}; //[attacker, defender]
        int redDices[] = {0, 0, 0}; //vetor com os valores de 3 dados
        int yellowDices[] = {0, 0, 0}; //inicializo com 0 pq é um valor fora do range
        
        //validação de quantidade suficiente para atacar já foi feita em State.getBattleUnitsForCombat()
        //o atacante tem direito ao 1º dado
        if (attacker.size() == 1){
            redDices[0] = numberGenerator.nextInt(6)+1;
        }
        //o atacante tem direito ao 2º dado
        if (attacker.size() == 2){
            redDices[1] = numberGenerator.nextInt(6)+1;
        }
        //o atacante tem direito ao 3º dado
        if (attacker.size() == 3){
            redDices[2] = numberGenerator.nextInt(6)+1;
        }
        
        //o defensor tem direito ao 1º dado
        if (defender.size() == 1){
            yellowDices[0] = numberGenerator.nextInt(6)+1;
        }
        //o defensor tem direito ao 2º dados
        if (defender.size() == 2){
            yellowDices[1] = numberGenerator.nextInt(6)+1;
        }
        //o defensor tem direito ao 3º dado
        if (defender.size() == 3){
            yellowDices[2] = numberGenerator.nextInt(6)+1;
        }
        
        Arrays.sort(redDices);
        Arrays.sort(yellowDices);
        
        for (int i = 0; i < redDices.length; i++) {
            if (redDices[i] <= yellowDices[i]){
                battleUnitsDefeat[0]++;//red defeat
            } else {
                battleUnitsDefeat[1]++;//yellow defeat
            }
        }
        
        return battleUnitsDefeat;
    }
}
