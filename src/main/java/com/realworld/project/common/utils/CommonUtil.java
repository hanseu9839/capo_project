package com.realworld.project.common.utils;

import com.realworld.project.common.code.NickName;
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
        boolean result = Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$",password);
        log.info("result : {}", result);
        return result;
    }

    public static boolean userIdValidationCheck(String userId){
        boolean result = Pattern.matches("^[a-z0-9]{6,12}$", userId);
        return result;
    }

    public static String createNickname(){
        NickName nickName = new NickName();
        String []determiners=nickName.getDeterminers();
        int length = determiners.length;
        log.info("determiners length : {} ", length);
        String determiner = determiners[(int)(Math.random()*length)];
        String []nicknames = nickName.getName();
        String name = nicknames[(int)(Math.random()*nicknames.length)];
        String nickname = determiner +" "+ name;
        log.info(nickname);
        return nickname;
    }
}
