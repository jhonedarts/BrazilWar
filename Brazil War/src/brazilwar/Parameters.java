/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * classe que contem os parametros do jogo
 * @author darts
 */
public class Parameters {
    private static Parameters instance;
    private HashMap<String, String[]> regions;
    private ArrayList<String> states;
    
    private String NORTH[] = {"AC", "AP", "AM", "PA", "RO", "RR"};
    private String NORTHEAST[] = {"AL", "BA", "CE", "MA", "PB", "PE", "PI", "RN", "SE"};
    private String MIDWEST[] = {"GO", "MT"};
    private String SOUTHEAST[] = {"ES", "MG", "SP", "RJ"};
    private String SOUTH[] = {"PR", "RS", "SC"};
    
    public static final String JOKER_SYMBOL = "CORINGA";
    public static final String SYMBOLS[] = {"TRIANGULO", "QUADRADO", "CIRCULO"};
    public static final int LIMIT_CARDS_IN_HANDS = 5;
    public static final int FIRST_TRADE = 4;
    public static final int TRADE_ACCUMULATOR = 2;
    
    private HashMap<String, String[]> frontiers;
    
    private Parameters() {
        initFrontiers();
        initStatesByRegion();
        initStates();//deve seguir initStatesByRegion()
    }
    
    public static Parameters getInstance(){
        if (instance == null){
            instance = new Parameters();
        }
        return instance;
    }
    
    private void initStates() {
        ArrayList<String> N = new ArrayList<String>(Arrays.asList(NORTH));
        ArrayList<String> NE = new ArrayList<>(Arrays.asList(NORTHEAST));
        ArrayList<String> MW = new ArrayList<>(Arrays.asList(MIDWEST));
        ArrayList<String> SE = new ArrayList<>(Arrays.asList(SOUTHEAST));
        ArrayList<String> S = new ArrayList<>(Arrays.asList(SOUTH));
        
        this.states = new ArrayList<>();
        states.addAll(N);
        states.addAll(NE);
        states.addAll(MW);
        states.addAll(SE);
        states.addAll(S);
    }
    
    /**
     * 
     * @return a colecao de siglas de todos estados
     */
    public String[] getStates() {
        return (String[]) this.states.toArray();
    }
    
    /**
     * 
     * @return quantidade total de estados
     */
    public int getStatesTotal(){
        return this.states.size();
    }
    
    /**
     * retorna um estado a partir da possicao na colecao
     * @param index indice
     * @return sigla do estado
     */
    public String getState(int index){
        return this.states.get(index);
    }
    
    /**
     * inicializa as regioes
     */
    private void initStatesByRegion() {
        this.regions = new HashMap<>();
        
        regions.put("NORTE", NORTH);
        regions.put("NORDESTE", NORTHEAST);
        regions.put("CENTRO-OESTE", MIDWEST);
        regions.put("SUDESTE", SOUTHEAST);
        regions.put("SUL", SOUTH);
    }
    
    /**
     * 
     * @return nomes das regioes
     */
    public String[] getRegions(){
        ArrayList<String> regionsNames = new ArrayList<>();
        for (String key : this.regions.keySet()){
            regionsNames.add(key);
        }
        return regionsNames.toArray(new String[regionsNames.size()]);
    }
    
    /**
     * 
     * @param region regiao
     * @return estados da regiao
     */
    public String[] getStatesByRegion(String region){
        return regions.get(region);
    }
    
    /**
     * 
     * @param state estado (sigla)
     * @return retorna a regiao a qual o estado pertence
     */
    public String getRegionByState(String state){
        for(String region : regions.keySet()){
            for(String st : regions.get(region)){
                if(st.equals(state)){
                    return region;
                }
            }
        }
        return null;
    }
    
    /**
     * 
     * @return quantidade total de regioes
     */
    public int getRegionsTotal(){
        return this.regions.size();
    }
    
    /**
     * retorna os estados que fazem fronteiras com o estado informado
     * @param initials sigla do estado
     * @return colecao de siglas de estados
     */
    public String[] getFrontiers(String initials) {
        return this.frontiers.get(initials);
    }
    
    /**
     * inicializa os estados por regiao
     */
    private void initFrontiers() {        
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
        
        this.frontiers = new HashMap<>();
        
        this.frontiers.put("AC", AC);
        this.frontiers.put("AP", AP);
        this.frontiers.put("PA", PA);
        this.frontiers.put("RO", RO);
        this.frontiers.put("RR", RR);
        this.frontiers.put("AL", AL);
        this.frontiers.put("BA", BA);
        this.frontiers.put("CE", CE);
        this.frontiers.put("MA", MA);
        this.frontiers.put("PB", PB);
        this.frontiers.put("PE", PE);
        this.frontiers.put("PI", PI);
        this.frontiers.put("RN", RN);
        this.frontiers.put("SE", SE);
        this.frontiers.put("GO", GO);
        this.frontiers.put("MT", MT);
        this.frontiers.put("ES", ES);
        this.frontiers.put("MG", MG);
        this.frontiers.put("SP", SP);
        this.frontiers.put("RJ", RJ);
        this.frontiers.put("PR", PR);
        this.frontiers.put("RS", RS);
        this.frontiers.put("SC", SC);
    }
    
}
