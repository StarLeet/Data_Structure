package 线性表._01_DynamicArray;
@SuppressWarnings("ALL")
public class Main {
    public static void main(String[] args) {
        Cust_ArrayList<Integer> list = new Cust_ArrayList<>();
        Main.addTest(list,20);
        Main.clearTest(list);

    }

    public static void clearTest(Cust_ArrayList list){
        list.printList();
        list.clear();
        System.gc();   //提醒垃圾回收机制运行
    }

    public static void addTest(Cust_ArrayList list,int num){
        for (int i = 0; i < 20; i++) {
            list.add(new Person("小" + i,(int)(Math.random()*100)));
        }
    }
}


