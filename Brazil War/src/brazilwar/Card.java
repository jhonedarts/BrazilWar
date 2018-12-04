/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

/**
 * Representa as cartas do jogo (exceto as de objetivos)
 * @author darts
 */
public class Card {
    private String symbol;
    private String name;

    /**
     * construtor
     * @param symbol simbolo da carta
     * @param name nome do estado
     */
    public Card(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    /**
     * para o tipo coringa que nao precisa de um valor de estado
     * @param symbol
     */
    public Card(String symbol) {
        this.symbol = symbol;
        this.name = null;
    }

    /**
     * 
     * @return o simbolo da carta
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * 
     * @return o nome do estado da carta
     */
    public String getName() {
        return name;
    }
    
}
