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
    
    /**
     * Implementa a logica do combate de dados
     * @param attacker quantidade de unidades de batalha do atacante
     * @param defender quantidade de unidades de batalha do defensor
     * @return Array com dois ints, o primeiro representa a quantidade
     * soldados atacantes mortos em combate, e o segundo os do defensor
     */
    public int[] combat(int attacker, int defender){//deveria receber so os exercitos ao inves dos estados?
        int battleUnitsDefeat[] = {0, 0}; //[attacker, defender]
        int redDices[] = {0, 0, 0}; //vetor com os valores de 3 dados
        int yellowDices[] = {0, 0, 0}; //inicializo com 0 pq é um valor fora do range
        
        //validação de quantidade suficiente para atacar já foi feita em State.getBattleUnitsForCombat()
        //o atacante tem direito ao 1º dado
        if (attacker == 1){
            redDices[0] = numberGenerator.nextInt(6)+1;
        }
        //o atacante tem direito ao 2º dado
        if (attacker == 2){
            redDices[1] = numberGenerator.nextInt(6)+1;
        }
        //o atacante tem direito ao 3º dado
        if (attacker == 3){
            redDices[2] = numberGenerator.nextInt(6)+1;
        }
        
        //o defensor tem direito ao 1º dado
        if (defender == 1){
            yellowDices[0] = numberGenerator.nextInt(6)+1;
        }
        //o defensor tem direito ao 2º dados
        if (defender == 2){
            yellowDices[1] = numberGenerator.nextInt(6)+1;
        }
        //o defensor tem direito ao 3º dado
        if (defender == 3){
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
