package 线性表._02_LinkedList.CircleLinked;

import 线性表.AbstractList;

public class CircleSingle<E> extends AbstractList<E> {
    Node<E> head;

    private static class Node<E>{
        E element;
        Node<E> next;
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public void clear() {
        if (size ==1 || head == null) {
            head = null;
            return;
        }
        Node<E> tail = head;
        while (tail.next != head){
            tail = tail.next;
        }
        tail.next = null;
        head = null;
    }

    private Node<E> findIndex(int index){
        rangeCheck(index);
        Node<E> indexNode = head;
        for (int i = 0; i < index; i++) {
            indexNode = indexNode.next;
        }
        return indexNode;
    }

    @Override
    public E get(int index) {
        return findIndex(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> setIndex = findIndex(index);
        E oldElement = setIndex.element;
        setIndex.element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (size == 0){
            head = new Node<E>(element,null);
            head.next = head;
        } else if (index == 0){
            Node<E> tail = findIndex(size-1);
            tail.next = new Node<E>(element,head);
            head = tail.next;
        }else {
            Node<E> preNode = findIndex(index - 1);
            preNode.next = new Node<>(element, preNode.next);
        }
        size++;
    }

    @Override
    public E remove(int index) {
        if (size == 0) return null;
        rangeCheck(index);
        E oldElement;
        if (size == 1){
            oldElement = head.element;
            clear();
        }else if (index == 0){
            Node<E> tail = findIndex(size - 1);
            tail.next = head.next;
            oldElement = head.element;
            head = head.next;
        }else{
            Node<E> preNode = findIndex(index-1);
            oldElement = preNode.next.element;
            preNode.next = preNode.next.next;
        }
        size--;
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        Node<E> indexNode = head;
        int index = ELEMENT_NOT_FOUND;
        for (int i = 0; i < size; i++) {
            if(indexNode.element == element) {
                index = i;
                break;
            }
            indexNode = indexNode.next;
        }
        return index;
    }

    public String toString() {
        if (head == null) return "CircleLink is empty";
        Node<E> tmpNode = head;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i != 0) stringBuffer.append(" ——> ");
            stringBuffer.append(tmpNode.element);
            tmpNode = tmpNode.next;
        }
        stringBuffer.append(" ——> " + tmpNode.element);
        String str = stringBuffer.toString();
        return str;
    }
}
