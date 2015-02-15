import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {

  private Elem<T> first;
  private Elem<T> last;
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
  public void addFirst(T item) {
    if (item == null) {
      throw new NullPointerException();
    }
    if (first != null) {
      Elem<T> elem = new Elem<T>(item, first, null);
      first.prev = elem;
      first = elem;
    } else {
      first = new Elem<T>(item, null, null);
      last = first;
    }
    size++;
  }

  /** Add the item to the end.
   * 
   * @param item
   *          to add */
  public void addLast(T item) {
    if (item == null) {
      throw new NullPointerException();
    }
    if (last != null) {
      Elem<T> elem = new Elem<T>(item, null, last);
      last.next = elem;
      last = elem;
    } else {
      last = new Elem<T>(item, null, null);
      first = last;
    }
    size++;
  }

  /** Remove and return the item from the front.
   * 
   * @return item from the front */
  public T removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    if (first.next != null) {
      first.next.prev = null;
    }
    T elem = first.data;
    first = first.next;
    size--;
    return elem;
  }

  /** Remove and return the item from the end.
   * 
   * @return item from the end */
  public T removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    if (last.prev != null) {
      last.prev.next = null;
    }
    T elem = last.data;
    last = last.prev;
    size--;
    return elem;
  }

  @Override
  public Iterator<T> iterator() {
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

  private class DequeIterator implements Iterator<T> {
    private Elem<T> current = first;

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      T elem = current.data;
      current = current.next;
      return elem;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
