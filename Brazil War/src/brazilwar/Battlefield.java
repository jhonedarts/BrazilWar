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
    
    public int[] combat(State attacker, State defender){//deveria receber so os exercitos ao inves dos estados?
        int soldiersDefeat[] = {0, 0}; //[attacker, defender]
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
                soldiersDefeat[1]++;//yellow defeat
            }
        }
        soldiersDefeat[0] = 3 - soldiersDefeat[0];
        
        attacker.removeSoldiers(soldiersDefeat[0]);//soldados do atacante sao retirados do mapa
        defender.removeSoldiers(soldiersDefeat[1]);//soldados do defensor sao retirados do mapa
        
        //se a retirada dos soldados é feita aqui, essa função não precisa ter retorno
        return soldiersDefeat;
    }
}
