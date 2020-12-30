package com.fizzed.queue;

import java.io.IOException;
import java.util.Objects;

abstract public class AbstractQueue<E> implements Queue<E> {
    
    protected final String name;
    protected boolean closed;

    public AbstractQueue(String name) {
        Objects.requireNonNull(name, "name was null");
        this.name = name;
        this.closed = false;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
    
    @Override
    public void close() throws IOException {
        this.closed = true;
    }
    
    protected void checkNotClosed() {
        if (this.closed) {
            throw new IllegalStateException("queue closed");
        }
    }
    
}