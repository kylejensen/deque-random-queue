public class RandomizedQueue<Item> implements Iterable<Item> {
    private int first, last;
    private int size, len;
    private Item[] arr;
    
    public RandomizedQueue() {
        size = 0;
        arr = (Item[]) new Object[2];
        len = arr.length;
        first = 0;
        last = 0;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity]; // :(
        for (int i = 0; i < size; i++) {
            copy[i] = arr[i];
        }
        
        arr = copy;
    }
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        len = arr.length;
        if (size == len) {
            resize(2 * len);
        }
        arr[first++] = item;
        size++;
        if (last == len) {
            last = 0;
        }
    }
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int rand = StdRandom.uniform(size);
        Item item = arr[rand];
        arr[rand] = arr[--size];
        arr[size] = null;
        if (size == arr.length / 4) {
            resize(arr.length / 2);
        }
        if (first == arr.length) {
            first = 0;
        }
        return item;
    }
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int rand = StdRandom.uniform(size);
        return arr[rand];
    }
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() {
            return i < size;
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Queue underflow");
            Item item = arr[(i + first) % arr.length];
            i++;
            return item;
        }
    }
    public static void main(String[] args) {

    }
}