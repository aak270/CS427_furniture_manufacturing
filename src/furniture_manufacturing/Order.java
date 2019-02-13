/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package furniture_manufacturing;

public class Order {
    int id;
    int time;
    Furniture[] furnitures;

    public Order(int id, int time, Furniture[] furnitures) {
        this.id = id;
        this.time = time;
        this.furnitures = furnitures;
    }
}
