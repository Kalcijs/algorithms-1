import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private Elem<Item> first, last;
  private int size;

  public Deque() {
  }

  public boolean isEmpty() {
    return size == 0;
  }
  public int size() {
    return size;
  }

  public void addFirst(Item item) {
    if (item == null)
      throw new NullPointerException();
    if (first != null) {
      Elem<Item> e = new Elem<Item>(item, first, null);
      first.prev = e;
      first = e;
    } else {
      first = new Elem<Item>(item, null, null);
      last = first;
    }
    size++;
  }

  public void addLast(Item item) {
    if (item == null)
      throw new NullPointerException();
    if (last != null) {
      Elem<Item> e = new Elem<Item>(item, null, last);
      last.next = e;
      last = e;
    } else {
      last = new Elem<Item>(item, null, null);
      first = last;
    }
    size++;
  }
  public Item removeFirst() {
    if (isEmpty())
      throw new NoSuchElementException();
    Item e = first.data;
    if (first.next != null)
      first.next.prev = null;
    first = first.next;
    size--;
    return e;
  }
  public Item removeLast() {
    if (isEmpty())
      throw new NoSuchElementException();
    Item e = last.data;
    if (last.prev != null)
      last.prev.next = null;
    last = last.prev;
    size--;
    return e;
  }
  @Override
  public Iterator<Item> iterator() {
    return new Iterator<Item>() {
      private Elem<Item> current = first;
      @Override
      public boolean hasNext() {
        return current != null;
      }

      @Override
      public Item next() {
        if (!hasNext())
          throw new NoSuchElementException();
        Item e = current.data;
        current = current.next;
        return e;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  public static void main(String[] args) {
  }

  private static class Elem<T> {
    private T data;
    private Elem<T> next, prev;
    public Elem(T data, Elem<T> next, Elem<T> prev) {
      this.data = data;
      this.next = next;
      this.prev = prev;
    }

  }
}
