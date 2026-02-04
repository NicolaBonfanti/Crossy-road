import java.awt.Color;
import java.awt.Graphics;

public class pollo {

    private int posX, posY;
    private int W, H;
    private Color color = Color.WHITE;

    public pollo(int posX, int posY, int W, int H) {
        this.posX = posX;
        this.posY = posY;
        this.W = W;
        this.H = H;
    }

    //disegna il pollo
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(posX, posY, W, H);
        g.setColor(Color.BLACK);
        g.drawRect(posX, posY, W, H);
    }

    //muove il pollo con un input
    public void move(int dx, int dy, int panelWidth, int panelHeight) {
        posX += dx;
        posY += dy;

        if (posX < 0) posX = 0;
        if (posY < 0) posY = 0;
        if (posX + W > panelWidth) posX = panelWidth - W;
        if (posY + H > panelHeight) posY = panelHeight - H;
    }

    // Getters
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public int getW() { return W; }
    public int getH() { return H; }
    public void setPosX(int newX) {posX = newX;}
    public void setPosY(int newY) {posY = newY;}
}
