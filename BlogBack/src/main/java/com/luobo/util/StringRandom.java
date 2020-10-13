package com.luobo.util;

import java.util.Random;

/**
 * @ClassName StringRandom
 * @Description 生成指定长度的随机数字字符串
 * @Author davidmorgan
 * @Date 2020/10/10 14:34
 * @Version 1.0
 **/
public class StringRandom {
    public static String getStringRandom(int length){
        StringBuffer val = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i < length; i++){
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val.append(String.valueOf(random.nextInt(10)));
            }
        }

        return val.toString();
    }

//    public static void main(String[] args) {
//        StringRandom s = new StringRandom();
//        String s1 = s.getStringRandom(6);
//        System.out.println("ddddd------>"+s1);
//    }
}
