/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package furniture_manufacturing;

public class Inventory {
    int numChair;
    int numTable;

    public Inventory(int numChair, int numTable) {
        this.numChair = numChair;
        this.numTable = numTable;
    }

    public void store(int type, int num) {
        if (type == 1) {
            this.addChair(num);
        } else if (type == 2) {
            this.addTable(num);
        }
    }

    public void addChair(int nChair) {
        this.numChair += nChair;
    }

    public void addTable(int nTable) {
        this.numTable += nTable;
    }
}