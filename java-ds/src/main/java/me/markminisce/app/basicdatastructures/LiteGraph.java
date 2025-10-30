package me.markminisce.app.basicdatastructures;

import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap; 
import java.util.HashSet;

public class LiteGraph {
    /**
     * A very small lightweight adjacency-list graph implementation.
     *
     * This class stores a mapping from integer vertices to a set of
     * adjacent integer vertices. It is intentionally minimal. 
     */
    Map<Integer, Set<Integer>> graph = new HashMap<>();

    public void add(int vertex, int u) {
        /**
         * Add a directed edge from vertex -> u. If the vertex does not yet
         * exist in the adjacency map, a LinkedHashSet is created to preserve
         * insertion order of neighbors.
         *
         * @param vertex the source vertex
         * @param u the destination vertex
         */
        if (!graph.containsKey(vertex)) {
            graph.put(vertex, new LinkedHashSet<>());
        }

        graph.computeIfAbsent(vertex, v -> new LinkedHashSet<>()).add(u);
    }

    /**
     * Depth-first traversal starting at {@code vertex}.
     *
     * @param vertex the start vertex
     * @param visited a mutable set which will contain visited vertices after
     *                the traversal completes (must not be null)
     */
    public void dfs(int vertex, Set<Integer> visited) {
        if (!visited.contains(vertex)) {
            visited.add(vertex);
        }

        Set<Integer> edges = graph.getOrDefault(vertex, new LinkedHashSet<>());
        for (int edge : edges) {
            if (!visited.contains(edge)) {
                dfs(edge, visited);
            }
        }
        System.out.println(String.format("Fully Processed %d", vertex));
    }

    /**
     * Breadth-first traversal starting at {@code vertex}.
     *
     * @param vertex the start vertex
     */
    public void bfs(int vertex) {
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(vertex);
        visited.add(vertex);

        while (!queue.isEmpty()) {
            int current = queue.removeFirst();

            Set<Integer> edges = graph.getOrDefault(current, new HashSet<>());
            for (Integer edge : edges) {
                if (!visited.contains(edge)) {
                    queue.addLast(edge);
                    visited.add(edge);
                }
            }

            System.out.println(String.format("Fully Processed %d", current));
        }
    }
}
