package _01_线性表._03_Stack;

import _01_线性表._01_DynamicArray.Cust_ArrayList;

public class Stack<E>{
    Cust_ArrayList<E> stack = new Cust_ArrayList<>();   //栈用链表跟数组都可以实现,在这里可以选择

    public void clear(){
        stack.clear();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public void push(E element){
        stack.add(element);
    }

    public E pop(){
        return stack.remove(stack.size() - 1);
    }

    public E peek(){
        return stack.get(stack.size() - 1);
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
