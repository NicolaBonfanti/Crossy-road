import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class SwingPaintDemo1 {
    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }
    
    private static void createAndShowGUI() {
        
        JFrame f = new JFrame("GUI");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(250,500);
        f.setResizable(false);
        MyPanel p = new MyPanel();
        f.add(p);
        f.pack();
        f.setVisible(true);
        p.setFocusable(true);
    }
}