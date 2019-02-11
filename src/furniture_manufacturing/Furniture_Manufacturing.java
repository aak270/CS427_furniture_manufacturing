/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package furniture_manufacturing;

public class Furniture_Manufacturing {
    
    /**
     * Warehouse
     **/
    public static final int STOCK_NAIL = 100;
    public static final int STOCK_WOOD = 100;
    public static final int STOCK_STEEL = 100;


    public static void main(String[] args) {
        int currentStock = 0;
        int productionNum = 10;


        furniture[] furnitures = generateFurniture(productionNum); 
        
    }

}
