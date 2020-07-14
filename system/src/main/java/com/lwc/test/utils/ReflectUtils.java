package com.lwc.test.utils;


import com.alibaba.druid.util.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类
 */
public class ReflectUtils {
    public static final String MAVEN_SRC_PATH = "src\\main\\java";

    public static final String COMMON_SRC_PATH = "src";

    /**
     * 查找所有非静态的字段包括父类的
     *
     * @param clazz
     *            目标class
     * @return 字段List集合
     */
    public static List<Field> getFields(Class<?> clazz) {
        List<Field> list = new ArrayList<Field>();
        for (Field field : clazz.getDeclaredFields()) {
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            if(!isStatic){
                list.add(field);
            }
        }
        if(null != clazz.getSuperclass()){
            Class<?> superClazz = clazz.getSuperclass();
            list.addAll(getFields(superClazz));
        }
        return list;
    }
    /**
     * 得到指定字段数组的Field
     */
    public static List<Field> getFields(Class<?> clazz,List<String> fields){
        List<Field> list = new ArrayList<Field>();
        for (Field field : clazz.getDeclaredFields()) {
            for(String fieldName:fields){
                if(StringUtils.equals(fieldName, field.getName())){
                    list.add(field);break;
                }
            }
        }
        if(null != clazz.getSuperclass()){
            Class<?> superClazz = clazz.getSuperclass();
            list.addAll(getFields(superClazz,fields));
        }
        return list;
    }
    /**
     * 查找对应的字段对象
     *
     * @param clazz
     *            对应class
     * @param fieldName
     * @return 获得字段对象
     */
    public static Field getField(Class<?> clazz, String fieldName) {

        Field field = null;
        List<Field> list = getFields(clazz);
        for (Field f : list) {
            if (f.getName().equals(fieldName)) {
                field = f;
                break;
            }
        }
        return field;
    }

    /**
     * 查找对应的字段名称
     *
     * @param field
     *            字段对象
     * @return 字段名称
     */
    public static String getName(Field field) {
        return field.getName();
    }

    /**
     * 查找对应的字段值
     *
     * @param fieldName
     *            字段对象
     * @param obj
     *            字段所属对象
     */
    public static Object getValue(String fieldName, Object obj) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor property = getProperty(fieldName, obj.getClass());
        if (null != property.getReadMethod()) {
            return property.getReadMethod().invoke(obj);
        }
        return null;
    }

    /**
     * 获得PropertyDescriptor 对象
     *
     * @param fieldName
     *            字段名称
     * @param clazz
     *            目标class
     * @return
     */
    public static PropertyDescriptor getProperty(String fieldName, Class<?> clazz) throws IntrospectionException {
        return new PropertyDescriptor(fieldName, clazz);
    }

    /**
     * 赋值方法
     *
     * @param fieldName
     *            字段名称
     * @param obj
     *            字段所属对象
     * @param value
     *            值
     */
    public static void setValue(String fieldName, Object obj, Object value) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor property = getProperty(fieldName, obj.getClass());
        if (null != property.getWriteMethod()) {
            property.getWriteMethod().invoke(obj, value);
        }

    }

    /**
     * 将一个对象的字段值 复制到另一个对象相同的字段里面
     *
     * @param sourse
     *            所需对象
     * @param target
     *            目标对象
     * @
     */
    public static void copySameFieldToTarget(Object sourse, Object target) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        if (null == sourse || null == target){
            throw new RuntimeException("复制资源或对象为null");
        }
        List<Field> sourseFields = getFields(sourse.getClass());
        List<Field> targetFields = getFields(target.getClass());
        for (Field s : sourseFields) {
            String sName = getName(s);
            for (Field t : targetFields) {
                String tName = getName(t);
                if (sName.equals(tName) && s.getType().equals(t.getType())) {
                    setValue(tName, target, getValue(sName, sourse));
                    break;
                }
            }
        }
    }

    /**
     * 将一个对象的字段值 复制到另一个对象相同的字段里面但是不复制null值
     *
     * @param sourse
     *            所需对象
     * @param target
     *            目标对象
     * @
     */
    public static void copySameFieldToTargetFilterNull(Object sourse, Object target) throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        if (null == sourse || null == target){
            throw new RuntimeException("复制资源或对象为null");
        }
        List<Field> sourseFields = getFields(sourse.getClass());
        List<Field> targetFields = getFields(target.getClass());
        for (Field s : sourseFields) {
            String sName = getName(s);
            for (Field t : targetFields) {
                String tName = getName(t);
                if (sName.equals(tName) && s.getType().equals(t.getType())) {
                    Object val = getValue(sName, sourse);
                    if (null != val) {
                        setValue(tName, target, val);
                    }
                    break;
                }
            }
        }
    }

    /**
     * 将一个对象的字段值 复制到另一个对象相同的字段里面但是不复制null值
     *
     * @param sourse
     *            所需对象
     * @param target
     *            目标对象
     * @
     */
    public static void copySameFieldToTargetFilter(Object sourse, Object target, String[] filterFields) throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        if (null == sourse || null == target) {
            throw new RuntimeException("复制资源或对象为null");
        }
        List<Field> sourseFields = getFields(sourse.getClass());
        List<Field> targetFields = getFields(target.getClass());
        for (Field s : sourseFields) {
            String sName = getName(s);
            for (Field t : targetFields) {
                String tName = getName(t);
                if (sName.equals(tName) && s.getType().equals(t.getType()) && !isInArray(filterFields, sName)) {
                    Object val = getValue(sName, sourse);
                    setValue(tName, target, val);
                    break;
                }
            }
        }
    }

    public static Boolean isInArray(Object[] objs, Object str){
        List<Object> objList = new ArrayList<Object>();
        for(Object obj : objs){
            objList.add(obj);
        }
        return objList.contains(str);
    }


    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws SecurityException, NoSuchMethodException {
        return clazz.getMethod(methodName, parameterTypes);
    }

    @SuppressWarnings({"unchecked","rawtypes"})
    public static List<Field> getFieldsByAnnotation(Class<?> clazz,Class annotation) {
        List<Field> list = new ArrayList<Field>();
        List<Field> allList = getFields(clazz);
        if(null != allList && allList.size() >0){
            for(Field field : allList){
                if(null != field.getAnnotation(annotation)){
                    list.add(field);
                }
            }
        }
        return list;
    }
    /**
     * 获取一个包下面所有的类
     * @param packagePath 包路径
     * @param srcPath class 根路径
     * @param isAll 是否递归全部检索
     * @return
     * @
     */
    public static List<Class<?>> getPackageClasses(String packagePath, String srcPath  , Boolean isAll) {
        List<Class<?>> result = new ArrayList<Class<?>>();
        File root = new File(System.getProperty("user.dir") + "\\" + srcPath+"\\" + packagePath.replace(".", "\\"));
        if(root.exists()){
            result.addAll(loop(root, packagePath, isAll));
        }
        return result;
    }

    private static List<Class<?>> loop(File folder, String packagePath, Boolean isAll)  {
        List<Class<?>> result = new ArrayList<Class<?>>();
        File[] files = folder.listFiles();
        for (int fileIndex = 0; fileIndex < files.length; fileIndex++) {
            File file = files[fileIndex];
            if (file.isDirectory() && isAll) {
                result.addAll(loop(file, packagePath +"." + file.getName(), true));
            }
            if(file.exists() && !file.isDirectory()){
                try {
                    String fileName = file.getName();
                    String name =  fileName.substring(0, fileName.lastIndexOf("."));
                    Class<?> clazz = Class.forName(packagePath +"." + name);
                    result.add(clazz);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
