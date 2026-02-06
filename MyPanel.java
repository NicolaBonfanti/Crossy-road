import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

class MyPanel extends JPanel {
    //timeline
    public TimeLine timeline;
    //lista di macchine e di tronchi
    private ArrayList<Car> cars = new ArrayList<Car>();
    private ArrayList<Tronco> tronchi = new ArrayList<Tronco>();
    //pollo
    public pollo p = new pollo(250, 470, 30, 30);
    //Gameloop
    public loop loop = new loop(p,tronchi,cars,this);
    //controllo della fine del gioco
    public boolean gameOver = false;
    //colori sfondo
    private Color colorGround = new Color(34, 139, 34);
    private Color colorStreet = new Color(77, 77, 77);
    private Color colorWater = new Color(173, 216, 230);

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        MyMouseAdapter mouse = new MyMouseAdapter(this);
        addMouseListener(mouse);
        
        MyKeyboardAdapter key = new MyKeyboardAdapter(this);
        addKeyListener(key);
        
        timeline = new TimeLine(this, 50, 5);

        inizializzazioneGioco();

        timeline.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        //Pulizia della pagina con l'aggiunta del game over
        g.setFont(new Font("Arial", Font.BOLD, 40));
        //schermata di perdita
        if (gameOver) { disegnaSchermataGameOver(g); return; }
        g.setColor(colorGround);
        g.fillRect(0, 390, 500, 120); // Base
        // Disegna la timeline in alto
        if (timeline != null) 
            timeline.draw(g);
        //disegna la base del mondo
        disegnaBase(g);
        //disegno del pollo
        p.draw(g);
        //disegna il punteggio
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Score: " + loop.score, 0,450);
    }

    public void setGameOver(boolean value) { gameOver = value; }

    void inizializzazioneGioco() 
    {
        p.setPosX(250);
        p.setPosY(470);
        loop.setEnd(false);
        setGameOver(false);
        loop.score = 0;

        for (int i = 0; i < 3; i++) {
            Car car = new Car(i * 200, 355, 100,30);
            car.start(); 
            cars.add(car);
        }

        for (int i = 0; i < 3; i++) {
            int[] posizioniX = {100, 300, 500};
            Car car = new Car(posizioniX[i], 285, 100,30);
            car.start(); 
            cars.add(car);
        }

        for (int i = 0; i < 3; i++) {
            Car car = new Car(i * 200, 215, 100,30);
            car.start(); 
            cars.add(car);
        }

        for (int i = 0; i < 3; i++) {
            int[] posizioniX = {150, 350, 550};
            Tronco t = new Tronco(posizioniX[i], 135, 100,34);
            t.start(); 
            tronchi.add(t);
        }

        for (int i = 0; i < 3; i++) {
            Tronco t = new Tronco(i * 200, 98, 100,34);
            t.start(); 
            tronchi.add(t);
        }

        loop.start();
    }

    public void disegnaBase(Graphics g)
    {
        //groung
        g.setColor(colorGround);
        g.fillRect(0,390,500,120);
        g.drawRect(0,390,500,120);

        //Street
        g.setColor(colorStreet);
        g.fillRect(0,210,500,180);
        g.drawRect(0,210,500,180);

        //groung
        g.setColor(colorGround);
        g.fillRect(0,173,500,37);
        g.drawRect(0,173,500,37);

        //water
        g.setColor(colorWater);
        g.fillRect(0,93,500,80);
        g.drawRect(0,93,500,80);

        //disegno delle auto
        if (cars != null) 
            for (Car car : cars) 
                car.draw(g);
        
        //disegno dei tronchi
        if (tronchi != null) 
            for (Tronco trs : tronchi) 
                trs.draw(g);
    } 

    private void disegnaSchermataGameOver(Graphics g) {
    Color veloScuro = new Color(0, 0, 0, 180); 
    g.setColor(veloScuro);

    g.setFont(new Font("Arial", Font.BOLD, 50));
    g.drawString("GAME OVER", 110, 100);
    g.setFont(new Font("Arial", Font.BOLD, 30));
    g.drawString("Punteggio Finale: " + loop.score, 130, 180);
    g.setFont(new Font("Arial", Font.PLAIN, 20));
    g.drawString("Premi [R] per ricominciare", 140, 280);
    g.drawString("Premi [Q] per uscire dal gioco", 135, 320);
    }

}




