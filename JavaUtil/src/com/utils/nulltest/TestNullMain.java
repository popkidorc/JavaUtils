package com.utils.nulltest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.utils.nulltest.TestNull.TestNullChlidB;

public class TestNullMain {

    /**
     *
     * 主方法
     *
     * @author sunjie at 2016年7月7日
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("01、testNull对象为null：");
        TestNull testNull = null;
        System.out.println("====源对象：" + testNull);
        TestNull notNullObject = ObjectUtils.getNotNullObject(testNull, TestNull.class);
        System.out.println("====处理后：" + notNullObject);

        System.out.println("");

        System.out.println("02、testNull对象内所有属性为null：");
        testNull = new TestNull();
        System.out.println("====源对象：" + testNull);
        notNullObject = ObjectUtils.getNotNullObject(testNull, TestNull.class);
        System.out.println("====处理后：" + notNullObject);

        System.out.println("");

        System.out.println("03、testNull对象内List、Map中元素为null：");
        testNull = new TestNull();
        List<TestNullChlidB> testNullChlid1 = new ArrayList<TestNull.TestNullChlidB>();
        testNullChlid1.add(null);
        testNull.setTestNullChlid1(testNullChlid1);
        Map<String, TestNullChlidB> testNullChlid2 = new HashMap<String, TestNull.TestNullChlidB>();
        testNullChlid2.put("key1", null);
        testNull.setTestNullChlid2(testNullChlid2);
        System.out.println("====源对象：" + testNull);
        notNullObject = ObjectUtils.getNotNullObject(testNull, TestNull.class);
        System.out.println("====处理后：" + notNullObject);

        System.out.println("");

        System.out.println("04、testNull对象内Array中元素为null：");
        testNull = new TestNull();
        TestNullChlidB[] testNullChlidB3 = new TestNullChlidB[1];
        testNullChlidB3[0] = null;
        testNull.setTestNullChlid3(testNullChlidB3);
        System.out.println("====源对象：" + testNull);
        notNullObject = ObjectUtils.getNotNullObject(testNull, TestNull.class);
        System.out.println("====处理后：" + notNullObject);

        System.out.println("");

        System.out.println("05、List<testNull>对象为null：");
        List<TestNull> testNulls = null;
        System.out.println("====源对象：" + testNulls);
        List<TestNull> notNullObjects = ObjectUtils.getNotNullObject(testNulls, List.class, TestNull.class);
        System.out.println("====处理后：" + notNullObjects);

    }
}
