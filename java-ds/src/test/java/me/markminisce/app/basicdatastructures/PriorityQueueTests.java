package me.markminisce.app.basicdatastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriorityQueueTests {

    PriorityQueue<Integer> minQueueTestObj; 

    @BeforeEach
    void setup() {
        minQueueTestObj = new PriorityQueue<Integer>((x, y) -> Integer.compare(x, y)); 
    }

    @Test
    void addNullTest() {
        assertThrows(NullPointerException.class, () -> minQueueTestObj.add(null));
    }

    @Test
    void maxHeapTest() {
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
        maxQueue.add(1);
        maxQueue.add(5);
        maxQueue.add(3);
        assertEquals(maxQueue.peek(), 5);
        assertEquals(maxQueue.remove(), 5);
        assertEquals(maxQueue.remove(), 3);
        assertEquals(maxQueue.remove(), 1);
    }

    @Test
    void removeSingleElementTest() {
        minQueueTestObj.add(42);
        assertEquals(minQueueTestObj.remove(), 42);
        assertThrows(IllegalStateException.class, () -> minQueueTestObj.remove());
    }

    @Test
    void add1Test() {
        minQueueTestObj.add(1);
        assertTrue(minQueueTestObj.size() == 1); 
    }

    @Test
    void addManyTest() {
        minQueueTestObj.add(5);
        minQueueTestObj.add(1);
        assertEquals(minQueueTestObj.size(), 2);
        assertEquals(minQueueTestObj.peek(), 1);
    }

    @Test
    void addManyComplexTest() {
        minQueueTestObj.add(5);
        minQueueTestObj.add(10);
        minQueueTestObj.add(40);
        minQueueTestObj.add(1);
        minQueueTestObj.add(1);
        minQueueTestObj.add(1);
        minQueueTestObj.add(0);
        minQueueTestObj.add(-2);
        assertEquals(minQueueTestObj.size(), 8);
        assertEquals(minQueueTestObj.peek(), -2);
    }

    @Test
    void removeBasicTest() {
        minQueueTestObj.add(10);
        minQueueTestObj.add(7);
        minQueueTestObj.add(8);
        minQueueTestObj.add(20);
        int removedElement = minQueueTestObj.remove(); 
        assertEquals(removedElement, 7);
        assertEquals(minQueueTestObj.size(), 3);
        assertEquals(minQueueTestObj.peek(), 8);
    }

    @Test
    void removeNothingTest() {
        Throwable exception = assertThrows(IllegalStateException.class, () -> minQueueTestObj.remove()); 
        assertTrue(exception.getMessage().toLowerCase().contains("queue is empty"));
    }

    @Test
    void removeManyTest() {
        int[] testElements = {50, 25, 5, 75, 70, -50, 0, -100};
        Arrays.stream(testElements).forEach(number -> minQueueTestObj.add(number));

        assertEquals(minQueueTestObj.peek(), -100); 
        assertEquals(minQueueTestObj.size(), 8); 

        int top = minQueueTestObj.remove(); 
        assertEquals(top, -100); 

        //remove rest of elements
        top = minQueueTestObj.remove(); 
        assertEquals(top, -50); 

        top = minQueueTestObj.remove(); 
        assertEquals(top, 0);
        
        top = minQueueTestObj.remove(); 
        assertEquals(top, 5);

        top = minQueueTestObj.remove(); 
        assertEquals(top, 25);

        top = minQueueTestObj.remove(); 
        assertEquals(top, 50);

        top = minQueueTestObj.remove(); 
        assertEquals(top, 70);

        top = minQueueTestObj.remove(); 
        assertEquals(top, 75);
    }

    @Test
    void peekEmptyTest() {
        assertEquals(minQueueTestObj.size(), 0);
        Throwable exception = assertThrows(NoSuchElementException.class, 
            () -> minQueueTestObj.peek()
        );  

        assertTrue(exception.getMessage().toLowerCase().contains("queue is empty"));
    }
}
