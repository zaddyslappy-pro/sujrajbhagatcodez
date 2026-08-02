class MyStack {
    // 1. Declare the queue(s) at class level
    private Queue<Integer> q;
    public MyStack() {
        // 2. Initialize inside the constructor
        q = new LinkedList<>();
    }
    
    public void push(int x) {
        q.add(x);
        for(int i=0; i<q.size()-1; i++){
            // Rotate the queue to keep the latest element at the front
            q.add(q.remove());
        }
    }

    // Removes the element on top of the stack and returns it
    public int pop() {
        return q.remove();
    }
    
    public int top() {
        return q.peek();
        
    }
    
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */