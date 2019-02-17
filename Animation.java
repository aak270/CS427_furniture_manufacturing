/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package furniture_manufacturing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Aak
 */
public class Animation extends JFrame {
    Canvas canvas = new Canvas();
    
    Animation(){
        add(canvas);
        
        setTitle("Furniture Factory");
        setSize(1280, 720);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public void drawOrder(int[] types) throws InterruptedException{
        canvas.n_order = types.length;
        for(int i = types.length-1, j = 0; i >= 0; i--, j++){
            canvas.types[j] = types[i];
        }
        repaint();
        Thread.sleep(20);
    }
    
    public void drawBuild(int type, int w, int s, int n) throws InterruptedException{
        Thread.sleep(500);
        
        canvas.build_type = type;
        canvas.isBuild = true;
        canvas.n_order--;
        
        repaint();
        Thread.sleep(100);
        
        //move the resource
        canvas.isMove = true;
        
        canvas.wood_x = 110;
        canvas.wood_y = 181;
        canvas.iron_x = 110;
        canvas.iron_y = 235;
        canvas.nail_x = 110;
        canvas.nail_y = 268;
        repaint();
        Thread.sleep(100);
        
        //change the resources
        setResource(w, s, n);
        
        //resource movement animation
        canvas.wood_x = 160;
        canvas.wood_y = 183;
        canvas.iron_x = 165;
        canvas.iron_y = 237;
        canvas.nail_x = 165;
        canvas.nail_y = 272;
        repaint();
        Thread.sleep(100);
            
        canvas.wood_x = 210;
        canvas.wood_y = 185;
        canvas.iron_x = 225;
        canvas.iron_y = 239;
        canvas.nail_x = 220;
        canvas.nail_y = 276;
        repaint();
        Thread.sleep(100);
            
        canvas.wood_x = 265;
        canvas.wood_y = 189;
        canvas.iron_x = 290;
        canvas.iron_y = 241;
        canvas.nail_x = 280;
        canvas.nail_y = 278;
        repaint();
        Thread.sleep(100);
            
        canvas.wood_x = 320;
        canvas.wood_y = 193;
        canvas.iron_x = 360;
        canvas.iron_y = 243;
        canvas.nail_x = 340;
        canvas.nail_y = 280;
        repaint();
        Thread.sleep(100);
            
        canvas.wood_x = 380;
        canvas.wood_y = 199;
        canvas.iron_x = -200;
        canvas.iron_y = -200;
        canvas.nail_x = -200;
        canvas.nail_y = -200;
        repaint();
        Thread.sleep(100);
        
        canvas.wood_x = 440;
        canvas.wood_y = 205;
        repaint();
        Thread.sleep(100);
        
        canvas.isMove = false;
        
        //animate producing
        int i = 10;
        int max = (type == 1) ? 30 : 50;
        for(int j = 0; j < max; j++){
            canvas.factory_x = (int) (Math.random() * 2 * i + canvas.F_X - i);
            canvas.factory_y = (int) (Math.random() * 2 * i + canvas.F_Y - i);
            
            repaint();
            Thread.sleep(20);
        }
        
        canvas.isBuild = false;
        repaint();
    }
    
    public void setInventory(int c, int t) throws InterruptedException{
        canvas.n_chair = c;
        canvas.n_table = t;
        repaint();
        Thread.sleep(20);
    }
    
    public void setResource(int w, int i, int n) throws InterruptedException{
        canvas.n_wood = n;
        canvas.n_iron = i;
        canvas.n_nail = n;
        repaint();
        Thread.sleep(20);
    }
    
    class Canvas extends JPanel{
        private int n_wood = 0;
        private int n_iron = 0;
        private int n_nail = 0;
        
        private int wood_x = 0;
        private int wood_y = 0;
        private int iron_x = 0;
        private int iron_y = 0;
        private int nail_x = 0;
        private int nail_y = 0;
        private boolean isMove = false;
        
        private int n_chair = 0;
        private int n_table = 0;
        
        private int n_order = 0;
        private int[] types = new int[10];
        
        private int build_type = 0;
        private boolean isBuild = false;
        
        private final int F_X = 320;
        private final int F_Y = 144;        
        private int factory_x = F_X;
        private int factory_y = F_Y;
        private BufferedImage factory;
        
        private BufferedImage warehouse;
        private BufferedImage wood;
        private BufferedImage iron;
        private BufferedImage nail;
        
        private BufferedImage chair;
        private BufferedImage table;       
        private BufferedImage order_chair;
        private BufferedImage order_table;

        Canvas() {
            URL r1 = getClass().getResource("factory.png");
            URL r2 = getClass().getResource("warehouse.png");
            URL r3 = getClass().getResource("wood.png");
            URL r4 = getClass().getResource("iron.png");
            URL r5 = getClass().getResource("nail.png");
            URL r6 = getClass().getResource("chair.png");
            URL r7 = getClass().getResource("table.png");
            URL r8 = getClass().getResource("order_chair.png");
            URL r9 = getClass().getResource("order_table.png");
            try {
                factory = ImageIO.read(r1);
                warehouse = ImageIO.read(r2);
                wood = ImageIO.read(r3);
                iron = ImageIO.read(r4);
                nail = ImageIO.read(r5);
                chair = ImageIO.read(r6);
                table = ImageIO.read(r7);
                order_chair = ImageIO.read(r8);
                order_table = ImageIO.read(r9);
            } catch (IOException e) {
                e.printStackTrace();
            }   
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(warehouse, 10, 10, 260, 151, this);
            g.drawImage(wood, 10, 181, 49, 39, this);
            g.drawString(Integer.toString(n_wood), 80, 200);
            g.drawImage(iron, 10, 235, 54, 27, this);
            g.drawString(Integer.toString(n_iron), 80, 248);
            g.drawImage(nail, 10, 268, this);
            g.drawString(Integer.toString(n_nail), 80, 298);
            
            if(isMove){
                g.drawImage(wood, wood_x, wood_y, 49, 39, this);
                g.drawImage(iron, iron_x, iron_y, 54, 27, this);
                g.drawImage(nail, nail_x, nail_y, this);
            }
            
            g.drawImage(factory, factory_x, factory_y, 644, 403, this);
            g.drawImage(chair, getWidth()-200, getHeight()/2 - 100, this);
            g.drawString(Integer.toString(n_chair), getWidth()-80, getHeight()/2 - 50);
            g.drawImage(table, getWidth()-220, getHeight()/2 + 50, this);
            g.drawString(Integer.toString(n_table), getWidth()-80, getHeight()/2 + 80);
            
            int x = 0;
            for(int i = 0; i < n_order; i++){
                switch(types[i]){
                    case 1:
                        g.drawImage(order_chair, x, getHeight()-105, this);
                        break;
                    case 2:
                        g.drawImage(order_table, x, getHeight()-105, this);
                        break;
                }
                x += 86;
            }
            
            if(isBuild){
                switch(build_type){
                    case 1:
                        g.drawImage(order_chair, getWidth()/2 - 20, getHeight()/2 - 20, this);
                        break;
                    case 2:
                        g.drawImage(order_table, getWidth()/2 - 20, getHeight()/2 - 20, this);
                        break;
                }
            }
        }
    }
}
