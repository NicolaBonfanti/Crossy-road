import java.util.ArrayList;

public class loop extends Thread{
    private pollo p;
    private ArrayList<Tronco> t = new ArrayList<Tronco>();
    private ArrayList<Car> c = new ArrayList<Car>();
    private MyPanel panel;

    public loop(pollo p, ArrayList<Tronco> t, ArrayList<Car> c, MyPanel panel) {
        this.p = p;
        this.t = t;
        this.c = c;
        this.panel = panel;
    }

    @Override
    public void run() {
        while(true)
        {
            MuoviPolloSulLegno();
        }
    }

    public void MuoviPolloSulLegno()
    {
        moveWithWood();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    //muove il pollo quando si trova su un tronco
    public void moveWithWood() {
        for (Tronco T : t) {
            boolean collisioneX = (p.getPosX() + p.getH() > T.getPosX()) && (p.getPosX() < T.getPosX() + T.getW());
            boolean collisioneY = (p.getPosY() + p.getH() > T.getPosY()) && (p.getPosY() < T.getPosY() + T.getH());
            if (collisioneX && collisioneY) {
                p.setPosX(p.getPosX() + 5);
                return; 
            }    
        }
    }

    
}
