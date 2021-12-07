package _06_优先级队列;

import _06_优先级队列.PriorityQueue.PriorityQueue;

/**
 * @ClassName Main
 * @Description
 * @Author StarLee
 * @Date 2021/12/6
 */

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Person> queue = new PriorityQueue<>();
        queue.enQueue(new Person("Jack", 2));
        queue.enQueue(new Person("Rose", 10));
        queue.enQueue(new Person("Jake", 5));
        queue.enQueue(new Person("James", 15));

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }

    }
}
