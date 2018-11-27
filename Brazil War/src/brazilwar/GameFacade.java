/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author lzricardo
 */
public class GameFacade {
    Scanner input;
    Battlefield battlefield;
    Deck deck;
    Map map;
    Trader trader;
    int playersQuantity;
    
    public GameFacade() {
        System.out.println("Carregando e configurando jogo...");
        input = new Scanner(System.in);
        battlefield = new Battlefield();
        System.out.println("25%");
        deck = new Deck();
        System.out.println("50%");
        map = new Map();
        System.out.println("75%");
        trader = Trader.getInstance();
        System.out.println("100%\n\n");
    }
    
    public void startGame() {
        System.out.println("BrazilWar\n");
        
        playersQuantitySelectGUI(); 
        map.shuffleMap(playersQuantity);
        mapShowGUI();
    }
    
    private void playersQuantitySelectGUI() {
        System.out.print("Digite a quantidade de jogadores: ");
        playersQuantity = input.nextInt();
    }
        
    private void mapShowGUI() {
        System.out.println(this.map.listStatesToString());
    }
}
