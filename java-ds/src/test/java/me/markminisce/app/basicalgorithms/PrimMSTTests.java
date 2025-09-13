package me.markminisce.app.basicalgorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.markminisce.app.basicdatastructures.Graph;
import me.markminisce.app.basicdatastructures.GraphNode;

public class PrimMSTTests {

    private PrimMST<Integer> testObj;
    private Graph<Integer> basicGraph; 

    @Test
    public void basicGraphTest() {     
        basicGraph  = getBasicGraphForMst();   
        testObj = new PrimMST<>(basicGraph);
        
        assertNotNull(testObj);
        
        Graph<Integer> result = testObj.getMST(); 
        assertNotNull(result);
        assertEquals(basicGraph.getVertexCount(), result.getVertexCount()); 
    }

    @Test
    public void verifyIndividualNodeConnections() {
        basicGraph  = getBasicGraphForMst();
        testObj = new PrimMST<>(basicGraph);
        Graph<Integer> result = testObj.getMST(); 

        //verify connections to Node{value: 1, weight: 2}
        LinkedList<GraphNode<Integer>> n12Edges = result.getVertices().stream()
            .filter(edge -> edge.getFirst().getId().equals("1") 
                        && edge.getFirst().getWeight() == 2).findFirst().get(); 
        
        //implementation will always store the vertex edge at the first node in the edge list.
        assertEquals(2, n12Edges.subList(1, n12Edges.size()).size());
        GraphNode<Integer> n12FirstEdge = n12Edges.get(1);
        assertEquals("3", n12FirstEdge.getId());
        assertEquals(2, n12FirstEdge.getWeight());

        GraphNode<Integer> n12SecondEdge = n12Edges.get(2);
        assertEquals("2", n12SecondEdge.getId());
        assertEquals(5, n12SecondEdge.getWeight());


        //verify connections to Node{value: 2, weight: 5}
        LinkedList<GraphNode<Integer>> n25Edges = result.getVertices().stream()
            .filter(edge -> edge.getFirst().getId().equals("2") 
                        && edge.getFirst().getWeight() == 5).findFirst().get(); 
        
        assertEquals(1, n25Edges.subList(1, n25Edges.size()).size());
        GraphNode<Integer> n25FirstEdge = n25Edges.get(1);
        assertEquals("4", n25FirstEdge.getId());
        assertEquals(1, n25FirstEdge.getWeight());       

        //verify connections to Node{value: 4, weight: 1}
        LinkedList<GraphNode<Integer>> n41Edges = result.getVertices().stream()
            .filter(edge -> edge.getFirst().getId().equals("4") 
                        && edge.getFirst().getWeight() == 1).findFirst().get();         
        
        //node doesn't have any edges, outDegree == 0
        assertEquals(1, n41Edges.size());

                //verify connections to Node{value: 3, weight: 2}
        LinkedList<GraphNode<Integer>> n32Edges = result.getVertices().stream()
            .filter(edge -> edge.getFirst().getId().equals("3") 
                        && edge.getFirst().getWeight() == 2).findFirst().get();         
        
        //node doesn't have any edges, outDegree == 0
        assertEquals(1, n32Edges.size());

    }

    private Graph<Integer> getBasicGraphForMst() {
        Graph<Integer> graph = new Graph<>(10); 

        GraphNode<Integer> n12 = new GraphNode<>(1,2);
        GraphNode<Integer> n25 = new GraphNode<>(2,5);
        GraphNode<Integer> n32 = new GraphNode<>(3,2);
        GraphNode<Integer> n37 = new GraphNode<>(3,7);
        GraphNode<Integer> n310 = new GraphNode<>(3,10);
        GraphNode<Integer> n41 = new GraphNode<>(4,1);
        
        graph.addEdge(n12, n25);
        graph.addEdge(n12, n32);

        graph.addEdge(n25, n37);
        graph.addEdge(n25, n41);

        graph.addEdge(n41, n310);

        return graph;
    }
}
