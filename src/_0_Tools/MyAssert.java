package _0_Tools;

public class MyAssert {
    //接口测试方法
    public static void test(boolean value){   //传入布尔表达式来检测是否正常
        try {
            if(!value) throw new Exception("测试不通过");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
