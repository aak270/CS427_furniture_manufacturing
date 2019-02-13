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
    }
    
    class Canvas extends JPanel{
        private int n_wood = 0;
        private int n_iron = 0;
        private int n_nail = 0;
               
        private int n_chair = 0;
        private int n_table = 0;
        
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

        Canvas() {
            URL r1 = getClass().getResource("factory.png");
            URL r2 = getClass().getResource("warehouse.png");
            URL r3 = getClass().getResource("wood.png");
            URL r4 = getClass().getResource("iron.png");
            URL r5 = getClass().getResource("nail.png");
            URL r6 = getClass().getResource("chair.png");
            URL r7 = getClass().getResource("table.png");
            try {
                factory = ImageIO.read(r1);
                warehouse = ImageIO.read(r2);
                wood = ImageIO.read(r3);
                iron = ImageIO.read(r4);
                nail = ImageIO.read(r5);
                chair = ImageIO.read(r6);
                table = ImageIO.read(r7);
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
            g.drawImage(factory, factory_x, factory_y, 644, 423, this);
            g.drawImage(chair, getWidth()-200, getHeight()/2 - 100, 82, 109, this);
            g.drawString(Integer.toString(n_chair), getWidth()-80, getHeight()/2 - 50);
            g.drawImage(table, getWidth()-220, getHeight()/2 + 50, 116, 69, this);
            g.drawString(Integer.toString(n_table), getWidth()-80, getHeight()/2 + 80);
        }
        
        public void setResource(int w, int i, int n){
            n_wood = n;
            n_iron = i;
            n_nail = n;
            repaint();
        }
        
        public void addInventory(int c){
            switch (c) {
            case 1:
                n_chair++;
                break;
            case 2:
                n_table++;
                break;
            }
            repaint();
        }
        
        public void shake(){
            int i = 10;
            factory_x = (int) (Math.random() * 2 * i + F_X - i);
            factory_y = (int) (Math.random() * 2 * i + F_Y - i);
            repaint();
        }
    }
}
