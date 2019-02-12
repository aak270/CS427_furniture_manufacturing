/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package furniture_manufacturing;

public class Warehouse {
    private int wood;
    private int steel;
    private int nail;
    
    public Warehouse(int wood, int steel, int nail) {
        this.nail = nail;
        this.steel = steel;
        this.wood = wood;
    }
    
    
    public int getWood() {
        return wood;
    }
    public void setWood(int wood){
        this.wood = wood;
    }
    public int getSteel() {
        return steel;
    }
    public void setSteel(int steel) {
        this.steel = steel;
    }
    public int getNail() {
        return nail;
    }
    public void setNail(int nail) {
        this.nail = nail;
    }
    
    public int takeWood(int wood) {
        if (this.wood - wood >= 0){
            this.wood -= wood;
            return wood;
        } else {
            return -1;  //Not enough mat
        }
    }
    public int takeSteel(int steel) {
        if (this.steel - steel >= 0){
            this.steel -= steel;
            return steel;
        } else {
            return -1;  //Not enough mat
        }
    }
    
    public int takeNail(int nail) {
        if (this.nail - nail >= 0){
            this.nail -= nail;
            return nail;
        } else {
            return -1;  //Not enough mat
        }
    }
}
