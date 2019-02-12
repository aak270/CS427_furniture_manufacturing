/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package furniture_manufacturing;

import java.util.Random;

public class Furniture_Manufacturing {

    /**
     * Warehouse
     *
     */
    public static final int STOCK_WOOD = 100;
    public static final int STOCK_STEEL = 100;
    public static final int STOCK_NAIL = 200000;
    public static Furniture[] myFurnitures;
    public static Warehouse myWarehouse;
    
    public static int simulation_runtime = 0;
    
    public static void main(String[] args) {
        /**
         * Make a furniture type random 1, 0; 1 is chair 0 is table
         *
         */
        myWarehouse = new Warehouse(STOCK_WOOD, STOCK_STEEL, STOCK_NAIL);
        myFurnitures = generateOrder(50);
        for (Furniture furniture : myFurnitures) {
            boolean isSuccess;
            if (furniture.type == 1) {
                isSuccess = makeChair();
            } else {
                isSuccess = makeTable();
            }
            if (isSuccess) {
                System.out.printf("Success %s %s \n", furniture.id+1, furniture.type == 1 ? "Chair" : "Table");
            } else {
                break;
            }
        }
        
        System.out.println("Total Runtime: " + simulation_runtime);
    }

    /**
     * function to generate the furniture
     *
     * @param numOfOrder number of limited orders at runtime
     * @return
     */
    public static Furniture[] generateOrder(int numOfOrder) {
        Furniture[] furnitures = new Furniture[numOfOrder];
        
        for (int i = 0; i < numOfOrder; i++) {
            int id = i;
            int type = (int) (Math.random() * 2 + 1);
            furnitures[i] = new Furniture(id, type);
        }
        return furnitures;
    }
    
    public static boolean makeChair() {
        int currWood = myWarehouse.takeWood(3);
        int currSteel = myWarehouse.takeSteel(5);
        int currNail = myWarehouse.takeNail(10);
        
        if (currWood == -1 || currSteel == -1 || currNail == -1 ) {
            if (currWood == -1) {
                System.out.println("Not Enough Wood");
            }
            if (currSteel == -1) {
                System.out.println("Not Enough Steel");
            }
            if (currNail == -1) {
                System.out.println("Not Enough Nail");
            }
            return false;
        } else {
            simulation_runtime += 3;
            return true;
        }
        
    }
    
    
    public static boolean makeTable() {
        int currWood = myWarehouse.takeWood(5);
        int currSteel = myWarehouse.takeSteel(3);
        int currNail = myWarehouse.takeNail(15);
        
        if (currWood == -1 || currSteel == -1 || currNail == -1 ) {
            if (currWood == -1) {
                System.out.println("Not Enough Wood");
            }
            if (currSteel == -1) {
                System.out.println("Not Enough Steel");
            }
            if (currNail == -1) {
                System.out.println("Not Enough Nail");
            }
            return false;
        } else {
            simulation_runtime += 5;
            return true;
        }
    }
}
