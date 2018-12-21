package graph;

/* See restrictions in Graph.java. */

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;


/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author Karina Patel
 */
public abstract class ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        _short = new TreeSet<>(new CompareW());


    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        _short.add(_source);
        for (int i = 0; i < _G.maxVertex() + 1; i++) {
            if (i == _source) {
                setWeight(i, 0);
            } else {
                setWeight(i, Double.POSITIVE_INFINITY);
            }
        }
        while (!_short.isEmpty()) {
            int temp = _short.pollFirst();
            if (temp != _dest) {
                for (int s : _G.successors(temp)) {
                    double weight1 = getWeight(temp);
                    double weight2 = getWeight(temp, s);
                    double weight3 = getWeight(s);
                    double weight4 = weight1 + weight2;
                    if (weight3 > weight4) {
                        _short.remove(s);
                        setWeight(s, weight4);
                        _short.add(s);
                        setPredecessor(s, temp);
                    }
                }
            } else {
                return;
            }

        }
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        ArrayList<Integer> shortPath = new ArrayList<Integer>();
        int pat = v;
        while (getPredecessor(pat) != 0) {
            shortPath.add(pat);
            pat = getPredecessor(pat);
        }
        shortPath.add(_source);
        Collections.reverse(shortPath);
        return shortPath;
    }

    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /** Comparator class with overriding compare method. */
    private class CompareW implements Comparator<Integer> {
        @Override
        public int compare(Integer one, Integer two) {
            double oneW = getWeight(one) + estimatedDistance(one);
            double twoW = getWeight(two) + estimatedDistance(two);
            if (oneW > twoW) {
                return 1;
            }
            if (oneW == twoW) {
                return one - two;
            }
            return -1;
        }
    }


    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
    /** Pathway data structure. */
    private TreeSet<Integer> _short;


}
