class MinStack {
    private Stack<Integer> st;
    private Stack<Integer> minSt;

    public MinStack() {
        st = new Stack<>();
        minSt = new Stack<>();
    }
    
    public void push(int value) {
        st.push(value);
        if(minSt.isEmpty() || value <= minSt.peek()){
            minSt.push(value);
        }
    }
    
    public void pop() {
        // Agar main stack ka top element hi minimum element tha, toh minSt se bhi hatao
        if (st.peek().equals(minSt.peek())) {
            minSt.pop();
        }
        st.pop();
    }
    
    public int top() {
        return st.peek();
    }
    
    public int getMin() {
        return minSt.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */