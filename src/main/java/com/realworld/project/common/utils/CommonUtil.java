package com.realworld.project.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
public class CommonUtil {
    public static boolean isEmpty(Object obj){
        if(obj==null) return true;
        if((obj instanceof String) && (((String)obj).trim().length() == 0)) {return true;}
        if(obj instanceof Map) {return ((Map<?, ?>)obj).isEmpty();}
        if(obj instanceof List) {return ((List<?>)obj).isEmpty();}
        if(obj instanceof Object[]) {return (((Object[])obj).length == 0);}

        return false;
    }

    public static boolean passwordValidationCheck(String password){
        boolean result = Pattern.matches("/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}$/",password);
        log.info("result : {}", result);
        return result;
    }
}
