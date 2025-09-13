package me.markminisce.app.basicdatastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTests {
    private Graph<Integer> testObj; 

    @BeforeEach
    public void setup() {
        testObj = new Graph<>(10); 
    }

    @Test
    public void basicAdditionTest() {
        GraphNode<Integer> n1 = new GraphNode<>(10);
        GraphNode<Integer> n2 = new GraphNode<>(20);
        GraphNode<Integer> n3 = new GraphNode<>(30);
        testObj.addEdge(n1, n2);
        testObj.addEdge(n1, n3);

        assertEquals(3, testObj.getVertexCount()); 
    }

    private Graph<Integer> getComplexGraph() {
        Graph<Integer> complexGraph = new Graph<>(10); 
        GraphNode<Integer> n1 = new GraphNode<>(10);
        GraphNode<Integer> n2 = new GraphNode<>(20);
        GraphNode<Integer> n3 = new GraphNode<>(30);
        GraphNode<Integer> n4 = new GraphNode<>(40);
        GraphNode<Integer> n5 = new GraphNode<>(50);
        complexGraph.addEdge(n1, n3);
        complexGraph.addEdge(n1, n2);
        
        complexGraph.addEdge(n2, n4);
        complexGraph.addEdge(n2, n1);

        complexGraph.addEdge(n3, n1);
        complexGraph.addEdge(n3, n2);
        complexGraph.addEdge(n3, n5);

        return complexGraph;
    }

    @Test
    public void verifyVertexConnectionsTest() {
        Graph<Integer> testGraph = getComplexGraph();
        assertEquals(5, testGraph.getVertexCount()); 

        ArrayList<LinkedList<GraphNode<Integer>>> vertices = testGraph.getVertices(); 

        assertTrue(areVertexesExpected(vertices.get(0), 
            IntStream.of(10, 30, 20).boxed().collect(Collectors.toSet())));

        assertTrue(areVertexesExpected(vertices.get(2), 
            IntStream.of(  20, 40, 10).boxed().collect(Collectors.toSet())));

        assertTrue(areVertexesExpected(vertices.get(1), 
            IntStream.of(  30, 10, 20, 50).boxed().collect(Collectors.toSet())));
            
        assertEquals(1, vertices.get(3).size());
        
    }

    private <T> boolean areVertexesExpected(LinkedList<GraphNode<T>> edges, Set<T> expectedEdges) {
      edges.stream().forEach(e -> {
        expectedEdges.remove(e.getValue()); 
      });

        return expectedEdges.size() == 0;
    }

    @Test
    public void toStringTest() {
        Graph<Integer> testGraph = getComplexGraph(); 
        assertNotNull(testGraph.toString()); 
    }

    @Test
    public void loopsShouldProduceExceptionTest() {
        GraphNode<Integer> n1 = new GraphNode<>(10);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            testObj.addEdge(n1, n1);   
        });

        assertEquals("Source cannot equal destination. Loops are not supported!", exception.getMessage());
    }
}