/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package furniture_manufacturing;

public class Furniture {
    int id;
    int type; //1: chair 2: table
    
    public Furniture(int id, int type) {
        this.id = id;
        this.type = type;
    }
}
