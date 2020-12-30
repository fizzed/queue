package com.fizzed.queue;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface Queue<E> extends Closeable {
    
    /**
     * Name of the queue.
     * 
     * @return The name of the queue
     */
    String getName();
 
    /**
     * Pushes the element onto the tail of this queue.
     * 
     * @param e the element to add
     * @throws InterruptedException if interrupted while waiting
     */
    void push(E e) throws InterruptedException;

    // helper methods which simply calls pop below?
    //default E popIndefinitely()
    //default E popImmediately()
    //default E popWaitable()
    
    /**
     * Retrieves and removes the head of this queue.
     * 
     * @param timeout If -1 will wait until an element exists. If 0 will return
     *      an element immediately (or null if none exists). If > 0 then will
     *      either return an element immediately if it exists or wait or return
     *      null if the timeout elapses.
     * @param unit The unit of time
     * @return The head of the queue
     * @throws InterruptedException if interrupted while waiting
     */
    
    // -1 = forever, 0 = no waiting, > 0 pop until
    E pop(long timeout, TimeUnit unit) throws InterruptedException;
    
    default List<E> drain() {
        List<E> list = new ArrayList<>();
        drain(list);
        return list;
    }
    
    default int drain(Collection<? super E> collection) {
        int count = 0;
        try {
            E element = this.pop(0, null);
            while (element != null) {
                collection.add(element);
                count++;
                element = this.pop(0, null);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Unexpected interruption", e);
        }
        return count;
    }
    
}