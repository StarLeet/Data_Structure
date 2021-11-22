package _01_线性表._02_LinkedList.CircleLinked;

public class Main {
    public static void main(String[] args) {
        CircleSingle<Integer> circleLink = new CircleSingle<>();

        circleLink.add(1);
        circleLink.add(2);
        circleLink.add(3);
        circleLink.add(4);
        circleLink.add(0,10);
        circleLink.remove(2);
        System.out.println(circleLink.indexOf(10));
        System.out.println(circleLink.toString());
    }
}
