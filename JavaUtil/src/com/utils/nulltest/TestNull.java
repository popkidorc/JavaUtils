package com.utils.nulltest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 
 * 测试类，包含3个内部类
 * 
 * @author sunjie at 2016年8月3日
 *
 */
public class TestNull {

    private String string1;

    private Long long1;

    private int int1;

    private Float float1;

    private Boolean boolean1;

    private Character character1;

    private TestNullEnum testNullEnum1;

    private TestNullChlidA testNullChlidA1;

    private List<TestNullChlidB> testNullChlid1;

    private Map<String, TestNullChlidB> testNullChlid2;

    private TestNullChlidB[] testNullChlid3;

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Long getLong1() {
        return long1;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public int getInt1() {
        return int1;
    }

    public void setInt1(int int1) {
        this.int1 = int1;
    }

    public Float getFloat1() {
        return float1;
    }

    public void setFloat1(Float float1) {
        this.float1 = float1;
    }

    public Boolean getBoolean1() {
        return boolean1;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public Character getCharacter1() {
        return character1;
    }

    public void setCharacter1(Character character1) {
        this.character1 = character1;
    }

    public TestNullEnum getTestNullEnum1() {
        return testNullEnum1;
    }

    public void setTestNullEnum1(TestNullEnum testNullEnum1) {
        this.testNullEnum1 = testNullEnum1;
    }

    public TestNullChlidA getTestNullChlidA1() {
        return testNullChlidA1;
    }

    public void setTestNullChlidA1(TestNullChlidA testNullChlidA1) {
        this.testNullChlidA1 = testNullChlidA1;
    }

    public List<TestNullChlidB> getTestNullChlid1() {
        return testNullChlid1;
    }

    public void setTestNullChlid1(List<TestNullChlidB> testNullChlid1) {
        this.testNullChlid1 = testNullChlid1;
    }

    public Map<String, TestNullChlidB> getTestNullChlid2() {
        return testNullChlid2;
    }

    public void setTestNullChlid2(Map<String, TestNullChlidB> testNullChlid2) {
        this.testNullChlid2 = testNullChlid2;
    }

    public TestNullChlidB[] getTestNullChlid3() {
        return testNullChlid3;
    }

    public void setTestNullChlid3(TestNullChlidB[] testNullChlid3) {
        this.testNullChlid3 = testNullChlid3;
    }

    @Override
    public String toString() {
        return "TestNull [string1=" + string1 + ", long1=" + long1 + ", int1=" + int1 + ", float1=" + float1
                + ", boolean1=" + boolean1 + ", character1=" + character1 + ", testNullEnum1=" + testNullEnum1
                + ", testNullChlidA1=" + testNullChlidA1 + ", testNullChlid1=" + testNullChlid1 + ", testNullChlid2="
                + testNullChlid2 + ", testNullChlid3=" + Arrays.toString(testNullChlid3) + "]";
    }

    public class TestNullChlidBaseA {
        private String childBaseAString1;

        private int childBaseALong1;

        private Boolean childABoolean2;

        public String getChildBaseAString1() {
            return childBaseAString1;
        }

        public void setChildBaseAString1(String childBaseAString1) {
            this.childBaseAString1 = childBaseAString1;
        }

        public int getChildBaseALong1() {
            return childBaseALong1;
        }

        public void setChildBaseALong1(int childBaseALong1) {
            this.childBaseALong1 = childBaseALong1;
        }

        public Boolean getChildABoolean2() {
            return childABoolean2;
        }

        public void setChildABoolean2(Boolean childABoolean2) {
            this.childABoolean2 = childABoolean2;
        }

        @Override
        public String toString() {
            return "TestNullChlidBaseA [childBaseAString1=" + childBaseAString1 + ", childBaseALong1="
                    + childBaseALong1 + ", childABoolean2=" + childABoolean2 + "]";
        }

    }

    public class TestNullChlidA extends TestNullChlidBaseA {
        private String childAString1;

        private Long childALong1;

        private long childALong2;

        private boolean childABoolean1;

        private Boolean childABoolean2;

        public String getChildAString1() {
            return childAString1;
        }

        public void setChildAString1(String childAString1) {
            this.childAString1 = childAString1;
        }

        public Long getChildALong1() {
            return childALong1;
        }

        public void setChildALong1(Long childALong1) {
            this.childALong1 = childALong1;
        }

        public long getChildALong2() {
            return childALong2;
        }

        public void setChildALong2(long childALong2) {
            this.childALong2 = childALong2;
        }

        public boolean isChildABoolean1() {
            return childABoolean1;
        }

        public void setChildABoolean1(boolean childABoolean1) {
            this.childABoolean1 = childABoolean1;
        }

        public Boolean getChildABoolean2() {
            return childABoolean2;
        }

        public void setChildABoolean2(Boolean childABoolean2) {
            this.childABoolean2 = childABoolean2;
        }

        @Override
        public String toString() {
            return "TestNullChlidA [childAString1=" + childAString1 + ", childALong1=" + childALong1 + ", childALong2="
                    + childALong2 + ", childABoolean1=" + childABoolean1 + ", childABoolean2=" + childABoolean2
                    + ", superClass=" + super.toString() + "]";
        }
    }

    public class TestNullChlidB {
        private String childBString1;

        private Float childBFloat1;

        private TestNullEnum testNullEnum1;

        public String getChildBString1() {
            return childBString1;
        }

        public void setChildBString1(String childBString1) {
            this.childBString1 = childBString1;
        }

        public Float getChildBFloat1() {
            return childBFloat1;
        }

        public void setChildBFloat1(Float childBFloat1) {
            this.childBFloat1 = childBFloat1;
        }

        public TestNullEnum getTestNullEnum1() {
            return testNullEnum1;
        }

        public void setTestNullEnum1(TestNullEnum testNullEnum1) {
            this.testNullEnum1 = testNullEnum1;
        }

        @Override
        public String toString() {
            return "TestNullChlidB [childBString1=" + childBString1 + ", childBFloat1=" + childBFloat1
                    + ", testNullEnum1=" + testNullEnum1 + "]";
        }

    }

}
