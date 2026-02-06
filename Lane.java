import java.awt.Graphics;

public abstract class Lane {
    protected int y;
    protected int W = 500;
    protected int H = 40;
    
    public Lane(int y) {
        this.y = y;
    }

    public abstract void draw(Graphics g);

    public int getY(){ return y; }
    public int getH(){ return H; }
    public int getW(){ return W; }
}

