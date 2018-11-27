/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brazilwar;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
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

    public int getArmyQuantity() {
        return battleUnits.size();
    }

    public void addBattleUnit(BattleUnit battleUnit) {
        this.battleUnits.add(battleUnit);
    }
    
    public void addBattleUnits(ArrayList<BattleUnit> battleUnits) {
        this.battleUnits.addAll(battleUnits);
    }
    
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
    
    public void removeBattleUnit(BattleUnit battleUnit) {
        this.battleUnits.remove(battleUnit);
    }
    
    public BattleUnit popBattleUnit() {
        if(this.battleUnits.size() < 1){
            return null;
        }
        BattleUnit bu = this.battleUnits.get(0);
        this.battleUnits.remove(0);
        return bu;
    }

    public String getArmyColor() {
        return battleUnitsColor;
    }

    public void setArmyColor(String battleUnitsColor) {
        this.battleUnitsColor = battleUnitsColor;
    }

    public String getInitials() {
        return initials;
    }
   
    public boolean isMyfrontier(String key){
        return false;
    }
}
