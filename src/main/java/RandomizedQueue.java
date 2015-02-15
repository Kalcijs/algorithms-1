import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<T> implements Iterable<T> {

  private T[] arr;
  private int capacity;
  private int size;

  public RandomizedQueue() {
    capacity = 1;
    arr = (T[]) new Object[capacity];
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
  public void enqueue(T item) {
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
    T[] newArr = (T[]) new Object[newCapacity];
    for (int i = 0; i < size; i++) {
      newArr[i] = arr[i];
    }
    arr = newArr;
    capacity = newCapacity;
  }

  /** Remove and return a random item.
   * 
   * @return the random item */
  public T dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    int index = StdRandom.uniform(size);
    T item = arr[index];
    arr[index] = arr[--size];
    if (size < capacity / 2) {
      resize(capacity / 2);
    }
    return item;
  }

  /** Return (but do not remove) a random item.
   * 
   * @return the random item */
  public T sample() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return arr[StdRandom.uniform(size)];
  }

  @Override
  public Iterator<T> iterator() {
    return new RandomizedQueueIterator();
  }

  public static void main(String[] args) {
  }

  private class RandomizedQueueIterator implements Iterator<T> {
    private int[] index = new int[size];
    private int current;

    {
      for (int i = 0; i < size; i++) {
        index[i] = i;
      }
      StdRandom.shuffle(index);
    }

    @Override
    public boolean hasNext() {
      return current < size;
    }

    @Override
    public T next() {
      if (current >= size || size == 0) {
        throw new NoSuchElementException();
      }
      return arr[index[current++]];
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
