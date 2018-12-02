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
    
    /**
     * Inicia e sortea o mapa para os jogadores
     */
    public void startGame() {
        System.out.println("BrazilWar\n");
        
        System.out.print("Digite a quantidade de jogadores: ");
        int playersQuantity = input.nextInt();
        selectColor(playersQuantity);
        map.shuffleMap(players);
        showMap();
    }
    
    /**
     * starta o jogo pra 4 players, pra testes
     */
    public void startGameByPass() {
        System.out.println("BrazilWar\n");
        
        String color = "verde";
        players.add(new Player(color));    
        color = "azul";
        players.add(new Player(color)); 
        map.shuffleMap(players);
        showMap();
    }
    
    /**
     * Solicita ao usuario a definição das cores dos players
     */
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
    private void showMap() {
        int armyQuantity;
        String color;
        String aux = "", armyQtt = "";
        aux = aux.concat("\nESTADO TROPAS  COR\n");
        for(String region : parameters.getRegions()){
            aux = aux.concat(region+"\n");
            for (String state : parameters.getStatesByRegion(region)){
                armyQuantity = map.getStateBattleUnitsQuantity(state);
                color = map.getStateBattleUnitsColor(state);
                armyQtt = (armyQuantity<10)? "0"+armyQuantity : ""+armyQuantity;
                aux = aux.concat("  "+state+"     "+armyQtt+"    "+color+"\n");
            }
        }
        System.out.println(aux+"\n");  
    }
    
    public void firstRound(){
        map.generateBattleUnitsPerRound(players);
        for (Player player : players) { 
            distribute(player);
        }
    }
    
    public void round(){
        map.generateBattleUnitsPerRound(players);
        for (Player player : players) {
            System.out.println("Jogador "+player.getColor());
            trade(player);            
            distribute(player);
            //attacks
            System.out.println("Selecione uma opção: ");
            System.out.println("(1) atacar\n(2) remanejar tropas\n(3) ver cards\n (4) não fazer nada");
            switch (input.nextInt()){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
            if(map.isGameOver()){
                System.out.println("O vencedor foi o Jogador "+map.getStateBattleUnitsColor(parameters.getState(0)));
                return;
            }
        }
    }
    
    private void trade(Player player){
        String comand;
        if(player.canTradeCards()){
            System.out.println("Deseja realizar a troca? (s/n)");
            comand = input.nextLine().trim();
            if(comand.equals("s")){
                while (true) {
                    System.out.println("Selecione os cards: (ex: 1,2,3)");
                    showCards(player);
                    System.out.println("(0) cancelar");
                    String comands[] = input.nextLine().split(",+");
                    if(!comands[0].equals("0")){                        
                        if(comands.length == Parameters.SYMBOLS.length){
                            ArrayList<Card> cards = new ArrayList<>();
                            cards.add(player.getCard(Integer.parseInt(comands[0].trim())));
                            cards.add(player.getCard(Integer.parseInt(comands[1].trim())));
                            cards.add(player.getCard(Integer.parseInt(comands[2].trim())));
                            if(trader.canTradeCards(cards)){
                                player.addBattleUnits(trader.getTrade(cards));
                                player.removeCards(cards);
                                break;
                            }else{
                                System.out.println("Entrada invalida");
                            } 
                        }else{
                            System.out.println("Entrada invalida");
                        }                        
                    }else{
                        break;
                    }
                }
            }
        }
    }
    
    private void distribute(Player player){
        System.out.println("Jogador "+player.getColor()+", você tem "+player.getBattleUnitsQttTotal()+" exércitos para distribuir:");
        for(String region : parameters.getRegions()){
            if(player.hasBattleUnitsByRegion(region)){
                System.out.println("sendo "+player.getBattleUnitsByRegionQtt(region)+" para distribuir na região "+region);
            }
        }
        System.out.println("exemplo MA-2, SP-4");

        do{
            String comand[] = input.nextLine().split("\\s+ |,+|-+");
            
            for (int j = 0; j < comand.length-1; j+=2) {
                String state = comand[j].toUpperCase().trim();
                String region = parameters.getRegionByState(state);
                int soldiersQtt = Integer.parseInt(comand[j+1]);
                
                if(map.getStateBattleUnitsColor(state).equals(player.getColor()) && soldiersQtt <= player.getBattleUnitsQttTotal()){
                    map.addSoldiers(state, soldiersQtt);
                    if(region != null && player.hasBattleUnitsByRegion(region)){                            
                        if(player.getBattleUnitsByRegionQtt(region) <= soldiersQtt){
                            soldiersQtt -= player.getBattleUnitsByRegionQtt(region);
                            player.removeSoldiersByRegion(region, player.getBattleUnitsByRegionQtt(region));
                        }else{
                            player.removeSoldiersByRegion(region, soldiersQtt);
                            soldiersQtt = 0;
                        }   
                    }
                    player.removeSoldiers(soldiersQtt);
                }else{
                    System.out.println("Entrada invalida");
                }
            }
            if(player.getBattleUnitsQttTotal() > 0){
                System.out.println("Ainda tem soldados, mande-os pra guerra\n"+player.getBattleUnitsQttTotal()+" soldados restantes");
                for(String region : parameters.getRegions()){
                    if(player.hasBattleUnitsByRegion(region)){
                        System.out.println("sendo "+player.getBattleUnitsByRegionQtt(region)+" para distribuir na região "+region);
                    }
                }
            }
        }while(player.getBattleUnitsQttTotal() > 0);
        showMap();
    }
    
    private void showCards(Player player){
        for (int i = 0; i < player.getCardsQtt(); i++) {
            System.out.println("card "+(i+1)+": "+ player.getCard(i).getName()+ " "+player.getCard(i).getSymbol());
        }
        
    }
}
