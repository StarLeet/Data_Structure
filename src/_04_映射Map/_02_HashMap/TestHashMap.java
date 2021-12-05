package _04_映射Map._02_HashMap;

import _04_映射Map.Map;
import _04_映射Map._01_TreeMap.TreeMap;
import _04_映射Map._02_HashMap.TestModel.Key;
import _04_映射Map._02_HashMap.TestModel.SubKey1;
import _04_映射Map._02_HashMap.TestModel.SubKey2;
import _04_映射Map._03_LinkedHashMap.LinkedHashMap;
import _0_Tools.CountTime;
import _0_Tools.MyAssert;
import _0_Tools.ReadFile.FileInfo;
import _0_Tools.ReadFile.Files;

/**
 * @ClassName TestHashMap
 * @Description
 * @Author StarLee
 * @Date 2021/12/4
 */

public class TestHashMap {
    static void test1Map(Map<String, Integer> map, String[] words) {
        CountTime.test(map.getClass().getName(), new CountTime.Task() {
            @Override
            public void execute() {
                for (String word : words) {
                    Integer count = map.get(word);
                    count = count == null ? 0 : count;
                    map.put(word, count + 1);
                }
                System.out.println(map.size()); // 17188

                int count = 0;
                for (String word : words) {
                    Integer i = map.get(word);
                    count += i == null ? 0 : i;
                    map.remove(word);
                }
                MyAssert.test(count == words.length);
                MyAssert.test(map.size() == 0);
            }
        });
    }

    /**
     *  @MethodName test1
     *  @Description  比较TreeMap与HashMap的效率
     *  @Param []
     *  @return void
     */
    static void test1() {
        String filepath = "E:\\Codefield\\java\\Java_source\\src\\java.base\\java\\util\\concurrent";
        FileInfo fileInfo = Files.read(filepath, null);
        String[] words = fileInfo.words();

        System.out.println("总行数：" + fileInfo.getLines());
        System.out.println("单词总数：" + words.length);
        System.out.println("-------------------------------------");

        test1Map(new TreeMap<>(), words);
        test1Map(new HashMap<>(), words);
        test1Map(new LinkedHashMap<>(), words);
    }

    /**
     *  @MethodName test2
     *  @Description  测试HashMap的同Key覆盖
     *  @Param [map]
     *  @return void
     */
    static void test2(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            map.put(new Key(i), i + 5);
        }
        MyAssert.test(map.size() == 20);
        MyAssert.test(map.get(new Key(4)) == 4);
        MyAssert.test(map.get(new Key(5)) == 10);
        MyAssert.test(map.get(new Key(6)) == 11);
        MyAssert.test(map.get(new Key(7)) == 12);
        MyAssert.test(map.get(new Key(8)) == 8);
    }

    /**
     *  @MethodName test3
     *  @Description  测试多种类型的同key覆盖
     *  @Param [map]
     *  @return void
     */
    static void test3(HashMap<Object, Integer> map) {
        map.put(null, 1); // 1
        map.put(new Object(), 2); // 2
        map.put("jack", 3); // 3
        map.put(10, 4); // 4
        map.put(new Object(), 5); // 5
        map.put("jack", 6);
        map.put(10, 7);
        map.put(null, 8);
        map.put(10, null);
        MyAssert.test(map.size() == 5);
        MyAssert.test(map.get(null) == 8);
        MyAssert.test(map.get("jack") == 6);
        MyAssert.test(map.get(10) == null);
        MyAssert.test(map.get(new Object()) == null);
        MyAssert.test(map.containsKey(10));
        MyAssert.test(map.containsKey(null));
        MyAssert.test(map.containsValue(null));
        MyAssert.test(map.containsValue(1) == false);
    }

    /**
     *  @MethodName test4
     *  @Description  测试HashMap的删除
     *  @Param [map]
     *  @return void
     */
    static void test4(HashMap<Object, Integer> map) {
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
        map.remove("jack");
        map.remove("jim");
        for (int i = 1; i <= 10; i++) {
            map.put("test" + i, i);
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            MyAssert.test(map.remove(new Key(i)) == i);
        }
        for (int i = 1; i <= 3; i++) {
            map.put(new Key(i), i + 5);
        }
        MyAssert.test(map.size() == 19);
        MyAssert.test(map.get(new Key(1)) == 6);
        MyAssert.test(map.get(new Key(2)) == 7);
        MyAssert.test(map.get(new Key(3)) == 8);
        MyAssert.test(map.get(new Key(4)) == 4);
        MyAssert.test(map.get(new Key(5)) == null);
        MyAssert.test(map.get(new Key(6)) == null);
        MyAssert.test(map.get(new Key(7)) == null);
        MyAssert.test(map.get(new Key(8)) == 8);
        map.traversal(new Map.Visitor<Object, Integer>() {
            public boolean visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    /**
     *  @MethodName test5
     *  @Description  测试复杂情况下的equals同Key覆盖
     *  @Param [map]
     *  @return void
     */
    static void test5(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        MyAssert.test(map.get(new SubKey1(1)) == 5);
        MyAssert.test(map.get(new SubKey2(1)) == 5);
        MyAssert.test(map.size() == 20);
    }

    public static void main(String[] args) {
//         HashMap功能测试
//		test1();
//		test2(new HashMap<>());
//		test3(new HashMap<>());
//		test4(new HashMap<>());
//		test5(new HashMap<>());

		// 最次HashMap测试
//		test2(new Simple_HashMap<>());
//		test3(new Simple_HashMap<>());
//		test4(new Simple_HashMap<>());
//		test5(new Simple_HashMap<>());

        //  LinkedHashMap测试
		test1();
        test2(new LinkedHashMap<>());
        test3(new LinkedHashMap<>());
        test4(new LinkedHashMap<>());
        test5(new LinkedHashMap<>());


        java.util.HashMap<String, String> map;
        java.util.LinkedHashMap<String, String> map2;
    }
}
