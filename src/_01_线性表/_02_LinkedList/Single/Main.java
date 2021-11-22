package _01_线性表._02_LinkedList.Single;

public class Main {
    public static void main(String[] args) {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
        singleLinkedList.add(2);
        singleLinkedList.add(3);
        singleLinkedList.add(4);
        singleLinkedList.add(5);
        singleLinkedList.remove(2);
        singleLinkedList.add(1,10);
        System.out.println(singleLinkedList.indexOf(10));
        System.out.println(singleLinkedList.toString());
        singleLinkedList.clear();
    }
}
