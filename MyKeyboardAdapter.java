import java.awt.event.KeyAdapter;
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
        // TODO Auto-generated method stub
        if(e.getKeyChar() == 'w')pannelloSuCuiLavorare.p.move(0, -37, pannelloSuCuiLavorare.getWidth(), pannelloSuCuiLavorare.getHeight());
        else if(e.getKeyChar() == 's')pannelloSuCuiLavorare.p.move(0, 37, pannelloSuCuiLavorare.getWidth(), pannelloSuCuiLavorare.getHeight());
        else if(e.getKeyChar() == 'a')pannelloSuCuiLavorare.p.move(-17, 0, pannelloSuCuiLavorare.getWidth(), pannelloSuCuiLavorare.getHeight());
        else if(e.getKeyChar() == 'd')pannelloSuCuiLavorare.p.move(17, 0, pannelloSuCuiLavorare.getWidth(), pannelloSuCuiLavorare.getHeight());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

}
