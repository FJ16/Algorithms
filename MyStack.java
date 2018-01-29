public class MyStack<E> {
    //private static final double LOADFACTOR = 0.75d; // Should we have this?
    private int top;
    private E[] items;

    public MyStack(int initialSize) {
        items = (E[]) new Object[initialSize]; // 这里统一用最high level的Object来囊括所有type的class, 然后 cast 成 E[]
        // HashTable的实现另看(LinkedList<Object>, Object省略)
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public void push(E item) {
        items[++top] = item;

        if (this.size() == items.length) {
            resize();
        }
    }

    public E pop() {
        return isEmpty() ? null : items[top--];
    }

    public E peek() {
        return isEmpty() ? null : items[top];
    }

    public void resize() {
        // ? double make sense 吗
        E[] newItems = (E[]) new Object[items.length * 2];

        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

}
