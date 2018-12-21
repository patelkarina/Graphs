package graph;
import java.util.ArrayList;

/* See restrictions in Graph.java. */

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Karina Patel
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        if (!getVertices().contains(v)) {
            return 0;
        }
        Iteration<Integer> iter = predecessors(v);
        int count = 0;
        for (int x : iter) {
            count++;
        }
        return count;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        ArrayList<Integer> predecess = new ArrayList<Integer>();
        if (getVertices().contains(v)) {
            for (int i = 0; i < getEdges().size(); i++) {
                if (getEdges().get(i).get(1) == v) {
                    predecess.add(getEdges().get(i).get(0));
                }
            }

        }
        return Iteration.iteration(predecess);
    }
}

