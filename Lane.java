import java.awt.Graphics;

public abstract class Lane {
    protected int y;
    protected int W = 500;
    protected int H = 40;
    
    public Lane(int y) {
        this.y = y;
    }

    public abstract void draw(Graphics g);

    //metodo universale che restituisce false se il pollo è salvo
    //creato per semplificare l'implementazione della loop
    public abstract boolean eseguiCheck(pollo p);

    public int getY(){ return y; }
    public int getH(){ return H; }
    public int getW(){ return W; }
}

