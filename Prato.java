import java.awt.Color;
import java.awt.Graphics;

public class Prato extends Lane{
    private Color colorGround = new Color(34, 139, 34);

    public Prato(int y){ 
        super(y);
    }
    
    @Override
    public void draw(Graphics g)
    {
        g.setColor(colorGround);
        g.fillRect(0,y,500,40);
    }

    @Override
    public boolean eseguiCheck(pollo p) {
        //false perchè il pollo non può mai morire sul prato
        return false;
    }
    
}
