package me.markminisce.app.basicdatastructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<T> {

    private ArrayList<T> queue;     
    private Comparator<T> comparator; 

    public PriorityQueue(Comparator<T> comparator) {
        this.queue = new ArrayList<T>(); 
        this.comparator = comparator;
    }

    public void add(T element) {
        if (element == null) {
            throw new NullPointerException("Null elements are not allowed in PriorityQueue");
        }
        queue.add(element);
        bubbleUp(queue.size() - 1);
    }

    private void bubbleUp(int index) {
        if (index == 0) {
            return; 
        }

        int parentIndex = getParentIndex(index);
        if (isDominatedBy(parentIndex, index)) {
            swap(index, parentIndex);
            bubbleUp(parentIndex);
        }
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private void swap(int oldPosition, int newPosition) {
        T temp = queue.get(newPosition); 
        queue.set(newPosition, queue.get(oldPosition));
        queue.set(oldPosition, temp);
    }

    public T remove() {
        if (queue.size() == 0) {
            throw new IllegalStateException("Queue is empty, the top element cannot be removed!"); 
        }
        
        T topElement = queue.get(0); 
        queue.set(0, queue.get(queue.size() - 1));
        queue.remove(queue.size() - 1);
        
        bubbleDown(0); 

        return topElement;
    }

    private void bubbleDown(int elementIndex) {
        if (elementIndex >= queue.size() - 1) {
            return;
        }

        int leftChildIndex = getLeftChildIndex(elementIndex);
        int rightChildIndex = getRightChildIndex(elementIndex);

        int bestCandidateChildIndex = leftChildIndex; 
        if (isDominatedBy(leftChildIndex, rightChildIndex)) {
            bestCandidateChildIndex = rightChildIndex;
        }

        if (isDominatedBy(elementIndex, bestCandidateChildIndex)) {
            swap(elementIndex, bestCandidateChildIndex);
            bubbleDown(bestCandidateChildIndex); 
        }
    }

    private boolean isDominatedBy(int currentIndex, int comparedIndex) {
        try {
            T currentElement = queue.get(currentIndex); 
            T comparedElement = queue.get(comparedIndex);

            if (currentElement == null || comparedElement == null) {
                return false;
            }

            return comparator.compare(currentElement, comparedElement) > 0;     
        }catch(IndexOutOfBoundsException e) {
            return false;
        }
    }

    private int getLeftChildIndex(int index){
        return (index * 2) + 1; 
    }

    private int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    public T peek() {
        if (queue.size() == 0) {
            throw new NoSuchElementException("Queue is empty! The top of the queue cannot be found!");
        }

        return queue.get(0); 
    }    

    public int size() {
        return queue.size();
    }
}
