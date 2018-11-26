/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.HashMap;

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
    
    public static String[] Frontiers(String initials) {
        HashMap<String, String[]> frontiers = new HashMap<>();
        String AC[] = {"AM"};
        String AP[] = {"PA"};
        String PA[] = {"AM", "RR", "MT", "AP", "MA", "GO"};
        String RO[] = {"PA", "AM"};
        String RR[] = {"AM", "MT"};
        String AL[] = {"BA", "PE", "SE"};
        String BA[] = {"GO", "PI", "PE", "AL", "SE", "ES", "MG"};
        String CE[] = {"PI", "PE", "PB", "RN"};
        String MA[] = {"PA", "GO", "PI"};
        String PB[] = {"RN", "CE", "PE"};
        String PE[] = {"PB", "CE", "PI", "BA", "AL"};
        String PI[] = {"CE", "MA", "BA", "PE"};
        String RN[] = {"CE", "PB"};
        String SE[] = {"AL", "BA"};
        String GO[] = {"PA", "MT", "MG", "BA", "MA"};
        String MT[] = {"GO", "PA", "AM", "RO", "PR", "SP", "MG"};
        String ES[] = {"BA", "MG", "RJ"};
        String MG[] = {"BA", "GO", "MT", "SP", "RJ", "ES"};
        String SP[] = {"MG", "MT", "PR", "RJ"};
        String RJ[] = {"ES", "MG", "SP"};
        String PR[] = {"SP", "MT", "SC"};
        String RS[] = {"SC"};
        String SC[] = {"PR", "RS"};
        
        frontiers.put("AC", AC);
        frontiers.put("AP", AP);
        frontiers.put("PA", PA);
        frontiers.put("RO", RO);
        frontiers.put("RR", RR);
        frontiers.put("AL", AL);
        frontiers.put("BA", BA);
        frontiers.put("CE", CE);
        frontiers.put("MA", MA);
        frontiers.put("PB", PB);
        frontiers.put("PE", PE);
        frontiers.put("PI", PI);
        frontiers.put("RN", RN);
        frontiers.put("SE", SE);
        frontiers.put("GO", GO);
        frontiers.put("MT", MT);
        frontiers.put("ES", ES);
        frontiers.put("MG", MG);
        frontiers.put("SP", SP);
        frontiers.put("RJ", RJ);
        frontiers.put("PR", PR);
        frontiers.put("RS", RS);
        frontiers.put("SC", SC);
        
        return frontiers.get(initials);
    }
}
