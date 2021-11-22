package _01_线性表._02_LinkedList.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> link = new LinkedList<>();
        link.add(5);
        link.add(6);
        link.add(1);
        link.add(7);
        link.add(2);
        link.add(3);
        link.remove(3);
        System.out.println(link.toString());
    }
}
