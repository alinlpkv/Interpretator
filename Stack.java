public class Stack {
    private static final int DEFSIZE = 16;
    private char[] array;
    private int head;

    public Stack() {
        array = new char[DEFSIZE];
        head = 0;

    }

    public final void push(char c) {
        array[head++] = c;
    }

    public final char pop() {
        return array[--head];
    }

    public final char top() {
        return array[head - 1];
    }

}
