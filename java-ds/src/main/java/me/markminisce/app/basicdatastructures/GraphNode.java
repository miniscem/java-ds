package me.markminisce.app.basicdatastructures;

import lombok.Data;

@Data
public class GraphNode<T> implements Identifiable {
    private T value;
    private int weight;
    private String id; 

    public GraphNode(String id) {
        this.id = id;
        this.value = null;
        this.weight = 0;
    }

    public GraphNode(T value) {
        this.value = value;
        this.id = Integer.toString(value.hashCode());
        this.weight = 0;
    }


    public GraphNode(T value, int weight) {
        this.value = value;
        this.id = Integer.toString(value.hashCode());
        this.weight = weight;
    }

    public String getId() {
        return id;
    }
}
