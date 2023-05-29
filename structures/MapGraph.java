package structures;
import java.util.*;
import java.io.*;

/** 
 * A graph is a Map of vertices and a set of edges. Vertices are represented 
 * by integers from 0 to n - 1. Edges are ordered pairs of vertices.
 */

public class MapGraph implements Graph {
  
  // Data Field
  
  /** The number of vertices */
  private int numV;
  
  /** Flag to indicate whether this is a directed graph */
  private boolean directed;
  
  /** A map to contain the edges that
    * originate with each vertex. */
  private Map<Integer,Set<Edge>> edges;
  
  /** Construct a graph with the specified number of vertices and directionality.
    * @param numV The number of vertices
    * @param directed The directionality flag
    */
  public MapGraph(int numV, boolean directed) {
    this.numV = numV;
    this.directed = directed;
    this.edges = new HashMap<Integer,Set<Edge>>();
  }
  
  /** 
   * Construct a graph and load the data from an input file. The first line  
   * of the input file should contain the number of vertices, followed by
   * a series of lines, each line with two or three data values. The first 
   * is the source, the second is the destination, and the optional third is 
   * the weight.
   * @param fileName The file to read data from
   * @param directed true if this is a directed graph, false otherwise
   */
  public MapGraph(String fileName, boolean directed) {
    try{
      Scanner fileSC = new Scanner(new File(fileName));    
      String line = fileSC.nextLine(); // first line is number of vertices
      this.numV = Integer.parseInt(line);
      this.directed = directed;
      this.edges = new HashMap<Integer,Set<Edge>>();
      
      while (fileSC.hasNextLine()){
        line = fileSC.nextLine();
        Scanner lineSC = new Scanner(line);
        int source = lineSC.nextInt();
        int dest = lineSC.nextInt();
        insert(new Edge(source, dest));
        lineSC.close();
      } 
      fileSC.close();
    }    
    catch(IOException ioe){
      return;
    }    
  }
  
  // Accessor Methods
  public int getNumV() {
    return this.numV;
  }
  
  public boolean isDirected() {
    return this.directed;
  }
  
  public boolean isEdge(int source, int dest) {
    Set<Edge> connections = this.edges.get(source);
    if (connections!=null){
      return this.edges.get(source).contains(new Edge(source, dest));
    }
    else{
      return false;
    }
  }

  public void insert(Edge edge) {
    //remove old edge(s) if there is one
    if(this.isEdge(edge.getSource(), edge.getDest())){
      this.edges.get(edge.getSource()).remove(edge);
      if(!isDirected()){
        this.edges.get(edge.getDest()).remove(new Edge(edge.getDest(),edge.getSource()));
      }
    }
    Set<Edge> connections = this.edges.get(edge.getSource());
    if(connections == null){
      this.edges.put(edge.getSource(), new HashSet<Edge>());
    }
    this.edges.get(edge.getSource()).add(edge);
    if (!isDirected()) {
      connections = this.edges.get(edge.getDest());
      if(connections == null){
        this.edges.put(edge.getDest(), new HashSet<Edge>());
      }      
      this.edges.get(edge.getDest()).add(new Edge(edge.getDest(),edge.getSource()));
    }
  }
  
  public Edge getEdge(int source, int dest) {
    Edge target = new Edge(source, dest);
    for (Edge edge : edges.get(source)) {
      if (edge.equals(target))
        return edge; // Desired edge found, return it.
    }
    // Assert: All edges for source checked.
    return target; // Desired edge not found.
  }

  public Iterator<Edge> getEdgeIterator(int source){
    if(this.edges.get(source)!=null){
      return this.edges.get(source).iterator();
    }
    return null;
  }
  
  
}
