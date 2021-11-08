package 线性表._02_LinkedList.Single;

import 线性表.AbstractList;

public class SingleLinkedList<E> extends AbstractList<E> {  //易错点,AbstractList<E>写成AbstractList报了这个错
    Node<E> head;                             //both methods have same erasure, yet neither overrides the other
    Node<E> tail;
    /*      内部节点类       */
    private static class Node<E> {
        E element;
        Node<E> next;
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
    @Override
    public void clear() {
        head = null;
        tail = null;
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
        Node<E> setPoint = findIndex(index);
        E oldElement = setPoint.element;
        setPoint.element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (head == null) {
            head = new Node<E>(element,null);
            tail = head;
            size++;
        }
        else if (index == size){
            tail.next = new Node<E>(element,null);
            tail = tail.next;
            size++;
        }else {
            rangeCheckForAdd(index);
            Node<E> preNode = findIndex(index-1);
            preNode.next = new Node<>(element,preNode.next);
            size++;
        }
    }

    @Override
    public E remove(int index) {
        E oldElement;
        rangeCheck(index);
        if (index == size - 1){ //说明要移除的是最后一个
            oldElement = tail.element;
            tail = findIndex(size - 2); //找出倒数第二个回退tail
            tail.next = null;
        }else {
            Node<E> preNode = findIndex(index-1);
            oldElement = preNode.next.element;
            preNode.next = preNode.next.next;
        }
        size--;
        return oldElement;
    }

    @Override
    public int indexOf(E element) {
        Node<E> findElement = head;
        for (int i = 0; i < size; i++) {
            if (findElement.element == element) return i;
            findElement = findElement.next;
        }
        return -1;
    }

    @Override
    public String toString() {
        if (head == null) return "LinkedList is empty";
        Node<E> tmpNode = head;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i != 0) stringBuffer.append(" ——> ");
            stringBuffer.append(tmpNode.element);
            tmpNode = tmpNode.next;
        }
        String str = stringBuffer.toString();
        return str;
    }
}
