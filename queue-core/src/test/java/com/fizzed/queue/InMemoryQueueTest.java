package com.fizzed.queue;

import com.fizzed.queue.InMemoryQueue;
import com.fizzed.queue.Queue;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryQueueTest {
    static private final Logger log = LoggerFactory.getLogger(InMemoryQueueTest.class);
    
    @Test
    public void pushAndPopForever() throws Exception {
        Queue<String> queue = new InMemoryQueue<>("test.queue");

        for (int i = 0; i < 100; i++) {
            queue.push("" + i);
        }

        for (int j = 0; j < 100; j++) {
            assertEquals(queue.pop(-1, null), "" + j);
        }
    }
    
    @Test
    public void pushAndPopImmediately() throws Exception {
        Queue<String> queue = new InMemoryQueue<>("test.queue");

        String item;
        
        item = queue.pop(0, null);
        assertThat(item, is(nullValue()));
        
        queue.push("test1");
        item = queue.pop(0, null);
        assertThat(item, is("test1"));
        
        item = queue.pop(0, null);
        assertThat(item, is(nullValue()));
    }
    
    @Test
    public void pushAndPopWaitable() throws Exception {
        Queue<String> queue = new InMemoryQueue<>("test.queue");

        String item;
        
        item = queue.pop(10, TimeUnit.MILLISECONDS);
        assertThat(item, is(nullValue()));
        
        queue.push("test1");
        item = queue.pop(10, TimeUnit.MILLISECONDS);
        assertThat(item, is("test1"));
        
        item = queue.pop(10, TimeUnit.MILLISECONDS);
        assertThat(item, is(nullValue()));
    }

}