package graph;

/* See restrictions in Graph.java. */

/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author Karina Patel
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    @Override
    protected abstract double getWeight(int u, int v);

    @Override
    public double getWeight(int v) {
        if (_G.contains(v)) {
            return weights[v];
        }
        return Double.POSITIVE_INFINITY;
    }

    @Override
    protected void setWeight(int v, double w) {
        weights[v] = w;
    }

    @Override
    public int getPredecessor(int v) {
        if (_G.contains(v)) {
            return predecessors[v];
        }
        return 0;
    }

    @Override
    protected void setPredecessor(int v, int u) {
        predecessors[v] = u;
    }

    /** Weights of vertices. */
    private double[] weights = new double[_G.maxVertex() + 1];
    /** Predecessor. */
    private int[] predecessors = new int[_G.maxVertex() + 1];

}
