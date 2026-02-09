import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

class MyPanel extends JPanel {
    //timeline
    public TimeLine timeline;
    //lista di corsie
    private ArrayList<Lane> mappa = new ArrayList<>();
    //pollo
    public pollo p = new pollo(250, 460, 20, 20);
    //Gameloop
    public loop loop = new loop(p,mappa,this);
    //controllo della fine del gioco
    public boolean gameOver = false;
    //utile per randomizzare le corsie
    private Random random = new Random();
    //parametro per spostare la telecamera
    public int camY;
    //tiene conto del punto più alto raggiunto
    public int recordY = 470;

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        MyMouseAdapter mouse = new MyMouseAdapter(this);
        addMouseListener(mouse);
        
        MyKeyboardAdapter key = new MyKeyboardAdapter(this);
        addKeyListener(key);
        
        timeline = new TimeLine(this, 50, 5);
        inizializzazioneGioco();
        loop.start();
        timeline.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //sposta la telecamera
        g.translate(0, camY);

        //controllo game over e stampa della schermata
        if (gameOver) { disegnaSchermataGameOver(g); return; }

        // Disegno della mappa
        for (Lane l : mappa) {
            l.draw(g);
        }
        //disegno del pollo 
        p.draw(g);
        //permette alla scritta e alla timeline di
        //rimanere sempre all'interno del pannello
        g.translate(0, -camY);
        //disegno della timeline
        timeline.draw(g);
        //scritta dello score 
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + loop.score, 10, 50);
    }

    public void setGameOver(boolean value) { gameOver = value; }

    void inizializzazioneGioco() 
    {
        //posizioni iniziali del pollo
        p.setPosX(250);
        p.setPosY(470);
        //resett dei valori della loop
        loop.setEnd(false);
        setGameOver(false);
        loop.score = 0;
        //reset della telecamera
        camY = 0;
        recordY = 470;

        //pulizia della mappa
        for(Lane l : mappa) 
            l.reset();
        mappa.clear();
        //creazione del Prato alla base per evitare che il pollo inizi
        //in una corsia non sicura
        mappa.add(new Prato(460));
        
        //creazione della mappa
        for (int i = 1; i < 1000; i++) {
            //posizione successiva alla corsia precedente
            int PosY = 460 - (i * 40);
            int corsia = random.nextInt(6); 
            //creazione casuale della corsia
            if (corsia == 1 || corsia == 2) 
                mappa.add(new Prato(PosY));
            else if (corsia == 3 || corsia == 4 || corsia == 5) 
                mappa.add(new Strada(PosY, 10 + random.nextInt(3), 6 + random.nextInt(2)));
            else 
                mappa.add(new Fiume(PosY, 6 + random.nextInt(2)));
        }
    }

    private void disegnaSchermataGameOver(Graphics g) {
        //estetica dello sfondo di game over 
        Color velo = new Color(0, 0, 0, 180); 
        g.setColor(velo);
        g.translate(0, -camY);
        //scritta Game Over
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("GAME OVER", 110, 100);
        //punteggio finale
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Punteggio Finale: " + loop.score, 130, 180);
        //punteggio massimo raggiunto
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("Miglior Punteggio: " + loop.highScore, 145, 230);
        //tasti per muoversi nel menù
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Premi [R] per ricominciare", 14+0, 280);
        g.drawString("Premi [Q] per uscire dal gioco", 135, 320);
    }

    public void resetGame(){
        loop.setEnd(true);

        try {
            loop.join(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inizializzazioneGioco();

        loop = new loop(p, mappa, this);
        loop.score = 0;
        timeline = new TimeLine(this, 50, 5);
        loop.start();
        timeline.start();
    
        revalidate();
        repaint();
    }

}




