import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DequeTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testIsEmpty() throws Exception {
    Deque deque = new Deque();
    assertNotNull(deque);
    assertTrue(deque.isEmpty());
    deque.addFirst("first");
    assertFalse(deque.isEmpty());
  }

  @Test
  public void testSize() throws Exception {
    Deque deque = new Deque();
    assertNotNull(deque);
    assertEquals(0, deque.size());
    deque.addFirst("first");
    assertEquals(1, deque.size());
    deque.addLast("last");
    assertEquals(2, deque.size());
    deque.removeFirst();
    assertEquals(1, deque.size());
    deque.removeFirst();
    assertEquals(0, deque.size());
  }

  @Test
  public void testAddRemoveFirst() throws Exception {
    Deque<String> deque = new Deque<String>();
    assertNotNull(deque);
    deque.addFirst("one");
    deque.addFirst("two");
    deque.addFirst("three");
    deque.addFirst("four");
    deque.addFirst("five");
    verifyIterator(deque.iterator(), "five", "four", "three", "two", "one");
    assertEquals("five", deque.removeFirst());
    assertEquals("four", deque.removeFirst());
    assertEquals("three", deque.removeFirst());
    assertEquals("two", deque.removeFirst());
    assertEquals("one", deque.removeFirst());
    assertTrue(deque.isEmpty());
    assertEquals(0, deque.size());
  }

  @Test
  public void testAddRemoveLast() throws Exception {
    Deque<String> deque = new Deque<String>();
    assertNotNull(deque);
    deque.addLast("one");
    deque.addLast("two");
    deque.addLast("three");
    deque.addLast("four");
    deque.addLast("five");
    verifyIterator(deque.iterator(), "one", "two", "three", "four", "five");
    assertEquals("five", deque.removeLast());
    assertEquals("four", deque.removeLast());
    assertEquals("three", deque.removeLast());
    assertEquals("two", deque.removeLast());
    assertEquals("one", deque.removeLast());
    assertTrue(deque.isEmpty());
    assertEquals(0, deque.size());
  }

  private <T> void verifyIterator(Iterator<T> iterator, T... items) {
    assertNotNull(iterator);
    for (T item : items) {
      assertTrue(iterator.hasNext());
      assertEquals(item, iterator.next());
    }
    try {
      iterator.next();
      fail("Should throw NoSuchElementException");
    } catch (NoSuchElementException e) {
      // OK
    }
  }

  @Test
  public void testNullPointerException() throws Exception {
    Deque<String> deque = new Deque<String>();
    try {
      deque.addFirst(null);
      fail("Should throw NullPointerException");
    } catch (NullPointerException e) {
      // oK
    }
    try {
      deque.addLast(null);
      fail("Should throw NullPointerException");
    } catch (NullPointerException e) {
      // oK
    }
  }

  @Test
  public void testNoSuchElementException() throws Exception {
    Deque<String> deque = new Deque<String>();
    try {
      deque.removeFirst();
      fail("Should throw NoSuchElementException");
    } catch (NoSuchElementException e) {
      // OK
    }
    try {
      deque.removeLast();
      fail("Should throw NoSuchElementException");
    } catch (NoSuchElementException e) {
      // OK
    }
  }

  @Test
  public void testUnsupportedOperationException() throws Exception {
    Deque<Integer> deque = new Deque<Integer>();
    deque.addFirst(1);
    try {
      Iterator<Integer> iterator = deque.iterator();
      assertTrue(iterator.hasNext());
      iterator.remove();
      fail("Should throw UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
      // oK
    }
  }

  @Test
  public void testAddFirstRemoveLast() throws Exception {
    Deque<String> deque = new Deque<String>();
    assertNotNull(deque);
    deque.addFirst("one");
    assertEquals("one", deque.removeLast());
    try {
      deque.removeLast();
      fail("Should fail on empty deque.");
    } catch (NoSuchElementException e) {
      // OK
    }
    try {
      deque.removeFirst();
      fail("Should fail on empty deque.");
    } catch (NoSuchElementException e) {
      // OK
    }
    assertTrue(deque.isEmpty());
    assertEquals(0, deque.size());
  }

  @Test
  public void testAddLastRemoveFirst() throws Exception {
    Deque<String> deque = new Deque<String>();
    assertNotNull(deque);
    deque.addLast("one");
    assertEquals("one", deque.removeFirst());
    try {
      deque.removeFirst();
      fail("Should fail on empty deque.");
    } catch (NoSuchElementException e) {
      // OK
    }
    try {
      deque.removeLast();
      fail("Should fail on empty deque.");
    } catch (NoSuchElementException e) {
      // OK
    }
    assertTrue(deque.isEmpty());
    assertEquals(0, deque.size());
  }

  @Test
  public void testAddFirstRemoveLast2() throws Exception {
    Deque<String> deque = new Deque<String>();
    assertNotNull(deque);
    deque.addFirst("two");
    deque.addFirst("one");
    verifyIterator(deque.iterator(), "one", "two");
    assertEquals("two", deque.removeLast());
    assertEquals("one", deque.removeLast());
    try {
      deque.removeLast();
      fail("Should fail on empty deque.");
    } catch (NoSuchElementException e) {
      // OK
    }
    try {
      deque.removeFirst();
      fail("Should fail on empty deque.");
    } catch (NoSuchElementException e) {
      // OK
    }
    assertTrue(deque.isEmpty());
    assertEquals(0, deque.size());
  }

  @Test
  public void testAddLastRemoveFirst2() throws Exception {
    Deque<String> deque = new Deque<String>();
    assertNotNull(deque);
    deque.addLast("one");
    deque.addLast("two");
    verifyIterator(deque.iterator(), "one", "two");
    assertEquals("one", deque.removeFirst());
    assertEquals("two", deque.removeFirst());
    try {
      deque.removeFirst();
      fail("Should fail on empty deque.");
    } catch (NoSuchElementException e) {
      // OK
    }
    try {
      deque.removeLast();
      fail("Should fail on empty deque.");
    } catch (NoSuchElementException e) {
      // OK
    }
    assertTrue(deque.isEmpty());
    assertEquals(0, deque.size());
  }

  @Test
  public void testAddFirstRemoveFirst() throws Exception {
    Deque<String> deque = new Deque<String>();
    assertNotNull(deque);
    deque.addFirst("one");
    assertEquals("one", deque.removeLast());
    try {
      deque.removeFirst();
      fail("Should fail on empty deque.");
    } catch (NoSuchElementException e) {
      // OK
    }
    assertTrue(deque.isEmpty());
    assertEquals(0, deque.size());
  }

  @Test
  public void testAddLastRemoveLast() throws Exception {
    Deque<String> deque = new Deque<String>();
    assertNotNull(deque);
    deque.addLast("one");
    assertEquals("one", deque.removeFirst());
    try {
      deque.removeLast();
      fail("Should fail on empty deque.");
    } catch (NoSuchElementException e) {
      // OK
    }
    assertTrue(deque.isEmpty());
    assertEquals(0, deque.size());
  }

}
