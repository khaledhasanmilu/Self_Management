package com.self.management.self_management;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PassWordHash {
   public static String hashPassword(String rawPassword) {
       try {
           MessageDigest md = MessageDigest.getInstance("MD5");

           byte [] bytes = md.digest(rawPassword.getBytes());
           System.out.println(Arrays.toString(bytes));
           BigInteger signNumber = new BigInteger(1,bytes);
           String hexnum = signNumber.toString(16);
           System.out.println(hexnum);
           while (hexnum.length()<32){
               hexnum = "0"+ hexnum;
           }
           return hexnum;
       }catch (NoSuchAlgorithmException e){
           System.out.println("hash problem");
           return null;
       }
   }
}

