import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Strada extends Lane{
    private ArrayList<Car> cars;
    private Color colorStreet = new Color(77, 77, 77);
    private Random random = new Random();

    public Strada(int y, int velocita, int nCars) {
        super(y);
        this.cars = new ArrayList<>();

        int ultimaX = 0;
        //creazione macchine
        for(int i = 0; i < nCars; i++){
            //creazione della macchina in posizione x casuale
            int spazio = 120 + random.nextInt(180);
            int x = ultimaX + spazio;
            Car c = new Car(x,y+5,80,30);

            cars.add(c);
            c.start();

            ultimaX = x;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(colorStreet);
        g.fillRect(0, y, 500, 40);

        for(Car c : cars)
            c.draw(g);
    }


    @Override
    public boolean eseguiCheck(pollo p) {
        //controllo se si trova nella corsia
        if(p.getPosY() < y || p.getPosY() >= y+40)
            return false;

        //controllo se collide con un'auto
        for (Car c : cars) {
            boolean collisioneX = (p.getPosX() + p.getW() > c.getPosX()) && (p.getPosX() < c.getPosX() + c.getW());
            boolean collisioneY = (p.getPosY() + p.getH() > c.getPosY()) && (p.getPosY() < c.getPosY() + c.getH());

            if (collisioneX && collisioneY) 
                return true; 
        }
        return false;
    }

    @Override
    public void reset(){
        //ferma i thread
        for(Car c : cars){
            c.stopCar();
            try {
                c.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //svuota la lista di macchine
        cars.clear();
    }
}
