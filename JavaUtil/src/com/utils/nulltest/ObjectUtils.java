package com.utils.nulltest;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * 对象处理工具类 TODO 该类可以改为工厂，对于各种特殊类型可以方便扩展
 * 
 * @author sunjie at 2016年7月28日
 *
 */
public class ObjectUtils {

    private static final String T = null;

    /**
     * 
     * 将任意对象及对象内属性转为非null对象。深度转换，自动创建对象（对于多个构造方法的类，使用第一个能够成功的构造方法进行实例化）。
     *
     * @author sunjie at 2016年7月28日
     *
     * @param object
     * @param clazz
     *            对象的类，无范型
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static <T> T getNotNullObject(T object, Class<T> clazz) {
        return getNotNullObject(object, clazz, new ArrayList<Class>());
    }

    /**
     * 
     * 将任意对象及对象内属性转为非null对象。深度转换，自动创建对象（对于多个构造方法的类，使用第一个能够成功的构造方法进行实例化）。
     *
     * @author sunjie at 2016年7月28日
     *
     * @param object
     * @param clazz
     *            对象的类
     * @param _actualTypeClazz
     *            泛型类，单范型，例如List
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static <T> T getNotNullObject(T object, Class<T> clazz, Class _actualTypeClazz) {
        List<Class> _actualTypeClazzes = new ArrayList<Class>();
        if (null != _actualTypeClazz) {
            _actualTypeClazzes.add(_actualTypeClazz);
        }
        return getNotNullObject(object, clazz, _actualTypeClazzes);
    }

    /**
     * 
     * 将任意对象及对象内属性转为非null对象。深度转换，自动创建对象（对于多个构造方法的类，使用第一个能够成功的构造方法进行实例化）。
     *
     * @author sunjie at 2016年7月28日
     *
     * @param object
     * @param clazz
     *            对象的类
     * @param _actualTypeClazz
     *            泛型类，多范型，例如Map
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T getNotNullObject(T object, Class<T> clazz, List<Class> _actualTypeClazzes) {
        try {
            if (null == clazz) {
                throw new Exception("clazz is NULL");
            }
            if (null == object) {
                return ObjectUtils.getNotNullObject((T) ObjectUtils.getNewInstance(clazz, _actualTypeClazzes), clazz,
                        _actualTypeClazzes);
            }
            if (clazz.isPrimitive()) {
                return object;
            }
            if (clazz.isEnum()) {
                return object;
            }
            if (clazz.isInterface()) {
                return ObjectUtils.getInterfaceNotNullObject(object, clazz, _actualTypeClazzes);
            }
            if (clazz.isArray()) {
                return ObjectUtils.getArrayNotNullObject(object, clazz, _actualTypeClazzes);
            }
            if (!clazz.isAnnotation() && !clazz.isInterface() && !clazz.isArray() && !clazz.equals(object.getClass())) {
                throw new Exception("object.getClass() and clazz is NOT EQUAL, clazz:" + clazz + ", obejctClass:"
                        + object.getClass());
            }
            // 获取父类字段
            Field[] declaredFields = getSuperFields(clazz);
            Field.setAccessible(declaredFields, true);
            for (Field field : declaredFields) {
                // final放过
                if (Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                Object fieldObject = field.get(object);
                Class fieldClass = field.getType();
                List<Class> fieldActualTypeClazzes = null;
                // 判断泛型
                if (field.getGenericType() instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                    fieldActualTypeClazzes = new ArrayList<Class>();
                    for (Type type : parameterizedType.getActualTypeArguments()) {
                        fieldActualTypeClazzes.add((Class) type);
                    }
                }
                if (null == fieldObject) {
                    fieldObject = ObjectUtils.getNewInstance(fieldClass, fieldActualTypeClazzes);
                }
                field.set(object, ObjectUtils.getNotNullObject(fieldObject, fieldClass, fieldActualTypeClazzes));
            }
        } catch (Exception e) {
            System.err.println("NotNullObject is ERROR , object:" + object + ", class:" + clazz
                    + ", _actualTypeClazzes:" + _actualTypeClazzes + ", e:" + e.getMessage());
        }
        return object;
    }

    @SuppressWarnings("rawtypes")
    private static Field[] getSuperFields(Class clazz) {
        if (null == clazz) {
            return new Field[] {};
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        if (null == clazz.getSuperclass()) {
            return new Field[] {};
        }
        Field[] superFields = getSuperFields(clazz.getSuperclass());
        return (Field[]) ArrayUtils.addAll(declaredFields, superFields);
    }

    /**
     * 
     * 获取interface类型的非空对象，TODO 需要自行扩展
     *
     * @author sunjie at 2016年7月28日
     *
     * @param clazz
     * @param _actualType
     *            泛型类型
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static <T> T getInterfaceNotNullObject(T object, Class<T> clazz, List<Class> _actualTypeClazzes)
            throws Exception {
        if (List.class.equals(clazz)) {
            List list = (List) object;
            Class actualTypeClazz = Object.class;
            for (int i = 0; i < list.size(); i++) {
                Object entry = list.get(i);
                if (!ObjectUtils.isEmpty(_actualTypeClazzes)) {
                    actualTypeClazz = _actualTypeClazzes.get(0);
                }
                if (null == entry) {
                    entry = ObjectUtils.getNewInstance(actualTypeClazz, null);
                }
                entry = ObjectUtils.getNotNullObject(entry, actualTypeClazz, new ArrayList<Class>());
                list.remove(i);
                list.add(i, entry);
            }
            return object;
        }
        if (Map.class.equals(clazz)) {
            Map map = (Map) object;
            Class actualTypeClazz = Object.class;
            for (Object key : map.keySet()) {
                if (!ObjectUtils.isEmpty(_actualTypeClazzes) && _actualTypeClazzes.size() > 1) {
                    actualTypeClazz = _actualTypeClazzes.get(1);// 取value的class
                }
                Object entry = map.get(key);
                if (null == entry) {
                    entry = ObjectUtils.getNewInstance(actualTypeClazz, null);
                }
                entry = ObjectUtils.getNotNullObject(entry, actualTypeClazz, new ArrayList<Class>());
                map.put(key, entry);
            }
            return object;
        }
        throw new Exception("not support this interface, clazz:" + clazz);
    }

    /**
     * 
     * 获取数组的非空对象，TODO 待实现
     *
     * @author sunjie at 2016年7月28日
     *
     * @param arrayObject
     * @param clazz
     * @param _actualTypeClazz
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static <T> T getArrayNotNullObject(T arrayObject, Class<T> clazz, List<Class> _actualTypeClazzes)
            throws Exception {
        Object[] objectArray = (Object[]) arrayObject;
        Class arrayClass = clazz.getComponentType();
        if (objectArray.length <= 0) {
            return (T) Array.newInstance(arrayClass, objectArray.length);
        }
        for (int i = 0; i < objectArray.length; i++) {
            objectArray[i] = ObjectUtils.getNotNullObject(arrayClass.cast(objectArray[i]), arrayClass);
        }
        return (T) objectArray;
    }

    /**
     * 
     * 获取基本类型的默认值
     *
     * @author sunjie at 2016年7月28日
     *
     * @param clazz
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static Object getPrimitiveDefault(Class clazz) {
        if (Boolean.TYPE.equals(clazz)) {
            return false;
        } else if (Character.TYPE.equals(clazz)) {
            return '0';
        }
        return 0;
    }

    /**
     * 
     * 获取interface类型的默认值，TODO 需要自行扩展
     *
     * @author sunjie at 2016年7月28日
     *
     * @param clazz
     * @param _actualType
     *            泛型类型
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    private static Object getInterfaceDefault(Class clazz, List<Class> _actualTypeClazzes) throws Exception {
        if (List.class.equals(clazz)) {
            return new ArrayList();
        }
        if (Map.class.equals(clazz)) {
            return new HashMap();
        }
        throw new Exception("not support this interface, clazz:" + clazz);
    }

    /**
     * 
     * 获取数组的默认值，TODO 待实现
     *
     * @author sunjie at 2016年7月28日
     *
     * @param clazz
     * @param _actualType
     *            泛型类型
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    private static Object getArrayDefault(Class clazz, List<Class> _actualTypeClazzes) throws Exception {
        return new ArrayList().toArray();
    }

    /**
     * 
     * 获取interface类型的默认值，需要自行扩展
     *
     * @author sunjie at 2016年7月28日
     *
     * @param clazz
     * @param _actualType
     *            泛型类型
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Object getEnumDefault(Class clazz) throws Exception {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (clazz.equals(field.getType())) {
                return Enum.valueOf(clazz, field.getName());
            }
        }
        throw new Exception("the enum is NOT VALUE, enum:" + clazz);
    }

    /**
     * 
     * 构造任意对象的空对象，深层构造
     *
     * @author sunjie at 2016年7月28日
     *
     * @param clazz
     * @param _actualTypeClazz
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Object getNewInstance(Class clazz, List<Class> _actualTypeClazzes) throws Exception {
        // 基本类型
        if (clazz.isPrimitive()) {
            return ObjectUtils.getPrimitiveDefault(clazz);
        }
        // enum
        if (clazz.isEnum()) {
            return ObjectUtils.getEnumDefault(clazz);
        }
        // interface
        if (clazz.isInterface()) {
            return ObjectUtils.getInterfaceDefault(clazz, _actualTypeClazzes);
        }
        // 数组
        if (clazz.isArray()) {
            return ObjectUtils.getArrayDefault(clazz, _actualTypeClazzes);
        }
        Object object = new Object();
        // 首先获取无参构造器
        try {
            Constructor constructor = clazz.getDeclaredConstructor();
            if (null != constructor) {
                object = constructor.newInstance();
            }
        } catch (NoSuchMethodException e1) {
            // 获取InnerClass构造器
            try {
                Constructor constructor = clazz.getDeclaredConstructor(clazz);
                if (null != constructor) {
                    object = constructor.newInstance(object);
                }
            } catch (NoSuchMethodException e2) {
                // 获取其他类型,取第一个
                Constructor[] constructors = clazz.getDeclaredConstructors();
                for (Constructor constructor : constructors) {
                    try {
                        List<Object> paramObjects = new ArrayList<Object>();
                        for (Class parameterClass : constructor.getParameterTypes()) {
                            paramObjects.add(ObjectUtils.getNewInstance(parameterClass, null));
                        }
                        // 基本类型
                        if (!clazz.isPrimitive()) {
                            object = constructor.newInstance(paramObjects.toArray());
                            break;
                        }
                    } catch (Exception e3) {
                        continue;// 构造失败，则用下一个构造方法继续
                    }
                }
            }
        }
        return object;
    }

    private static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

}
