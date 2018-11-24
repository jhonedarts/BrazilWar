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
public class Parameters {
    
    public static final String NORTH[] = {"AC", "AP", "AM", "PA", "RO", "RR"};
    public static final String NORTHEAST[] = {"AL", "BA", "CE", "MA", "PB", "PE", "PI", "RN", "SE"};
    public static final String MIDWEST[] = {"GO", "MT"};
    public static final String SOUTHEAST[] = {"ES", "MG", "SP", "RJ"};
    public static final String SOUTH[] = {"PR", "RS", "SC"};
    public static final String STATES[] = {
    /* NORTE*/      "AC", "AP", "AM", "PA", "RO", "RR",
    /* NORDESTE*/   "AL", "BA", "CE", "MA", "PB", "PE", "PI", "RN", "SE",  
    /* CENTRO-OESTE*/"GO", "MT", 
    /* SUDESTE*/    "ES", "MG", "SP", "RJ",
    /* SUL*/        "PR", "RS", "SC"};
    public static final String REGIONS[] = {"Norte", "Nordeste", "Centro-Oeste", "Sudeste", "Sul"};
    
    public static final String JOKER_SYMBOL = "CORINGA";
    
    public static final String SYMBOLS[] = {"TRIANGULO", "QUADRADO", "CIRCULO"};
    
    public static final int LIMIT_CARDS_IN_HANDS = 5;
    public static final int FIRST_TRADE = 4;
    public static final int TRADE_ACCUMULATOR = 2;
    public static final String COLOR[] = {"VERMELHO", "AZUL", "VERDE", "BRANCO", "PRETO", "AMARELO"};;
}
