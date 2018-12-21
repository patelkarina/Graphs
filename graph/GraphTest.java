package graph;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/** Unit tests for the Graph class.
 *  @author Karina Patel
 */
public class GraphTest {


    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void addRemove() {
        UndirectedGraph u = new UndirectedGraph();
        u.add();
        u.add();
        u.add();
        u.remove(2);
        u.remove(3);
        assertEquals(2, u.add());
        assertEquals(3, u.add());
        u.remove(3);
        assertEquals(3, u.add());
        assertEquals(4, u.add());
        u.remove(1);
        assertEquals(1, u.add());

        DirectedGraph d = new DirectedGraph();
        d.add();
        d.add();
        d.add();
        d.remove(2);
        d.remove(3);
        assertEquals(2, d.add());
        assertEquals(3, d.add());
        d.remove(3);
        assertEquals(3, d.add());
        assertEquals(4, d.add());
        d.remove(1);
        assertEquals(1, d.add());
    }

    @Test
    public void edgeCountTest() {
        UndirectedGraph u = new UndirectedGraph();
        u.add();
        u.add();
        u.add();
        u.add();

        u.add(1, 2);
        u.add(2, 3);
        u.add(3, 4);
        assertEquals(u.edgeSize(), 3);
        u.remove(2);
        assertEquals(u.edgeSize(), 1);

        DirectedGraph d = new DirectedGraph();
        d.add();
        d.add();
        d.add();
        d.add();

        d.add(1, 2);
        d.add(2, 3);
        d.add(3, 4);
        assertEquals(d.edgeSize(), 3);
        d.remove(2);
        assertEquals(u.edgeSize(), 1);

    }

    @Test
    public void edgeCountTest2() {

        UndirectedGraph u = new UndirectedGraph();
        u.add();
        u.add();
        u.add();
        u.add();

        u.add(1, 2);
        u.add(2, 3);
        u.add(3, 4);
        assertEquals(u.edgeSize(), 3);
        u.remove(2, 3);
        assertEquals(u.edgeSize(), 2);

        DirectedGraph d = new DirectedGraph();
        d.add();
        d.add();
        d.add();
        d.add();

        d.add(1, 2);
        d.add(2, 3);
        d.add(3, 4);
        assertEquals(d.edgeSize(), 3);
        d.remove(2, 3);
        assertEquals(u.edgeSize(), 2);

    }

    @Test
    public void addRemove2() {
        UndirectedGraph u = new UndirectedGraph();
        u.add();
        u.add();
        u.add();
        assertEquals(u.edgeId(2, 3), u.add(2, 3));
        assertEquals(u.edgeId(1, 2), u.add(1, 2));
        ArrayList<ArrayList<Integer>> edgestest =
                new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> first = new ArrayList<Integer>();
        first.add(2);
        first.add(3);
        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);
        edgestest.add(0, first);
        edgestest.add(1, second);
        assertTrue(u.getEdges().contains(edgestest.get(0)));
        assertTrue(u.getEdges().contains(edgestest.get(1)));
        u.remove(2, 3);
        assertFalse(u.getEdges().contains(edgestest.get(0)));

