import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first, last;
    private int size;
    
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }
    
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return size;
    }
    
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node<Item> newFirst = new Node<Item>();
        newFirst.item = item;
        if (first != null) {
            newFirst.next = first;
            first.prev = newFirst;
        }
        first = newFirst;
        if (last == null) last = first;
        size++;
    }
    
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node<Item> newLast = new Node<Item>();
        newLast.item = item;
        if (last != null) {
            newLast.prev = last;
            last.next = newLast;
        }
        last = newLast;
        if (first == null) first = last;
        size++;
    }
    
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("Queue underflow");
        }
        Node<Item> oldFirst = first;
        first = first.next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        size--;
        return oldFirst.item;
    }
    
    public Item removeLast() {
        if (first == null) {
            throw new NoSuchElementException("Queue underflow");
        }
        Node<Item> oldLast = last;
        last = oldLast.prev;
        
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        size--;
        return oldLast.item;
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }
    
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;
        
        public ListIterator(Node<Item> first) {
            current = first;
        }
        
        public boolean hasNext() {
            return current != null;
        }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args) {
        
    }
}