import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private Elem<Item> first;
  private Elem<Item> last;
  private int size;

  public Deque() {
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  /** Add the item to the front.
   * 
   * @param item
   *          to add */
  public void addFirst(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    if (first != null) {
      Elem<Item> elem = new Elem<Item>(item, first, null);
      first.prev = elem;
      first = elem;
    } else {
      first = new Elem<Item>(item, null, null);
      last = first;
    }
    size++;
  }

  /** Add the item to the end.
   * 
   * @param item
   *          to add */
  public void addLast(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    if (last != null) {
      Elem<Item> elem = new Elem<Item>(item, null, last);
      last.next = elem;
      last = elem;
    } else {
      last = new Elem<Item>(item, null, null);
      first = last;
    }
    size++;
  }

  /** Remove and return the item from the front.
   * 
   * @return item from the front */
  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    if (first.next != null) {
      first.next.prev = null;
    }
    Item elem = first.data;
    first = first.next;
    size--;
    return elem;
  }

  /** Remove and return the item from the end.
   * 
   * @return item from the end */
  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    if (last.prev != null) {
      last.prev.next = null;
    }
    Item elem = last.data;
    last = last.prev;
    size--;
    return elem;
  }

  @Override
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  public static void main(String[] args) {
  }

  private static class Elem<T> {
    private T data;
    private Elem<T> next;
    private Elem<T> prev;

    public Elem(T data, Elem<T> next, Elem<T> prev) {
      this.data = data;
      this.next = next;
      this.prev = prev;
    }

  }

  private class DequeIterator implements Iterator<Item> {
    private Elem<Item> current = first;

    public boolean hasNext() {
      return current != null;
    }

    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Item elem = current.data;
      current = current.next;
      return elem;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
