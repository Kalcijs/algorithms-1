public class Subset {

  public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);
    RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
    while (!StdIn.isEmpty()) {
      String str = StdIn.readString();
      randomizedQueue.enqueue(str);
    }
    for (int i = 0; i < k; i++) {
      StdOut.println(randomizedQueue.dequeue());
    }
  }

}
