import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeLine {

    private MyPanel panel;
    private Timer timer;
    private int delay;
    private int decrementa;
    private int previousY;
    public int LaneW;   // larghezza corrente della barra
    public boolean gameOver = false;

    // Costruttore
    public TimeLine(MyPanel panel, int delay, int decrementa) {
        this.panel = panel;
        this.delay = delay;
        this.decrementa = decrementa;
        this.previousY = panel.p.getPosY();

        this.LaneW = 0; // sarà calcolata al primo paint

        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
    }

    // Avvia la timeline
    public void start() {
        timer.start();
    }

    // Ferma la timeline
    public void stop() {
        timer.stop();
    }

    // Aggiorna la barra: scende se il pollo non si muove, reset se si muove
    private void update() {
        if (gameOver) {
            timer.stop();
            return;
        }

        if (panel.p.getPosY() < previousY) {
            LaneW = panel.getWidth(); // reset della barra
        } else {
            LaneW -= decrementa;
            if (LaneW <= 0) {
                LaneW = 0;
                gameOver = true;
                System.out.println("GAME OVER!");
            }
        }

        previousY = panel.p.getPosY();
        panel.repaint();
    }

    // Disegna la barra verde lunga tutto il pannello con angoli smussati
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (LaneW <= 0) LaneW = panel.getWidth(); // prima inizializzazione

        g2.setColor(Color.GREEN);
        g2.fillRoundRect(0, 5, LaneW, 20, 15, 15); // barra verde

        g2.setColor(Color.BLACK);
        g2.drawRoundRect(0, 5, panel.getWidth(), 20, 15, 15); // bordo fisso
    }

    // Stampa lo stato attuale della barra
    public void printStatus() {
        System.out.println("Barra larghezza: " + LaneW + ", GameOver: " + gameOver);
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

}
