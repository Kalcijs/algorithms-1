import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] arr;
  private int capacity;
  private int size;

  public RandomizedQueue() {
    capacity = 1;
    arr = (Item[]) new Object[capacity];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  /** Add the item.
   * 
   * @param item
   *          to add */
  public void enqueue(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    if (size + 1 > capacity) {
      resize(capacity * 2);
    }
    arr[size] = item;
    size++;
  }

  private void resize(int newCapacity) {
    Item[] newArr = (Item[]) new Object[newCapacity];
    for (int i = 0; i < size; i++) {
      newArr[i] = arr[i];
    }
    arr = newArr;
    capacity = newCapacity;
  }

  /** Remove and return a random item.
   * 
   * @return the random item */
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    int index = StdRandom.uniform(size);
    Item item = arr[index];
    arr[index] = arr[--size];
    if (size < capacity / 2) {
      resize(capacity / 2);
    }
    return item;
  }

  /** Return (but do not remove) a random item.
   * 
   * @return the random item */
  public Item sample() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return arr[StdRandom.uniform(size)];
  }

  @Override
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }

  public static void main(String[] args) {
  }

  private class RandomizedQueueIterator implements Iterator<Item> {
    private int[] index = new int[size];
    private int current;

    {
      for (int i = 0; i < size; i++) {
        index[i] = i;
      }
      StdRandom.shuffle(index);
    }

    public boolean hasNext() {
      return current < size;
    }

    public Item next() {
      if (current >= size || size == 0) {
        throw new NoSuchElementException();
      }
      return arr[index[current++]];
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
