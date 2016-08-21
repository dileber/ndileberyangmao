package com.zhonghua.shidawei.nhaoyang.app;

import com.drcosu.ndileber.tools.HJson;
import com.drcosu.ndileber.tools.HPref;
import com.zhonghua.shidawei.nhaoyang.data.model.UserModel;

/**
 * Created by shidawei on 16/8/7.
 */
public final class AppHper {

    public static void putAppUser(UserModel userModel){
        HPref.getInstance().put(Configer.PERF_APP,Configer.PERF_USER, HJson.toJson(userModel));
    }

    public static UserModel getAppUser(){
        //String o = (String)PerfManager.getInstance().get(Configer.PERF_APP,Configer.PERF_USER, "");
        String user = HPref.getInstance().get(Configer.PERF_APP,Configer.PERF_USER,null,String.class);
        if(user!=null){
            return HJson.fromJson(user,UserModel.class);
        }else{
            return null;
        }
    }

    public static boolean clearAppUser(){
        return HPref.getInstance().remove(Configer.PERF_APP, Configer.PERF_USER);
    }

}
