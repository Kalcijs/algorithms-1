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
  public void enqueue(Item item) {
    if (item == null)
      throw new NullPointerException();
    if (size + 1 > capacity) {
      resize(capacity * 2);
    }
    arr[size] = item;
    size++;
  }
  private void resize(int newCapacity) {
    Item[] newArr = (Item[]) new Object[newCapacity];
    if (capacity > newCapacity) {
      for (int i = 0; i < newCapacity; i++) {
        newArr[i] = arr[i];
      }
    } else {
      for (int i = 0; i < capacity; i++) {
        newArr[i] = arr[i];
      }
    }
    arr = newArr;
    capacity = newCapacity;
  }

  public Item dequeue() {
    if (isEmpty())
      throw new NoSuchElementException();
    int i = StdRandom.uniform(size);
    Item item = arr[i];
    size--;
    arr[i] = arr[size];
    arr[size] = null;
    if (size < capacity / 2) {
      resize(capacity / 2);
    }
    return item;
  }
  public Item sample() {
    if (isEmpty())
      throw new NoSuchElementException();
    return arr[StdRandom.uniform(size)];
  }

  @Override
  public Iterator<Item> iterator() {
    return new Iterator<Item>() {
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
      public Item next() {
        if (current >= size || size == 0)
          throw new NoSuchElementException();
        return arr[index[current++]];
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  public static void main(String[] args) {
  }

}
