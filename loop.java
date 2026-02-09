import java.util.ArrayList;

public class loop extends Thread{
    private pollo p;
    private MyPanel panel;
    private ArrayList<Lane> mappa;
    private boolean end = false;
    public int score = -1;
    public int highScore = 0;
    public int MaxHcam = 0;

    public loop(pollo p, ArrayList<Lane> mappa, MyPanel panel) {
        this.p = p;
        this.mappa = mappa;
        this.panel = panel;
    }

    @Override
    public void run() {
        while(!end)
        {
            //controllo delle corsie sul pollo
            CheckCorsiaPollo();
            //controllo che non sia terminato il tempo
            if(panel.timeline != null && panel.timeline.gameOver)
                end = true;
            //rende il gioco fluido

            try {
                sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //fine del gioco
        panel.setGameOver(end);
        panel.repaint();
    }

    //in base a quale corsia si trovi esegue il check di quella corsia
    public void CheckCorsiaPollo(){ for(Lane lane : mappa) if(lane.eseguiCheck(p)){end = true; return;} }

    public void setEnd(boolean end) {this.end = end;}
}
