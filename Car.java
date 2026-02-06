import java.awt.Graphics;
import java.awt.Color;

public class Car extends Thread {

    private int posX, posY;
    private int W, H;
    private int velocita = 200; 
    private Color color = Color.RED;
    private boolean running = true;
    private int panelWidth = 500; 

    public Car(int posX, int posY, int W, int H) {
        this.posX = posX;
        this.posY = posY;
        this.W = W;
        this.H = H;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(posX, posY, W, H);
        g.setColor(Color.BLACK);
        g.drawRect(posX, posY, W, H);
    }

    @Override
    public void run() {
        while (running) {
            posX += 5; 

            if (posX > panelWidth) {
                posX = -W;
            }

            try {
                sleep(velocita); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // Getters
    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public int getW() { return W; }
    public int getH() { return H; }
}
