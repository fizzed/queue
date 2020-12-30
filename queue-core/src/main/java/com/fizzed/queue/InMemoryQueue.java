package com.fizzed.queue;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class InMemoryQueue<E> extends AbstractQueue<E> {

    private final BlockingQueue<E> queue;
    
    public InMemoryQueue(String name) {
        super(name);
        this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public void push(E e) throws InterruptedException {
        this.checkNotClosed();
        this.queue.put(e);
    }

    @Override
    public E pop(long timeout, TimeUnit unit) throws InterruptedException {
        this.checkNotClosed();
        if (timeout < 0) {
            return this.queue.take();
        } else if (timeout == 0) {
            return this.queue.poll();
        } else {
            Objects.requireNonNull(unit, "unit was null");
            return this.queue.poll(timeout, unit);
        }
    }
    
}