
/**
 * Union-Find (Disjoint Set Union) data structure implementation.
 * <p>
 * Maintains a collection of disjoint sets and supports efficient union and find operations.
 * This implementation is generic and works with GraphNode<T> objects identified by unique IDs.
 *
 * @param <T> the type of value stored in each GraphNode
 */
package me.markminisce.app.basicdatastructures;

import java.util.HashMap;
import java.util.Map;



/**
 * Union-Find (Disjoint Set Union) data structure for GraphNode<T> objects.
 *
 * @param <T> the type of value stored in each GraphNode
 */
public class UnionFind<T> {

    /**
     * Maps node IDs to their corresponding GraphNode objects.
     */
    private Map<String, GraphNode<T>> nodeIdToNode; 

    /**
     * Maps node IDs to their parent node IDs in the union-find structure.
     */
    private Map<String, String> parent;

    /**
     * Maps node IDs to the size of the subtree rooted at that node.
     * Used for union by size optimization.
     */
    private Map<String, Integer> nodeIdToSize; 
    
    /**
     * Constructs an empty UnionFind structure.
     */
    public UnionFind() {
        nodeIdToNode = new HashMap<>();
        parent = new HashMap<>();
        nodeIdToSize = new HashMap<>();
    }

    /**
     * Adds a new node to the union-find structure.
     *
     * @param node the GraphNode to add
     */
    public void add(GraphNode<T> node) {
        if (nodeIdToNode.containsKey(node.getId())) {
            return;
        }
        String nodeId =  node.getId(); 
        nodeIdToNode.put(nodeId, node);
        nodeIdToSize.put(nodeId, 1);
        parent.put(nodeId, null);
    }

    /**
     * Finds the representative (root) node of the set containing the given node.
     *
     * @param node the node to find the root for
     * @return the root GraphNode of the set containing the given node
     */
    public GraphNode<T> find(GraphNode<T> node) {
        String currentNodeId = node.getId(); 
        while (parent.get(currentNodeId) != null) {
            currentNodeId = parent.get(currentNodeId); 
        }
        return nodeIdToNode.get(currentNodeId);
    }

    /**
     * Unites the sets containing the two given nodes.
     * After this operation, find(node1) == find(node2).
     *
     * @param node1 the first node
     * @param node2 the second node
     */
    public void union(GraphNode<T> node1, GraphNode<T> node2) {
        GraphNode<T> root1 = find(node1);
        GraphNode<T> root2 = find(node2);
        if (root1.equals(root2)) {
            return;
        }
        if (nodeIdToSize.get(root1.getId()) > nodeIdToSize.get(node2.getId())) {
            parent.put(root2.getId(), root1.getId()); 
            nodeIdToSize.put(root1.getId(), nodeIdToSize.get(root1.getId()) + nodeIdToSize.get(root2.getId())); 
        } else {
            parent.put(root1.getId(), root2.getId()); 
            nodeIdToSize.put(root2.getId(), nodeIdToSize.get(root1.getId()) + nodeIdToSize.get(root2.getId())); 
        }
    }

    /**
     * Checks if two nodes are in the same connected component (set).
     *
     * @param node1 the first node
     * @param node2 the second node
     * @return true if both nodes are in the same set, false otherwise
     */
    public boolean sameComponent(GraphNode<T> node1, GraphNode<T> node2) {
        return find(node1).equals(find(node2));
    }
}
