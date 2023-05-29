import structures.*;
import java.util.*;

public class Monster {
  private int loc;

  public Monster() {
    Random rand = new Random();
    this.loc = rand.nextInt(5) + 5;
  }

  public Monster(int loc) {
    this.loc = loc;
  }

  public int getLoc() {
    return this.loc;
  }

  public void move(int loc) {
    this.loc = loc;
  }

  public int nextMove(MapGraph nMap, int loc2) {
    Queue<Integer> theQ = new LinkedList<Integer>();
    // Array to store the visit status for each vertex; a true indicates that
    // a vertex has been added to queue or visited before
    boolean[] seen = new boolean[nMap.getNumV()];
    // append visited vertices to this String
    theQ.offer(loc);
    // 2-Mark start as seen
    seen[loc] = true;
    int next = -1;
    int toReturn = -1;
    int prevCount = 0;
    int nextCount = 0;
    Iterator<Edge> x = nMap.getEdgeIterator(loc2);
    // COMPLETE ME
    // **************************************************************************************************
    // 3- Repeat following as long as queue is not empty
    // 3.1- Dequeue and visit (by appending vertex to visitSequence
    // 3.2- Enqueue adjacent vertices (not seen before) marking each as seen

    while (x != null && x.hasNext()) {
      
      prevCount = nextCount;
      nextCount = 0;
      Edge b = x.next();
      next = b.getDest();
      if(!x.hasNext())
        return next;
      while (!theQ.isEmpty()) {
        int next2 = theQ.remove();
        if (next2 == loc2) {
          Arrays.fill(seen, false);
          theQ.clear();
          break;
        }
        Iterator<Edge> y = nMap.getEdgeIterator(next2);
        while (y != null && y.hasNext()) {
          int next3 = y.next().getDest();
          if (next3 == loc2) {
            Arrays.fill(seen, false);
            theQ.clear();
            break;
          }
          if (!seen[next3]) {
            seen[next3] = true;
            theQ.add(next3);
          }
        }
        nextCount++;
      }
      if (prevCount > nextCount) {
        toReturn = next;
      }
    }
    Arrays.fill(seen, false);
    theQ.clear();
    return toReturn;
  }
}
