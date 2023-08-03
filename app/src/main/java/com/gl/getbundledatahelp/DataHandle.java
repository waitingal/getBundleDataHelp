package com.gl.getbundledatahelp;

import android.os.Bundle;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

class DataHandle {
    private Object object;
    private Bundle bundle;

    public DataHandle(Object object,Bundle bundle) {
        this.object = object;
        this.bundle = bundle;
    }

    public void startHandle(){
        if(object == null || bundle == null){
            return;
        }
        List<Attribute> attributeList = findAttribute();
        setAttribute(attributeList);
    }

    private List<Attribute> findAttribute(){
        return findClass(object);
    }

    /**
     * 遍历属性集合 设置值
     * @param attributeList
     */
    private void setAttribute(List<Attribute> attributeList){
        if(attributeList.isEmpty() || bundle == null){
            return;
        }
        for (Attribute attribute:attributeList){
            attribute.setFieldValue(object,bundle);
        }
    }

    /**
     * 循环获取class/父类/超类中有ParamData注解的属性
     * @param object
     * @return
     */
    private List<Attribute> findClass(Object object){
        List<Attribute> attributeList = new ArrayList<>();
        Class mClass = object.getClass();
        while (mClass != null && !isSystemClass(mClass)){
           // LogUtil.e(LogTag,"findClass--"+mClass.getName());
            List<Attribute> classAttribute = findFields(mClass);
            if(classAttribute != null){
                attributeList.addAll(classAttribute);
            }
            mClass =  mClass.getSuperclass();
        }
        return attributeList;
    }

    /**
     * 获取某个class中有ParamData注解的属性
     * @param c
     * @return
     */
    private List<Attribute> findFields(Class<?> c){
        Field[] fields = c.getDeclaredFields();
        if(fields == null){
            return null;
        }
        List<Attribute> attributeList = new ArrayList<>();
        for (Field field:fields){
            ParamData annotate = field.getAnnotation(ParamData.class);
            if(annotate == null){
                continue;
            }
            if(Modifier.isFinal(field.getModifiers())){
                continue;
            }
            attributeList.add(new Attribute(c,field,annotate));
        }
        return attributeList;
    }

    /**
     * 判断某个class 是否在 android / java  包路径下
     * 返回true 视为系统类，不参与属性获取与赋值
     * @param mClass
     * @return
     */
    private boolean isSystemClass(Class mClass){
        return mClass.getName().startsWith("android") || mClass.getName().startsWith("java");
    }
}
