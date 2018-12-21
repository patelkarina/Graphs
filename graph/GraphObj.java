package graph;
import java.util.ArrayList;

/* See restrictions in Graph.java. */

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Karina Patel
 */
abstract class GraphObj extends Graph {

    /** A double ArrayList of edges. */
    private ArrayList<ArrayList<Integer>> edges;

    /** An ArrayList of vertices. */
    private ArrayList<Integer> vertices;

    /** A new, empty Graph. */
    GraphObj() {
        vertices = new ArrayList<Integer>();
        edges = new ArrayList<ArrayList<Integer>>();
        count = 0;
    }

    @Override
    public int vertexSize() {
        return vertices.size();
    }

    @Override
    public int maxVertex() {
        if (vertices.isEmpty()) {
            return 0;
        }
        return vertices.get(vertices.size() - 1);

    }

    @Override
    public int edgeSize() {
        return count;
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (!vertices.contains(v)) {
            return 0;
        }
        int outgoing = 0;
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).get(0) == v) {
                outgoing++;
            } else if (edges.get(i).get(1) == v && !isDirected()) {
                outgoing++;
            }
        }
        return outgoing;
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        return vertices.contains(u);
    }

    @Override
    public boolean contains(int u, int v) {
        if (!vertices.contains(u) || !vertices.contains(v)) {
            return false;
        }
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).get(0) == u && edges.get(i).get(1) == v) {
                return true;
            } else if (!isDirected() && edges.get(i).get(0) == v
                    && edges.get(i).get(1) == u) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int add() {
        if (vertices.size() == 0) {
            vertices.add(1);
            return 1;
        }
        int i = 0;
        while (i < vertices.size() && vertices.get(i) == i + 1) {
            i++;
        }
        int elem = i + 1;
        vertices.add(i, elem);
        return elem;
    }

    @Override
    public int add(int u, int v) {
        if (vertices.contains(u) && vertices.contains(v)) {
            for (int i = 0; i < edges.size(); i++) {
                if (edges.get(i).get(0) == u && edges.get(i).get(1) == v) {
                    return edgeId(u, v);
                }
            }
            ArrayList<Integer> edgesUV = new ArrayList<Integer>();
            edgesUV.add(0, u);
            edgesUV.add(1, v);
            edges.add(edgesUV);
            count += 1;
        }
        return edgeId(u, v);
    }

    @Override
    public void remove(int v) {
        if (vertices.contains(v)) {
            for (int j = 0; j < vertices.size(); j++) {
                if (vertices.get(j) == v) {
                    Iteration<Integer> successors = successors(v);
                    for (int s : successors) {
                        remove(v, s);
                    }

                    Iteration<Integer> predecessors = predecessors(v);
                    for (int p : predecessors) {
                        remove(p, v);
                    }
                    vertices.remove(j);
                }
            }
        }
    }

    @Override
    public void remove(int u, int v) {
        if (vertices.contains(u) && vertices.contains(v)) {
            for (int i = 0; i < edges.size(); i++) {
                if ((edges.get(i).get(0) == u && edges.get(i).get(1) == v)
                        || (!isDirected()
                        && edges.get(i).get(1) == u
                        && edges.get(i).get(0) == v)) {
                    edges.remove(i);
                    count -= 1;
                }
            }
        }
    }


    @Override
    public Iteration<Integer> vertices() {
        return Iteration.iteration(vertices);
    }

    @Override
    public Iteration<Integer> successors(int v) {
        ArrayList<Integer> success = new ArrayList<Integer>();
        if (vertices.contains(v)) {
            for (int i = 0; i < edges.size(); i++) {
                if (edges.get(i).get(0) == v) {
                    success.add(edges.get(i).get(1));
                } else if (edges.get(i).get(1) == v && !isDirected()) {
                    success.add(edges.get(i).get(0));
                }
            }

        }
        return Iteration.iteration(success);
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        ArrayList<int[]>  arrEdges = new ArrayList<int[]>();
        for (ArrayList<Integer> e : edges) {
            int e1 = e.get(0);
            int e2 = e.get(1);
            arrEdges.add(new int [] {e1, e2});
        }
        return Iteration.iteration(arrEdges);
    }

    @Override
    protected void checkMyVertex(int v) {
        super.checkMyVertex(v);
    }

    @Override
    protected int edgeId(int u, int v) {
        if (isDirected()) {
            return ((u + v) * (u + v + 1)) / 2 + v;
        }
        int maxV = Math.max(u, v);
        int minV = Math.min(u, v);
        return ((maxV + minV) * (maxV + minV + 1)) / 2 + minV;
    }

    /** Returns the ArrayList of vertices. */
    public ArrayList<Integer> getVertices() {
        return vertices;
    }

    /** Returns the double ArrayList of edges. */
    public ArrayList<ArrayList<Integer>> getEdges() {
        return edges;
    }

    /** Edge count. */
    private int count;

}
