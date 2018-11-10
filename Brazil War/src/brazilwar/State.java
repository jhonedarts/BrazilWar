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
public class State {
    
    private int soldiersQuantity;
    private String soldiersColor; //nessa logica o estado recebe uma marcação de que é o dono dele
                                //seria melhor por o jogador pra ter uma lista de estados ou dos id dos estados?
    private String initials;

    public State(String initials) {
        this.soldiersQuantity = 0;
        this.soldiersColor = "none";
        this.initials = initials;
    }

    public int getArmyQuantity() {
        return soldiersQuantity;
    }

    public void addSoldiers(int soldiers) {
        this.soldiersQuantity += soldiersQuantity;
    }
    
    public void removeSoldiers(int soldiers) {
        this.soldiersQuantity -= soldiersQuantity;
    }

    public String getArmyColor() {
        return soldiersColor;
    }

    public void setArmyColor(String soldiersColor) {
        this.soldiersColor = soldiersColor;
    }

    public String getInitials() {
        return initials;
    }
   
    
}
