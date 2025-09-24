package me.markminisce.app.basicdatastructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.markminisce.app.model.Student;

public class UnionFindTests {

    private UnionFind<Student> testObj;

    @BeforeEach
    public void setup() {
        testObj = new UnionFind<>();
    }

    @Test
    void addTest() {
        GraphNode<Student> n1 = new GraphNode<>(Student.builder().firstName("Alice").lastName("Away").build());
        GraphNode<Student> n2 = new GraphNode<>(Student.builder().firstName("Bob").lastName("Bart").build());
        testObj.add(n1); 
        testObj.add(n2);
        assertNotNull(testObj);
    }

    @Test
    void addDuplicateNodeTest() {
        GraphNode<Student> n1 = new GraphNode<>(Student.builder().id(1).firstName("Alice").lastName("Away").build());
        testObj.add(n1);
        testObj.add(n1); // Should not throw or break
        assertEquals(n1, testObj.find(n1));
    }

    @Test
    void findOnNonexistentNodeTest() {
        GraphNode<Student> n1 = new GraphNode<>(Student.builder().id(99).firstName("Ghost").lastName("Node").build());
        assertNull(testObj.find(n1));
    }

    @Test
    void unionOnNonexistentNodesTest() {
        GraphNode<Student> n1 = new GraphNode<>(Student.builder().id(100).firstName("A").lastName("B").build());
        GraphNode<Student> n2 = new GraphNode<>(Student.builder().id(101).firstName("C").lastName("D").build());
        assertThrows(NullPointerException.class, () -> testObj.union(n1, n2));
    }

    @Test
    void unionWithSelfTest() {
        GraphNode<Student> n1 = new GraphNode<>(Student.builder().id(1).firstName("Alice").lastName("Away").build());
        testObj.add(n1);
        testObj.union(n1, n1); // Should be a no-op
        assertEquals(n1, testObj.find(n1));
        assertTrue(testObj.sameComponent(n1, n1));
    }

    @Test
    void sameComponentDifferentSetsTest() {
        GraphNode<Student> n1 = new GraphNode<>(Student.builder().id(1).firstName("Alice").lastName("Away").build());
        GraphNode<Student> n2 = new GraphNode<>(Student.builder().id(2).firstName("Bob").lastName("Bart").build());
        testObj.add(n1);
        testObj.add(n2);
        assertFalse(testObj.sameComponent(n1, n2));
    }

    @Test
    void emptyUnionFindTest() {
        GraphNode<Student> n1 = new GraphNode<>(Student.builder().id(1).firstName("Alice").lastName("Away").build());
        assertNull(testObj.find(n1));
        assertThrows(NullPointerException.class, () -> testObj.sameComponent(n1, n1));
    }

    @Test
    void singleNodeTest() {
        GraphNode<Student> n1 = new GraphNode<>(Student.builder().id(1).firstName("Alice").lastName("Away").build());
        testObj.add(n1);
        assertEquals(n1, testObj.find(n1));
        assertTrue(testObj.sameComponent(n1, n1));
    }

    @Test
    void multipleUnionsSingleComponentTest() {
        GraphNode<Student> n1 = new GraphNode<>(Student.builder().id(1).firstName("A").lastName("A").build());
        GraphNode<Student> n2 = new GraphNode<>(Student.builder().id(2).firstName("B").lastName("B").build());
        GraphNode<Student> n3 = new GraphNode<>(Student.builder().id(3).firstName("C").lastName("C").build());
        testObj.add(n1);
        testObj.add(n2);
        testObj.add(n3);
        testObj.union(n1, n2);
        testObj.union(n2, n3);
        assertTrue(testObj.sameComponent(n1, n3));
        assertEquals(testObj.find(n1), testObj.find(n3));
    }

    @Test
    void basicUnionTest() {
        GraphNode<Student> n1 = new GraphNode<>(
            Student.builder().id(1).firstName("Alice").lastName("Away").build());
        GraphNode<Student> n2 = new GraphNode<>(
            Student.builder().id(2).firstName("Bob").lastName("Bart").build());
        testObj.add(n1); 
        testObj.add(n2);
        testObj.union(n1, n2); 

        GraphNode<Student> result = testObj.find(n2);
        assertEquals(n2, result);
        assertTrue(testObj.sameComponent(n1, n2)); 
    } 
    
    @Test
    void complexUnionTest() {
        GraphNode<Student> n1 = new GraphNode<>(
            Student.builder().id(1).firstName("Alice").lastName("Away").build());
        GraphNode<Student> n2 = new GraphNode<>(
            Student.builder().id(2).firstName("Bob").lastName("Bart").build());

        GraphNode<Student> n3 = new GraphNode<>(
            Student.builder().id(3).firstName("Charlie").lastName("Novak").build());

        GraphNode<Student> n4 = new GraphNode<>(
            Student.builder().id(4).firstName("David").lastName("Dugan").build());

        GraphNode<Student> n5 = new GraphNode<>(
            Student.builder().id(5).firstName("Clara").lastName("Whitman").build()); 
            
        GraphNode<Student> n6 = new GraphNode<>(
            Student.builder().id(6).firstName("Miles").lastName("Thornton").build()); 

        GraphNode<Student> n7 = new GraphNode<>(
            Student.builder().id(7).firstName("Ivy").lastName("Donnelly").build()); 
        
        testObj.add(n1);
        testObj.add(n2);
        testObj.add(n3);
        testObj.add(n4);
        testObj.add(n5);
        testObj.add(n6);
        testObj.add(n7);

        testObj.union(n3, n5);
        testObj.union(n2, n7);
        testObj.union(n2, n4);
        testObj.union(n4, n6);

        assertEquals(n5, testObj.find(n5)); 
        assertEquals(n5, testObj.find(n3)); 
        assertTrue(testObj.sameComponent(n3, n5)); 

        assertEquals(n7, testObj.find(n7));
        assertEquals(n7, testObj.find(n2));
        assertEquals(n7, testObj.find(n4));
        assertEquals(n7, testObj.find(n6));
        assertTrue(testObj.sameComponent(n7, n6)); 
        assertTrue(testObj.sameComponent(n6, n4)); 
    }
}
