package alg;

import java.util.Stack;

public class QueueWithStack {
    private Stack<String> inStack = new Stack();
    private Stack<String> outStack = new Stack();

    public void enqueue(String item) {
        inStack.push(item);
    }

    public String dequeue(){
        String item = null;
        if (!outStack.isEmpty()) {
            item =  outStack.pop();
        } else {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
            if(!outStack.isEmpty()) {
                item = outStack.pop();
            }
        }
        return item;
    }
}
