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
 * @author lzricardo & darts
 */
public class GameFacade {
    private Parameters parameters = Parameters.getInstance();
    private Scanner input;
    private Battlefield battlefield;
    private Deck deck;
    private Map map;
    private Trader trader;
    private ArrayList<Player> players;
    private boolean tips = true;
    
    /**
     * inicializa os componentes principais do jogo war 1
     */
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
     * starta o jogo pra 2 players, para testes
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
    
    public void enableTips(boolean en){
        this.tips = en;
    }
    
    /**
     * Solicita ao usuario a definição das cores dos players
     * @param playersQuatity quantidade de jogadores
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
     * lista todos os estados por sigla, quatidade de tropas 
     * e cor agrupados por região
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
    
    /**
     * Primeira rodada, so distribuição de tropas
     */
    public void firstRound(){
        map.generateBattleUnitsPerRound(players);
        for (Player player : players) { 
            distribute(player);
        }
    }
    
    /**
     * Rodada com trocas de cartas, distribuição de tropas, 
     * e escolhas de ataque, remanejar tropas e ver as cartas
     */
    public void round(){
        map.generateBattleUnitsPerRound(players);
        for (Player player : players) { 
            trade(player);            
            distribute(player);
            //attacks
            int comand;
            do{
                System.out.println("\nJogador "+player.getColor());
                System.out.println("Selecione uma opção: ");
                System.out.println("(1) atacar\n(2) remanejar tropas\n(3) ver cards\n(4) encerrar jogada");
                comand = input.nextInt();
                switch (comand){
                    case 1:
                        attack(player);
                        break;
                    case 2:
                        reallocate(player);
                        break;
                    case 3:
                        showCards(player);
                        break;
                    default:
                        comand = 0;//break while
                        break;
                }
                if(map.isGameOver()){
                    System.out.println("O vencedor foi o Jogador "+map.getStateBattleUnitsColor(parameters.getState(0)));
                    return;
                }
            }while(comand > 0 && comand <= 3);
        }
    }
    
