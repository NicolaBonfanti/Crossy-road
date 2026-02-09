import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MyKeyboardAdapter implements KeyListener{
    

MyPanel pannelloSuCuiLavorare;

    public MyKeyboardAdapter(MyPanel p){
        this.pannelloSuCuiLavorare = p;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        char key = e.getKeyChar();
        if(pannelloSuCuiLavorare.gameOver == false)
        {
            //gestiscono lo spostamento laterale
            if(key == 'a')pannelloSuCuiLavorare.p.move(-20, 0, pannelloSuCuiLavorare.getWidth(), 5000);
            else if(key == 'd')pannelloSuCuiLavorare.p.move(20, 0, pannelloSuCuiLavorare.getWidth(), 5000);
    
            //gestisce l'avanzamento
            int limiteInferiore = 500 - pannelloSuCuiLavorare.camY;
            if(key == 'w') { 
                //sposta il pollo
                pannelloSuCuiLavorare.p.move(0, -40, pannelloSuCuiLavorare.getWidth(), 5000);
                //controllo dell'aumento del record
                if (pannelloSuCuiLavorare.p.getPosY() < pannelloSuCuiLavorare.recordY) {
                    //aumenta il punteggio solo se il record aumenta
                    pannelloSuCuiLavorare.loop.score += 1;
                    //aumenta l'higscore solo se lo score è maggiore di quell'ultimo
                    if(pannelloSuCuiLavorare.loop.score > pannelloSuCuiLavorare.loop.highScore)
                        pannelloSuCuiLavorare.loop.highScore = pannelloSuCuiLavorare.loop.score;
                //aggiornamento del record
                pannelloSuCuiLavorare.recordY = pannelloSuCuiLavorare.p.getPosY();
                
                // Muovi la telecamera solo se il pollo supera la metà schermo
                if (pannelloSuCuiLavorare.p.getPosY() < 250) {
                    pannelloSuCuiLavorare.camY = 250 - pannelloSuCuiLavorare.p.getPosY();
                }
            }
            } 

            //gestisce l'arretramento
            else if(key == 's') {
                //permette al pollo di non superare il limite inferiore
                if (pannelloSuCuiLavorare.p.getPosY() + 60 < limiteInferiore) 
                    pannelloSuCuiLavorare.p.move(0, 40, pannelloSuCuiLavorare.getWidth(), 5000);
            }  
        }else{
            //tasti del menù
            if(e.getKeyChar() == 'q') System.exit(0);
            if(e.getKeyChar() == 'r') pannelloSuCuiLavorare.resetGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

}
