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
public class Card {
    private String symbol;
    private String name;

    public Card(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public Card(String symbol) {
        this.symbol = symbol;
        this.name = null;
    }
    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
    
}
