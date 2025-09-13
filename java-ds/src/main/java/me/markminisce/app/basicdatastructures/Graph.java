package me.markminisce.app.basicdatastructures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import lombok.Getter;

//Graph implemented as an adjacency matrix
public class Graph<T> {
    @Getter
    private Map<GraphNode<T>, LinkedList<GraphNode<T>>> vertices;
    
    private Set<String> vertexIds; 

    @Getter
    private Map<String, Integer> outdegree;
    
    public Graph(int capacity) {
        initGraph(capacity); 
    }

    private void initGraph(int capacity) {
        vertices = new HashMap<GraphNode<T>, LinkedList<GraphNode<T>>>();
        this.outdegree = new HashMap<String, Integer>();
        this.vertexIds = new HashSet<>();
    }

    public void addEdge(GraphNode<T> origin, GraphNode<T> destination) {
        if (origin.equals(destination)) {
            throw new IllegalArgumentException("Source cannot equal destination. Loops are not supported!");
        }

        addVertex(origin);
        
        if (destination != null) {
            addVertex(destination);

            LinkedList<GraphNode<T>> currentVertexEdges = this.vertices.get(origin); 
            currentVertexEdges.addLast(destination);
            outdegree.put(origin.getId(), outdegree.getOrDefault(origin.getId() + 1, 1)); 
        }

        vertexIds.addAll(Arrays.asList(origin.getId(), destination.getId())); 
    }

    public void addVertex(GraphNode<T> vertex) {
        if (!this.vertices.containsKey(vertex)) {
            this.vertices.put(vertex, new LinkedList<GraphNode<T>>()); 
        }
        
        vertexIds.add(vertex.getId()); 
    }

    public int getVertexCount() {
        return vertexIds.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(); 
        LinkedList<GraphNode<T>> currentEdges;
        GraphNode<T> currentVertex; 


        for (Map.Entry<GraphNode<T>, LinkedList<GraphNode<T>>> vertexAndEdges : vertices.entrySet()) {            
            currentVertex = vertexAndEdges.getKey();
            currentEdges = vertexAndEdges.getValue();

            sb.append(String.format("%s[%d] -->", currentVertex.getValue(), currentVertex.getWeight()));

            currentEdges.stream()
                .forEach((node) -> {
                        sb.append(String.format("%s[%d] -->", node.getValue(), node.getWeight()));
                });
                
            sb.append("\n");
        }

        return sb.toString().trim();
    }
}
