package me.markminisce.app.basicalgorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

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

        //verify connections to Node{value: 1, weight: 2}\
        GraphNode<Integer> vertex12 = 
            result.getVertices().entrySet().stream().map(vertexAndEdges -> vertexAndEdges.getKey())
                .filter(vertex -> vertex.getId().equals("1") && vertex.getWeight() == 2).findFirst().get(); 

        LinkedList<GraphNode<Integer>> n12Edges = result.getVertices().get(vertex12);
        
        //implementation will always store the vertex edge at the first node in the edge list.
        assertEquals(2, n12Edges.size());
        GraphNode<Integer> n12FirstEdge = n12Edges.get(0);
        assertEquals("3", n12FirstEdge.getId());
        assertEquals(2, n12FirstEdge.getWeight());

        GraphNode<Integer> n12SecondEdge = n12Edges.get(1);
        assertEquals("2", n12SecondEdge.getId());
        assertEquals(5, n12SecondEdge.getWeight());


        //verify connections to Node{value: 2, weight: 5}
        GraphNode<Integer> vertex25 = 
            result.getVertices().entrySet().stream().map(vertexAndEdges -> vertexAndEdges.getKey())
                .filter(vertex -> vertex.getId().equals("2") && vertex.getWeight() == 5).findFirst().get(); 

        LinkedList<GraphNode<Integer>> n25Edges = result.getVertices().get(vertex25);
        
        assertEquals(1, n25Edges.size());
        GraphNode<Integer> n25FirstEdge = n25Edges.get(0);
        assertEquals("4", n25FirstEdge.getId());
        assertEquals(1, n25FirstEdge.getWeight());       

        //verify connections to Node{value: 4, weight: 1}
        GraphNode<Integer> vertex41 = 
            result.getVertices().entrySet().stream().map(vertexAndEdges -> vertexAndEdges.getKey())
                .filter(vertex -> vertex.getId().equals("4") && vertex.getWeight() == 1).findFirst().get(); 

        LinkedList<GraphNode<Integer>> n41Edges = result.getVertices().get(vertex41);      
        
        //node doesn't have any edges, outDegree == 0
        assertEquals(0, n41Edges.size());

                //verify connections to Node{value: 3, weight: 2}
        GraphNode<Integer> vertex32 = 
            result.getVertices().entrySet().stream().map(vertexAndEdges -> vertexAndEdges.getKey())
                .filter(vertex -> vertex.getId().equals("3") && vertex.getWeight() == 2).findFirst().get(); 

        LinkedList<GraphNode<Integer>> n32Edges = result.getVertices().get(vertex32);      
        
        //node doesn't have any edges, outDegree == 0
        assertEquals(0, n32Edges.size());
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
