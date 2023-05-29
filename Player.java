import java.util.*;
import structures.*;

public class Player {

    private int loc;
    private boolean dead;
    private boolean finish;

    public void setFinish() {
        this.finish = true;
    }

    public boolean isFinished() {
        return this.finish;
    }

    public Player(int loc) {
        this.loc = loc;
        this.dead = false;
        this.finish = false;
    }

    public boolean isDead() {
        return this.dead;
    }

    public int getLoc() {
        return this.loc;
    }

    public void setDead() {
        this.dead = true;
    }

    public void move(int i, MapGraph map) {
        Iterator<Edge> n = map.getEdgeIterator(loc);
        int b = 0;
        for (int j = 0; j < i; j++) {
            if(n.hasNext()){
                b = n.next().getDest();
            }
        }
        this.loc = b;
    }
}
