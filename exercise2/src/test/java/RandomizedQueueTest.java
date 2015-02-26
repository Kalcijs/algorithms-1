import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class RandomizedQueueTest {

  @Test
  public void testIsEmpty() throws Exception {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    assertNotNull(queue);
    assertTrue(queue.isEmpty());
    queue.enqueue("");
    assertFalse(queue.isEmpty());
    queue.dequeue();
    assertTrue(queue.isEmpty());
  }

  @Test
  public void testSize() throws Exception {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    assertNotNull(queue);
    assertEquals(0, queue.size());
    queue.enqueue("1");
    assertEquals(1, queue.size());
    queue.enqueue("2");
    assertEquals(2, queue.size());
    queue.dequeue();
    assertEquals(1, queue.size());
  }

  @Test
  public void testEnqueueDequeue() throws Exception {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    assertNotNull(queue);
    queue.enqueue("one");
    queue.enqueue("two");
    queue.enqueue("three");
    queue.enqueue("four");
    queue.enqueue("five");
    assertEquals(5, queue.size());
    verifyIterator(queue.iterator(), "one", "two", "three", "four", "five");
    queue.dequeue();
    queue.dequeue();
    queue.dequeue();
    queue.dequeue();
    queue.dequeue();
    assertEquals(0, queue.size());
    Iterator<String> iterator = queue.iterator();
    assertNotNull(iterator);
    try {
      iterator.next();
      fail("Should throw NoSuchElementException");
    } catch (NoSuchElementException e) {
      // OK
    }
  }

  @Test
  public void testException() throws Exception {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    try {
      queue.enqueue(null);
      fail("Should throw NullPointerException");
    } catch (NullPointerException e) {
      // OK
    }
    try {
      queue.dequeue();
      fail("Should throw NoSuchElementException");
    } catch (NoSuchElementException e) {
      // OK
    }
  }

  @Test
  public void testSample() throws Exception {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    assertNotNull(queue);
    queue.enqueue("one");
    queue.enqueue("two");
    queue.enqueue("three");
    queue.enqueue("four");
    queue.enqueue("five");
    assertEquals(5, queue.size());
    List<String> items = Arrays.asList("one", "two", "three", "four", "five");
    for (int i = 0; i < 25; i++) {
      String item = queue.sample();
      assertNotNull(item);
      assertTrue(items.contains(item));
      assertEquals(5, queue.size());
    }
  }

  @Test
  public void testIterator() throws Exception {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    assertNotNull(queue);
    queue.enqueue("1");
    Iterator<String> iterator = queue.iterator();
    assertNotNull(iterator);
    assertTrue(iterator.hasNext());
    try {
      iterator.remove();
      fail("Should throw UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      // OK
    }
    assertEquals("1", iterator.next());
    assertFalse(iterator.hasNext());
  }

  private <T> void verifyIterator(Iterator<T> iterator, T... items) {
    assertNotNull(iterator);
    List<T> itemsList = Arrays.asList(items);
    int count = 0;
    while (iterator.hasNext()) {
      T item = iterator.next();
      assertNotNull(item);
      assertTrue(itemsList.contains(item));
      count++;
    }
    assertEquals(itemsList.size(), count);
    try {
      iterator.next();
      fail("Should throw NoSuchElementException");
    } catch (NoSuchElementException e) {
      // OK
    }
  }

}
