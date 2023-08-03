package com.gl.getbundledatahelp;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import java.lang.reflect.Field;

public final class BundleDataHandleUtils {

    public static void getIntentData(Object object, Intent intent){
        if(object == null || intent == null || intent.getExtras() == null){
            return;
        }
        getBundleData(object,intent.getExtras());
    }

    public static void getBundleData(Object object,Bundle bundle){
        if(object == null || bundle == null ){
            return;
        }
       new DataHandle(object, bundle).startHandle();
    }

    public static Intent initParamToIntent(Intent intent, ArrayMap<String,Object> map){
        if(intent == null){
            intent = new Intent();
        }
        if(map != null || map.size() > 0){
            intent.putExtras(initParamToBundle(intent.getExtras(),map));
        }
        return intent;
    }

    public static Bundle initParamToBundle(Bundle bundle,ArrayMap<String,Object> map){
        if(bundle == null){
            bundle = new Bundle();
        }
        try {
            Class superClass = bundle.getClass().getSuperclass();
            Field mapField = superClass.getDeclaredField("mMap");
            mapField.setAccessible(true);
            mapField.set(bundle,map);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return bundle;
    }
}
