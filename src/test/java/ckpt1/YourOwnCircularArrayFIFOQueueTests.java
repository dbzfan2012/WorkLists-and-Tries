package ckpt1;

import cse332.interfaces.worklists.FixedSizeFIFOWorkList;
import cse332.types.AlphabeticString;
import datastructures.dictionaries.HashTrieMap;
import datastructures.worklists.CircularArrayFIFOQueue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


public class YourOwnCircularArrayFIFOQueueTests {

    /**
     * After adding an element into the queue, test that size()
     * is updated.
     */
    @Test()
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void test_size_afterInsertion_incrementsByOne() {
        CircularArrayFIFOQueue<Integer> queue = new CircularArrayFIFOQueue<>(5);
        // Implement this test!
        queue.add(123454321);
        assertEquals(queue.size(), 1, "The queue size is not what it should be!");
    }

    /**
     * Test that add() throws the correct exception when the queue is out of capacity.
     */
    @Test()
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void test_add_isFull_throwsException() {
        CircularArrayFIFOQueue<Integer> queue = new CircularArrayFIFOQueue<>(5);
        // Implement this test!
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        assertThrows(IllegalStateException.class, () -> {
            queue.add(75);
        });
    }

    /**
     * Test that the front pointer of the queue can actually "loop around".
     * <p>
     * For example, say that your capacity is 5. Then, if a client adds 5 elements,
     * calls next() 5 times, and then insert again, your code should be able to handle
     * the location of front pointer correctly.
     * <p>
     * This one is the hardest, since it requires you to trigger the edge case as a client
     * and then test its behavior!
     */
    @Test()
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void test_addNext_cyclesEntireQueue_returnsCorrect() {
        CircularArrayFIFOQueue<Integer> queue = new CircularArrayFIFOQueue<>(5);
        // Implement this test!
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        for (int i = 0; i < 5; i++) {
            queue.next();
        }
        queue.add(10000);
        assertEquals(queue.peek(), 10000, "The queue peeked at the wrong index!");
    }
}
