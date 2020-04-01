package alg;

import java.util.LinkedList;
import java.util.Queue;

public class StackWithQueue {
    private static Queue<String> queue1 = new LinkedList();
    private static Queue<String> queue2 = new LinkedList();

    public static void push(String item) {
        if (queue2.isEmpty()) {
            queue1.add(item);
        } else {
            queue2.add(item);
        }
    }


    public static String pop() {
        String item;
        if (!queue2.isEmpty()) {
            while (queue2.size() > 1) {
                queue1.add(queue2.remove());
            }
            item = queue2.remove();
        } else {
            while(queue1.size() > 1) {
                queue2.add(queue1.remove());
            }
            item = queue1.remove();
        }
        return item;
    }
}
