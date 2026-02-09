import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Fiume extends Lane{
    private ArrayList<Tronco> tronchi;
    private Color colorWater = new Color(173, 216, 230);
    private Random random = new Random();

    public Fiume(int y, int nTronchi){
        super(y);
        this.tronchi = new ArrayList<>();
        int ultimaX = 0;

        for(int i = 0; i < nTronchi; i++){
            int spazio = 120 + random.nextInt(200);
            int x = ultimaX + spazio;

            Tronco t  = new Tronco(x, y + 3, 100, 34);
            tronchi.add(t);
            t.start();

            ultimaX = x;
        }
    }

    @Override
    public void draw(Graphics g){
        g.setColor(colorWater);
        g.fillRect(0,y,500,40);

        for(Tronco t : tronchi)
            t.draw(g);
    }

    @Override
    public boolean eseguiCheck(pollo p) {
        //controllo se si trova nella corsia
        if(p.getPosY() < y || p.getPosY() >= y + 40)
                return false;

        //controllo se si trova su un tronco
        for (Tronco t : tronchi) {
            boolean collisioneX = (p.getPosX() + p.getW() > t.getPosX()) && (p.getPosX() < t.getPosX() + t.getW());
            boolean collisioneY = (p.getPosY() + p.getH() > t.getPosY()) && (p.getPosY() < t.getPosY() + t.getH());

            if (collisioneX && collisioneY) 
            {
                p.setPosX(p.getPosX() + 5);
                return false; 
            }
            
        }
        return true;
    }

    @Override
    public void reset(){
        for(Tronco t : tronchi){
            t.stopTronco();
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        tronchi.clear();
    }
}