    /**
     * Verica se @player tem a possibilidade de realizar uma troca
     * Se sim, pergunta se deseja trocar e quais cartas
     * Se as cartas informadas validarem uma troca, player recebe as tropas
     * @param player
     */
    private void trade(Player player){
        System.out.println("\nJogador "+player.getColor());
        String comand;
        if(player.canTradeCards()){
            System.out.println("Deseja realizar a troca? (s/n)");
            comand = input.next().trim();
            if(comand.equals("s")){
                while (true) {
                    System.out.println("Selecione os cards:"+((tips)?"(ex: 1,2,3)":""));
                    showCards(player);
                    System.out.println("(0) cancelar");
                    String comands[] = input.next().split(",");
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
    
    /**
     * Chama os metodos pra gerar as tropas por rodada
     * Player decide onde coloca-las 
     * @param player
     */
    private void distribute(Player player){
        System.out.println("Você tem "+player.getBattleUnitsQttTotal()+" exércitos para distribuir:");
        for(String region : parameters.getRegions()){
            if(player.hasBattleUnitsByRegion(region)){
                System.out.println("sendo "+player.getBattleUnitsByRegionQtt(region)+" para distribuir na região "+region);
            }
        }
        if(tips)System.out.println("exemplo MA-2, SP-4");

        do{
            String comand[] = input.nextLine().split("-|,|\\s");
            
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
    
    /**
     * Pergunta qual as coordenadas do ataque, valida a posse e fronteira
     * Chama o @battlefield para realizar o combate
     * @param player
     */
    private void attack(Player player){
        System.out.println("\nJogador "+player.getColor());
        String answer ="s";
        System.out.println("Informe seu ataque:"+((tips)?" \nex: \"SP-MG\", SP ataca MG, e vc deve ter posse de SP":""));
        String comand[] = input.next().split("-|,");
        while(answer.equals("s")){    
            System.out.println("lenght "+comand.length);
            if(comand.length == 2){
                String attacker = comand[0].trim().toUpperCase();
                String defender = comand[1].trim().toUpperCase();
                String attackerColor = map.getStateBattleUnitsColor(attacker);
                String defenderColor = map.getStateBattleUnitsColor(defender);
                int attackerQtt;
                int defenderQtt;
                if(attackerColor != null && defenderColor != null){
                    //se o jogador tem o estado..
                    if(player.getColor().equals(attackerColor) && map.hasFrontier(attacker, defender)){
                        int result[] = battlefield.combat(map.getSoldiersForAttack(attacker), map.getSoldiersForDefense(defender));
                        System.out.println(result[0]+" Soldados vermelhos foram derrotados");
                        System.out.println(result[1]+"  Soldados amarelos foram derrotados");
                        map.removeBattleUnits(attacker, result[0]);
                        map.removeBattleUnits(defender, result[1]);
                        attackerQtt = map.getStateBattleUnitsQuantity(attacker);
                        defenderQtt = map.getStateBattleUnitsQuantity(defender);
                        System.out.println("Situação atual: "+attacker+"-"+attackerQtt+" "+defender+"-"+defenderQtt);
                        if (defenderQtt == 0) {
                            System.out.println("As tropas Vermelhas empunham sua bandeira sob o teritorio inimigo!");
                            attackerQtt = map.getStateBattleUnitsQuantity(attacker);
                            if(attackerQtt == 2){//move um pro novo territorio
                                map.moveBattleUnits(attacker, defender, 1);
                                System.out.println("1 tropa foi enviada ao novo território!");
                            }else{
                                System.out.println("Com quantas tropas deseja avancar"+((tips)?" ex: 1, 2 ou 3":""));
                                int moveQtt = input.nextInt();
                                map.moveBattleUnits(attacker, defender, moveQtt);
                                System.out.println(moveQtt+" tropas foram enviadas ao novo território!");
                            }
                            player.addCard(deck.nextCard());
                            answer = "n";
                        }else{
                            System.out.println("Deseja continuar o ataque? (s/n)");
                            answer = input.next();
                        }                        
                    }else{
                        System.out.println("Entrada invalida!");
                        System.out.println("Deseja continuar o ataque? (s/n)");
                        answer = input.next();
                    }
                }
            }
        }
    }
    
    /**
     * Permite a movimentação das tropas entre os estados pertencentes ao @player
     * @param player
     */
    private void reallocate(Player player) {
        System.out.println("\nJogador "+player.getColor());
        String answer = "s";
        String comands[];
        while (answer.equals("s")){
            System.out.println("Informe o remanejamento:"+((tips)?"\nex: \"SE-BA-10,MG-BA-5\", move 10 soldados de SE para BA e 5 de MG para BA (nao use espacos)":""));
            comands = input.next().split("-|,");
            
            if(comands.length % 3 == 0){
                for (int i = 0; i < comands.length; i+=3) {
                    String stateA = comands[i].trim().toUpperCase();
                    String stateB = comands[i+1].trim().toUpperCase();
                    if(map.hasFrontier(stateA, stateB) && map.getStateBattleUnitsQuantity(stateA) > Integer.parseInt(comands[i+2])){
                        map.moveBattleUnits(stateA, stateB, Integer.parseInt(comands[i+2]));
                        System.out.println("Moveu "+Integer.parseInt(comands[i+2])+" tropas de "+stateA+" para "+stateB);
                    }else{
                        System.out.println("Entrada invalida!");                        
                    }
                }
            }else{
                System.out.println("Entrada invalida!");
            }
            System.out.println("Deseja continuar o remanejamento? (s/n)");
            answer = input.next();
        }
    }
    
    /**
     * Printa as cartas do @player
     * @param player
     */
    private void showCards(Player player){
        System.out.println("");
        for (int i = 0; i < player.getCardsQtt(); i++) {
            System.out.println("card "+(i+1)+": "+ player.getCard(i).getName()+ " "+player.getCard(i).getSymbol());
        }
        if (player.getCardsQtt()==0) {
            System.out.println("Voce não tem cartas!");
        }
    }
}
