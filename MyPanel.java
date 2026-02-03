import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.awt.event.MouseMotionAdapter; 

class MyPanel extends JPanel {

    ArrayList<Car> cars = new ArrayList<Car>();

    public pollo p = new pollo(250, 470, 30, 30);

    public int x = 1;
    public int y = 1;
    private Color colorGround = new Color(34, 139, 34);
    private Color colorStreet = new Color(77, 77, 77);
    private Color colorWater = new Color(173, 216, 230);

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        MyMouseAdapter mouse = new MyMouseAdapter(this);
        addMouseListener(mouse);
        
        MyKeyboardAdapter key = new MyKeyboardAdapter(this);
        addKeyListener(key);
        
        inizializzazioneGioco();
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        //groung
        g.setColor(colorGround);
        g.fillRect(0,390,500,120);
        g.drawRect(0,390,500,120);

        //Street
        g.setColor(colorStreet);
        g.fillRect(0,210,500,180);
        g.drawRect(0,210,500,180);

        //groung
        g.setColor(colorGround);
        g.fillRect(0,173,500,37);
        g.drawRect(0,173,500,37);

        //groung
        g.setColor(colorWater);
        g.fillRect(0,90,500,83);
        g.drawRect(0,90,500,83);

        //pollo
        p.draw(g);

        if (cars != null) {
        for (Car car : cars) {
            car.draw(g);
    }
}

        
    }  

    void inizializzazioneGioco() 
    {

        for (int i = 0; i < 3; i++) {
            Car car = new Car(i * 200, 355, 100,30,500);
            car.start(); 
            cars.add(car);
        }

        for (int i = 0; i < 3; i++) {
            int[] posizioniX = {100, 300, 500};
            Car car = new Car(posizioniX[i], 285, 100,30,500);
            car.start(); 
            cars.add(car);
        }

        for (int i = 0; i < 3; i++) {
            Car car = new Car(i * 200, 215, 100,30,500);
            car.start(); 
            cars.add(car);
        }
    }
}