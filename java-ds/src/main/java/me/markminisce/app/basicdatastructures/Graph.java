package me.markminisce.app.basicdatastructures;

import java.util.ArrayList;
import java.util.LinkedList;

import lombok.Getter;

//Graph implemented as an adjacency matrix
//TODO
//1. Examine the contract and basic operations of a graph
//2. Implement weighted 
public class Graph<T> {
    @Getter
    private ArrayList<LinkedList<GraphNode<T>>> vertices;

    @Getter
    private int[] outdegree;
    
    public Graph(int capacity) {
        initGraph(capacity); 
    }

    private void initGraph(int capacity) {
        vertices = new ArrayList<LinkedList<GraphNode<T>>>();
        this.outdegree = new int[capacity];
    }

    public void addEdge(GraphNode<T> origin, GraphNode<T> destination) {
        if (origin.equals(destination)) {
            throw new IllegalArgumentException("Source cannot equal destination. Loops are not supported!");
        }

        addVertex(origin);
        addVertex(destination);

        LinkedList<GraphNode<T>> currentVertexEdges; 

        for(int i = 0; i < vertices.size(); i++) {
            currentVertexEdges = vertices.get(i); 
            if (currentVertexEdges.peekFirst().equals(origin)) {
                currentVertexEdges.addLast(destination);
                outdegree[i]++;
                return;
            }
        }
    }

    private void addVertex(GraphNode<T> vertex) {
        for(int i = 0; i < this.vertices.size(); i++){
            if (vertex.equals(this.vertices.get(i).peekFirst())) {
                return;
            }
        }
        
        vertices.add(new LinkedList<GraphNode<T>>());
        LinkedList<GraphNode<T>> newOriginEdges = vertices.get(vertices.size() - 1); 
        newOriginEdges.addLast(vertex);
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(); 
        LinkedList<GraphNode<T>> currentEdges;

        for (int i = 0; i < this.vertices.size(); i++) {
            currentEdges = vertices.get(i);

            currentEdges.stream()
                .forEach((node) -> {
                        sb.append(String.format("%s[%d] -->", node.getValue(), node.getWeight()));
                });

            sb.append("NULL");
            sb.append("\n");
        }

        return sb.toString().trim();
    }
}
