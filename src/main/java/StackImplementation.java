import java.util.Arrays;

public class StackImplementation {

    public static void main(String[] args) {
        MyStack stack = new MyStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    static class MyStack<E> {

        int size = 0;
        Object objects[];

        MyStack() {
            objects = new Object[10];
        }

        MyStack(int capacity) {
            objects = new Object[capacity];
        }

        void ensureCapacity() {
            int newSize = objects.length * 2;
            objects = Arrays.copyOf(objects, newSize);
        }

        public void push(E e) {
            if (objects.length == size) {
                ensureCapacity();
            }
            objects[size] = e;
            size++;
        }

        public E pop() {
            E element = (E) objects[size - 1];
            objects[size - 1] = null;
            size--;
            return element;
        }

        public E peek() {
            return (E) objects[size - 1];
        }
    }
}
