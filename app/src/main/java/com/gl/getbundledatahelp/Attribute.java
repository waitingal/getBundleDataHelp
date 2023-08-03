package com.gl.getbundledatahelp;

import android.os.Bundle;
import android.text.TextUtils;
import java.lang.reflect.Field;

class Attribute {
    private Class<?> c;
    private Field field;
    private ParamData annotate;

    public Attribute(Class<?> c, Field field, ParamData annotate) {
        this.c = c;
        this.field = field;
        this.annotate = annotate;
    }

    private void setFieldAccessible(){
        field.setAccessible(true);
    }

    private String getFieldName(){
        return  !TextUtils.isEmpty(annotate.paramName()) ? annotate.paramName() : field.getName();
    }

    public void setFieldValue(Object obj, Bundle bundle){
        if(obj == null || bundle == null){
            return;
        }
        String fieldName = getFieldName();
        if(!bundle.containsKey(fieldName)){
            return;
        }
        setFieldAccessible();
        try {
            field.set(obj,bundle.get(fieldName));
        }catch (IllegalArgumentException e){
              System.out.println(e.toString());
        }catch (IllegalAccessException e){
            System.out.println(e.toString());
        }
    }
}
