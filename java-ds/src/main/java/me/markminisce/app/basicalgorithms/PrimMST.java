package me.markminisce.app.basicalgorithms;

import me.markminisce.app.basicdatastructures.Graph;
import me.markminisce.app.basicdatastructures.GraphNode;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

//Creates Minimum Spanning Tree (MST) with Prim's Algorithm, a 
//greedy algorithm to build a MST
public class PrimMST<T> {

    private Graph<T> graph;
    private Map<GraphNode<T>, Integer> distances;
    private Map<GraphNode<T>, GraphNode<T>> destinationToParent;
    private Set<String> nodesInMst; 
    private Graph<T> mst;


    public PrimMST(Graph<T> graph) {
        this.graph = graph;
        this.distances = new HashMap<GraphNode<T>, Integer>();
        this.destinationToParent = new HashMap<GraphNode<T>, GraphNode<T>>();
        this.nodesInMst = new HashSet<>();

        this.mst = new Graph<>(graph.getVertexCount()); 
        
        graph.getVertices().entrySet().stream()
            .map(vertexAndEdges -> vertexAndEdges.getKey())
            .forEach(vertex -> this.distances.put(vertex, Integer.MAX_VALUE));
    }

    public Graph<T> getMST() {
        //start with arbitrary node, in our case, always start with first node.
        Map.Entry<GraphNode<T>, LinkedList<GraphNode<T>>> currentVertexAndEdges = 
            this.graph.getVertices().entrySet().stream().findFirst().get();
        
        //start by adding the first vertex
        mst.addVertex(currentVertexAndEdges.getKey()); 
        nodesInMst.add(currentVertexAndEdges.getKey().getId());

        int overallMinDistance = Integer.MAX_VALUE;

        //while not all nodes have been placed in the mst
        while(mst.getVertexCount() < this.graph.getVertexCount()) {

            //update known distances to all nodes based on what we've seen thus far
            for (GraphNode<T> n : currentVertexAndEdges.getValue()){
                boolean inMst = isInMst(n);
                int currentKnownDistance = distances.get(n);
                int discoveredDistance = n.getWeight();
                if (!n.equals(currentVertexAndEdges.getKey()) && !inMst && discoveredDistance < currentKnownDistance) {
                    this.distances.put(n, n.getWeight()); 
                    this.destinationToParent.put(n, currentVertexAndEdges.getKey());
                }
            }

            //add node to the MST with minimum known distance
            overallMinDistance = Integer.MAX_VALUE;
            GraphNode<T> bestDestination = null;
            GraphNode<T> bestOrigin = null;
            for(Map.Entry<GraphNode<T>, Integer> distanceToVertex : this.distances.entrySet()) {
                if (!isInMst(distanceToVertex.getKey()) && distanceToVertex.getValue() < overallMinDistance) {
                    bestOrigin = destinationToParent.get(distanceToVertex.getKey());
                    bestDestination = distanceToVertex.getKey(); 
                    overallMinDistance = distanceToVertex.getValue();
                }
            }

            this.mst.addEdge(bestOrigin, bestDestination);
            this.nodesInMst.add(bestDestination.getId()); 
            currentVertexAndEdges = getEdges(bestDestination);
        }

        return mst;
    }

    private boolean isInMst(GraphNode<T> node) {
        return this.nodesInMst.contains(node.getId());
    }

    private Map.Entry<GraphNode<T>, LinkedList<GraphNode<T>>> getEdges(GraphNode<T> vertex) {
        return this.graph.getVertices().entrySet().stream()
            .filter(vertexAndEdges -> vertexAndEdges.getKey().equals(vertex))
            .findFirst()
            .get();
    }
}
