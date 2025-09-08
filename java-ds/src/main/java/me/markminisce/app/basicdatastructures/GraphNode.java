package me.markminisce.app.basicdatastructures;

import lombok.Data;

@Data
public class GraphNode<T> {
    private T value;
    private int weight;

    public GraphNode(T value) {
        this.value = value;
        this.weight = 0;
    }

    public GraphNode(T value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}
