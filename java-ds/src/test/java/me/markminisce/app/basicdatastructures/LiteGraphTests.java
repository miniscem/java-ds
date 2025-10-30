package me.markminisce.app.basicdatastructures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LiteGraphTests {

    LiteGraph testObj = new LiteGraph();

    @BeforeEach
    void setup() {
        testObj = new LiteGraph();
    }

    /**
     * 0 -> 1,2,3
     * 2 -> 1,3,4
     * 3 -> 1,2,5
     *
     * Verify DFS populates the visited set with all reachable nodes.
     */
    @Test
    void testDfsVisitedSet() {
        testObj.add(0, 1);
        testObj.add(0, 2);
        testObj.add(0, 3);
        testObj.add(2, 1);
        testObj.add(2, 3);
        testObj.add(2, 4);
        testObj.add(3, 1);
        testObj.add(3, 2);
        testObj.add(2, 5);

        Set<Integer> visited = new HashSet<>();
        testObj.dfs(0, visited);

        Set<Integer> expected = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        assertEquals(expected, visited, "DFS should visit all reachable nodes");
    }


    /**
     * Test DFS handles isolated vertices correctly.
     */
    @Test
    void testIsolatedVertexDfs() {
        Set<Integer> visited = new HashSet<>();
        testObj.dfs(42, visited);
        assertTrue(visited.contains(42), "DFS should mark isolated start vertex as visited");
        assertEquals(1, visited.size(), "Only the isolated vertex should be visited");
    }

    /**
     * Test DFS handles a self-loop without infinite recursion.
     */
    @Test
    void testSelfLoopDfs() {
        testObj.add(1, 1);
        Set<Integer> visited = new HashSet<>();
        testObj.dfs(1, visited);
        assertTrue(visited.contains(1), "DFS should visit the vertex with a self-loop");
        assertEquals(1, visited.size(), "Self-loop should not cause additional vertices to be visited");
    }
}
