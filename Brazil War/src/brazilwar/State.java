/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Representa o estado
 * @author darts
 */
public class State {
    
    private ArrayList<BattleUnit> battleUnits;
    private String battleUnitsColor; //nessa logica o estado recebe uma marcação de que é o dono dele
                                //seria melhor por o jogador pra ter uma lista de estados ou dos id dos estados?
    private String initials;
    private HashSet<String> frontiers;

    public State(String initials) {
        this.battleUnits = new ArrayList<>();
        this.frontiers = new HashSet<>();
        this.battleUnitsColor = "none";
        this.initials = initials;
    }

    /**
     * 
     * @return Quantidade de tropas que estao dno estado
     */
    public int getArmyQuantity() {
        return battleUnits.size();
    }

    /**
     * adiciona uma unidade
     * @param battleUnit
     */
    public void addBattleUnit(BattleUnit battleUnit) {
        this.battleUnits.add(battleUnit);
    }
    
    /**
     * adiciona uma colecao de unidades
     * @param battleUnits
     */
    public void addBattleUnits(ArrayList<BattleUnit> battleUnits) {
        this.battleUnits.addAll(battleUnits);
    }

    /**
     * adiciona soldados 
     * @param qtt quantidade de soldados
     */
    public void addSoldiers(int qtt) {
        for (int i = 0; i < qtt; i++) {
            this.battleUnits.add(new Soldier());
        }
    }
    
    /**
     * retorna 0-3 unidades dependendo da disponibilidade e condicao 
     * de permanecia de uma unidade no territorio
     * @return colecao de unidade que vao a guerra
     */
    public ArrayList<BattleUnit> getBattleUnitsForAttack(){
        ArrayList<BattleUnit> aux = new ArrayList<>();
        if (this.battleUnits.size() >= 4){
            aux.add(this.battleUnits.get(0));
            aux.add(this.battleUnits.get(1));
            aux.add(this.battleUnits.get(2));
        } else if (this.battleUnits.size() == 3){
            aux.add(this.battleUnits.get(0));
            aux.add(this.battleUnits.get(1));
        } else if (this.battleUnits.size() == 2){
            aux.add(this.battleUnits.get(0));
        }
        return aux;
    }
    
    /**
     * retorna 0-3 unidades dependendo da disponibilidade
     * @return colecao de unidade que vao a guerra defender
     */
    public ArrayList<BattleUnit> getBattleUnitsForDefense(){
        ArrayList<BattleUnit> aux = new ArrayList<>();
        if (this.battleUnits.size() >= 3){
            aux.add(this.battleUnits.get(0));
            aux.add(this.battleUnits.get(1));
            aux.add(this.battleUnits.get(2));
        } else if (this.battleUnits.size() == 2){
            aux.add(this.battleUnits.get(0));
            aux.add(this.battleUnits.get(1));
        } else if (this.battleUnits.size() == 1){
            aux.add(this.battleUnits.get(0));
        }
        return aux;
    }
    
    /**
     * remove uma unidade 
     * @param battleUnit
     */
    public void removeBattleUnit(BattleUnit battleUnit) {
        this.battleUnits.remove(battleUnit);
    }
    
    /**
     * retorna uma unidade removendo-a do estado
     * @return
     */
    public BattleUnit popBattleUnit() {
        if(this.battleUnits.size() < 1){
            return null;
        }
        BattleUnit bu = this.battleUnits.get(0);
        this.battleUnits.remove(0);
        return bu;
    }

    /**
     * 
     * @return cor do estado que referencia a cor do jogador 
     * de posse das unidades que estao no estado
     */
    public String getArmyColor() {
        return battleUnitsColor;
    }

    /**
     * muda a cor do estado, util quando o estado tem um novo dono
     * @param battleUnitsColor nova cor
     */
    public void setArmyColor(String battleUnitsColor) {
        this.battleUnitsColor = battleUnitsColor;
    }

    /**
     * retorna sigla do estado
     * @return
     */
    public String getInitials() {
        return initials;
    }

    /**
     * remove unidades do estado
     * @param qtt quantidade de unidades
     */
    public void removeBattleUnits(int qtt) {
        if(this.battleUnits.size() >= qtt){
            for (int i = 0; i < qtt; i++) {
                this.battleUnits.remove(0);
            }       
        }
    }
}
