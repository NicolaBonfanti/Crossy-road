import java.awt.Rectangle;
import java.util.ArrayList;

class Lane {
        int y;                     // Y nel MONDO
        boolean isRoad;            // strada o sicura
        int speed;                 // velocità macchine
        ArrayList<Rectangle> cars = new ArrayList<>();
    }