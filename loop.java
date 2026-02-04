import java.util.ArrayList;

public class loop extends Thread{
    private pollo p;
    private ArrayList<Tronco> t = new ArrayList<Tronco>();
    private ArrayList<Car> c = new ArrayList<Car>();
    private MyPanel panel;
    private boolean end = false;
    public int score = 0;

    public loop(pollo p, ArrayList<Tronco> t, ArrayList<Car> c, MyPanel panel) {
        this.p = p;
        this.t = t;
        this.c = c;
        this.panel = panel;
    }

    @Override
    public void run() {
        while(!end)
        {
            MuoviPolloSulLegno();
            EndGameCheck();
        }
        panel.setGameOver(end);
        panel.repaint();
    }

    public void MuoviPolloSulLegno()
    {
        if(CheckOnWood())
            p.setPosX(p.getPosX() + 5);
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean CheckOnWood() {
        for (Tronco T : t) {
            boolean collisioneX = (p.getPosX() + p.getW() > T.getPosX()) && (p.getPosX() < T.getPosX() + T.getW());
            boolean collisioneY = (p.getPosY() + p.getH() > T.getPosY()) && (p.getPosY() < T.getPosY() + T.getH());
            if (collisioneX && collisioneY) {
                return true; 
            }    
        }
        return false;
    }

    public boolean CheckCarCollision()
    {
        for (Car car : c) {
            boolean collisioneX = (p.getPosX() + p.getW() > car.getPosX()) && (p.getPosX() < car.getPosX() + car.getW());
            boolean collisioneY = (p.getPosY() + p.getH() > car.getPosY()) && (p.getPosY() < car.getPosY() + car.getH());
            if (collisioneX && collisioneY) 
                return true; 
        }
        return false;
    }
    
    public boolean CheckOnWater()
    {
        boolean check = (p.getPosY() > 93 && p.getPosY() < 173);
        if(check == true && CheckOnWood() == false)
            return true;
        return false;
    }

    public void EndGameCheck(){
        if(CheckCarCollision())
            end = true;
        if(CheckOnWater())
            end = true;
        if(panel.timeline.gameOver == true)
            end = true;
    }

    public void setEnd(boolean end) {this.end = end;}
}
