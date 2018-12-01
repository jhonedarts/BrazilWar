/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author lzricardo
 */
public class GameFacade {
    private Parameters parameters = Parameters.getInstance();
    private Scanner input;
    private Battlefield battlefield;
    private Deck deck;
    private Map map;
    private Trader trader;
    private ArrayList<Player> players;
    
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
        players = new ArrayList<>();
        System.out.println("100%\n");
    }
    
    public void startGame() {
        System.out.println("BrazilWar\n");
        
        System.out.print("Digite a quantidade de jogadores: ");
        int playersQuantity = input.nextInt();
        selectColor(playersQuantity);
        map.shuffleMap(players);
        mapShowGUI();
    }
        
    private void selectColor(int playersQuatity){
        System.out.print("\nSeleção de cores\n");
        for(int i=0; i < playersQuatity; i++){
            System.out.print("Player "+i+":\n");
            String color = input.next();
            players.add(new Player(color));
        }
        
    }
    
    /**
     * lista todos os estados por sigla, quatidade de tropas e cor
     */
    private void mapShowGUI() {
        int armyQuantity;
        String color;
        String aux = "", aux2 = "";
        aux = aux.concat("\nESTADO TROPAS COR\n");
        for(int i=0; i < parameters.getStatesTotal(); i++){
            armyQuantity = map.getStateBattleUnitsQuantity(parameters.getState(i));
            color = map.getStateBattleUnitsColor(parameters.getState(i));
            aux2 = (armyQuantity>10)? "0"+armyQuantity : ""+armyQuantity;
            aux = aux.concat("  "+parameters.getState(i)+"     "+aux2+"    "+color+"\n");
        }
        System.out.println(aux);
    
    }
}
