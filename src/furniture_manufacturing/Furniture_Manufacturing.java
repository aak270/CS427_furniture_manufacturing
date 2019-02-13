/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package furniture_manufacturing;

public class Furniture_Manufacturing {

    /**
     * Warehouse
     *
     */
    public static final int STOCK_WOOD = 100;
    public static final int STOCK_STEEL = 100;
    public static final int STOCK_NAIL = 100;

    public static Furniture[] myFurnitures;
    public static Order[] myOrders;
    public static Warehouse myWarehouse;

    public static Inventory myInventory = new Inventory(0, 0);

    /**
     * index [0: wood , 1: steel, 2: nail]
     */
    public static int[] chairMaterials = { 3, 5, 10 };
    public static int[] tableMaterials = { 5, 3, 15 };

    public static int simulation_runtime = 0;
    public static int[] intervalOrderTime = { 0, 10 };
    public static int numOfOrder = 50;

    public static void main(String[] args) {
        myOrders = generateOrder(numOfOrder);

        /**
         * Make a furniture type random 1, 0; 1 is chair 0 is table
         *
         */

        myWarehouse = new Warehouse(STOCK_WOOD, STOCK_STEEL, STOCK_NAIL);

        for (Order order : myOrders) {
            if (checkResources(order.furnitures)) {
                /**
                 * Wait 10 min
                 */
                for (Furniture furniture : order.furnitures) {
                    buildFurniture(furniture.type);
                }
                System.out.printf("Order %d Success with %d furniture(s)\n", order.id + 1, order.furnitures.length);
            } else {
                addResources();
                for (Furniture furniture : order.furnitures) {
                    buildFurniture(furniture.type);
                }
                System.out.printf("Order %d Success with %d furniture(s)\n", order.id + 1, order.furnitures.length);

            }
        }
        System.out.println("Total Runtime: " + simulation_runtime);
    }

    /**
     * function to generate the furniture
     *
     * @param numOfOrders number of limited orders at runtime
     * @return
     */
    public static Order[] generateOrder(int numOfOrders) {
        Order[] orders = new Order[numOfOrders];

        for (int i = 0; i < numOfOrders; i++) {
            int id = i;
            int time = (int) (Math.random() * intervalOrderTime[1] + intervalOrderTime[0]);
            Furniture[] furnitures = generateFurnitures(1);
            orders[i] = new Order(id, time, furnitures);/**
                                                         * 
                                                         */
        }
        return orders;
    }

    public static Furniture[] generateFurnitures(int numOfFurnitures) {
        Furniture[] furnitures = new Furniture[numOfFurnitures];
        for (int i = 0; i < numOfFurnitures; i++) {
            int type = (int) (Math.random() * 2 + 1);
            furnitures[i] = new Furniture(type);
        }
        return furnitures;
    }

    public static void buildFurniture(int type) {

        int currWood = 0;
        int currSteel = 0;
        int currNail = 0;
        int processTime = 0;

        switch (type) {
        case 1:
            currWood = myWarehouse.takeWood(chairMaterials[0]);
            currSteel = myWarehouse.takeSteel(chairMaterials[1]);
            currNail = myWarehouse.takeNail(chairMaterials[2]);
            processTime = 3;
            break;

        case 2:
            currWood = myWarehouse.takeWood(tableMaterials[0]);
            currSteel = myWarehouse.takeSteel(tableMaterials[1]);
            currNail = myWarehouse.takeNail(tableMaterials[2]);
            processTime = 5;
            break;
        }

        simulation_runtime += processTime;
    }

    public static boolean checkResources(Furniture[] furnitures) {
        int requireWood = 0;
        int requireSteel = 0;
        int requireNail = 0;
        boolean isEnough = true; // false: not enough materials

        for (Furniture furniture : furnitures) {
            switch (furniture.type) {
            case 1:
                requireWood += chairMaterials[0];
                requireSteel += chairMaterials[1];
                requireNail += chairMaterials[2];
                break;
            case 2:
                requireWood += tableMaterials[0];
                requireSteel += tableMaterials[1];
                requireNail += tableMaterials[2];
                break;
            }
        }
        if (requireWood > myWarehouse.getWood()) {
            isEnough = false;
            System.out.println("\nError: Not Enough Wood");
        }
        if (requireSteel > myWarehouse.getSteel()) {
            isEnough = false;
            System.out.println("\nError: Not Enough Steel");
        }
        if (requireNail > myWarehouse.getNail()) {
            isEnough = false;
            System.out.println("\nError: Not Enough Nail");
        }
        return isEnough;
    }

    public static void addResources() {
        double percentageFrom = 0.4;
        double percentageTo = 0.6;

        int randWood = rand(STOCK_WOOD * percentageFrom, STOCK_WOOD * percentageTo);
        int randSteel = rand(STOCK_STEEL * percentageFrom, STOCK_STEEL * percentageTo);
        int randNail = rand(STOCK_NAIL * percentageFrom, STOCK_NAIL * percentageTo);

        myWarehouse.addWoods(randWood);
        myWarehouse.addSteels(randSteel);
        myWarehouse.addNails(randNail);

        System.out.printf("Restocking Warehouse: Woods-%d | Steels-%d | Nails-%d\n", randWood, randSteel, randNail);
    }

    public static int rand(double from, double to) {
        return (int) (Math.random() * to + from);
    }
}

/**
 * 
 * 
 * public static boolean makeChair() { int currWood = myWarehouse.takeWood(3);
 * int currSteel = myWarehouse.takeSteel(5); int currNail =
 * myWarehouse.takeNail(10);
 * 
 * if (currWood == -1 || currSteel == -1 || currNail == -1) { if (currWood ==
 * -1) { System.out.println("Not Enough Wood"); } if (currSteel == -1) {
 * System.out.println("Not Enough Steel"); } if (currNail == -1) {
 * System.out.println("Not Enough Nail"); } return false; } else {
 * simulation_runtime += 3; return true; } }
 * 
 * public static boolean makeTable() { int currWood = myWarehouse.takeWood(5);
 * int currSteel = myWarehouse.takeSteel(3); int currNail =
 * myWarehouse.takeNail(15);
 * 
 * if (currWood == -1 || currSteel == -1 || currNail == -1) { if (currWood ==
 * -1) { System.out.println("Not Enough Wood"); } if (currSteel == -1) {
 * System.out.println("Not Enough Steel"); } if (currNail == -1) {
 * System.out.println("Not Enough Nail"); } return false; } else {
 * simulation_runtime += 5; return true; } }
 * 
 * 
 */