        DirectedGraph d = new DirectedGraph();
        d.add();
        d.add();
        d.add();
        assertEquals(d.edgeId(2, 3), d.add(2, 3));
        assertEquals(d.edgeId(1, 2), d.add(1, 2));
        ArrayList<ArrayList<Integer>> edgestestdirected =
                new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> firstdirected = new ArrayList<Integer>();
        firstdirected.add(2);
        firstdirected.add(3);
        ArrayList<Integer> seconddirected = new ArrayList<Integer>();
        seconddirected.add(1);
        seconddirected.add(2);
        edgestestdirected.add(0, firstdirected);
        edgestestdirected.add(1, seconddirected);
        assertTrue(d.getEdges().contains(edgestestdirected.get(0)));
        assertTrue(d.getEdges().contains(edgestestdirected.get(1)));
        d.remove(2, 3);
        assertFalse(d.getEdges().contains(edgestestdirected.get(0)));
    }

    @Test
    public void testSuccessors() {
        DirectedGraph d = new DirectedGraph();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add(2, 3);
        d.add(3, 4);
        d.add(4, 6);
        d.add(4, 7);
        Iteration<Integer> iter = d.successors(4);
        int edge1 = iter.next();
        int edge2 = iter.next();
        assertEquals(edge1, 6);
        assertEquals(edge2, 7);
        assertEquals(d.outDegree(4), 2);

        UndirectedGraph u = new UndirectedGraph();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add(2, 3);
        u.add(3, 4);
        u.add(4, 6);
        u.add(4, 7);
        Iteration<Integer> iter2 = u.successors(4);
        int uedge1 = iter2.next();
        int uedge2 = iter2.next();
        int uedge3 = iter2.next();
        assertEquals(uedge1, 3);
        assertEquals(uedge2, 6);
        assertEquals(uedge3, 7);
        assertEquals(u.outDegree(4), 3);

    }

    @Test
    public void testPredecessors() {
        DirectedGraph d = new DirectedGraph();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add(2, 3);
        d.add(3, 4);
        d.add(2, 4);
        d.add(4, 6);
        d.add(4, 7);
        Iteration<Integer> iter = d.predecessors(4);
        int edge1 = iter.next();
        int edge2 = iter.next();
        assertEquals(edge1, 3);
        assertEquals(edge2, 2);
        assertEquals(d.inDegree(4), 2);

        UndirectedGraph u = new UndirectedGraph();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add();
        u.add(2, 3);
        u.add(3, 4);
        u.add(2, 4);
        u.add(4, 6);
        u.add(4, 7);
        Iteration<Integer> iter2 = u.predecessors(4);
        int uedge1 = iter2.next();
        int uedge2 = iter2.next();
        int uedge3 = iter2.next();
        int uedge4 = iter2.next();
        assertEquals(uedge1, 3);
        assertEquals(uedge2, 2);
        assertEquals(uedge3, 6);
        assertEquals(uedge4, 7);
        assertEquals(u.inDegree(4), 4);

    }

    @Test
    public void testBreadthFirst1() {
        DirectedGraph d = new DirectedGraph();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add(5, 4);
        d.add(5, 3);
        d.add(4, 1);
        d.add(3, 2);
        d.add(1, 5);
        BreadthFirstTraversal breadth = new BreadthFirstTraversal(d);
        breadth.traverse(1);
        assertEquals(true, breadth.marked(1));
        assertEquals(true, breadth.marked(5));
        assertEquals(false, breadth.marked(6));
    }

    @Test
    public void testBreadthFirst2() {
        DirectedGraph d = new DirectedGraph();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add(1, 2);
        d.add(1, 3);
        d.add(3, 4);
        d.add(2, 5);
        d.add(5, 6);
        d.add(6, 7);
        BreadthFirstTraversal breadth = new BreadthFirstTraversal(d);
        breadth.traverse(1);
        assertEquals(true, breadth.marked(1));
        assertEquals(true, breadth.marked(2));
        assertEquals(true, breadth.marked(3));
        assertEquals(true, breadth.marked(4));
        assertEquals(true, breadth.marked(5));
        assertEquals(true, breadth.marked(6));
        assertEquals(true, breadth.marked(7));
        assertEquals(false, breadth.marked(8));
    }

    @Test
    public void testDepthFirst1() {
        DirectedGraph d = new DirectedGraph();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add(0, 1);
        d.add(1, 2);
        d.add(3, 4);
        d.add(4, 5);
        d.add(5, 6);
        DepthFirstTraversal depth = new DepthFirstTraversal(d);
        depth.traverse(5);
        assertEquals(true, depth.marked(5));
        assertEquals(false, depth.marked(8));
    }

    @Test
    public void testDepthFirst2() {
        DirectedGraph d = new DirectedGraph();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add();
        d.add(1, 2);
        d.add(1, 3);
        d.add(3, 4);
        d.add(2, 5);
        d.add(5, 6);
        d.add(6, 7);
        DepthFirstTraversal depth = new DepthFirstTraversal(d);
        depth.traverse(5);
        assertEquals(false, depth.marked(1));
        assertEquals(false, depth.marked(2));
        assertEquals(false, depth.marked(3));
        assertEquals(false, depth.marked(4));
        assertEquals(true, depth.marked(5));
        assertEquals(true, depth.marked(6));
        assertEquals(true, depth.marked(7));
        assertEquals(false, depth.marked(8));
    }



}
