import java.awt.Graphics;

public abstract class Lane {
    protected int y;
    protected int W = 500;
    protected int H = 40;
    protected boolean active = false; // serve per non far partire i thread mille volte
    
    public Lane(int y) {
        this.y = y;
    }

    public abstract void draw(Graphics g);

    //metodo universale che restituisce false se il pollo è salvo
    //creato per semplificare l'implementazione della loop
    public abstract boolean eseguiCheck(pollo p);

    public abstract void reset();

    // nuovi metodi per gestire i thread pian piano
    public abstract void attivaThread();
    
    public int getY(){ return y; }
    public int getH(){ return H; }
    public int getW(){ return W; }
}