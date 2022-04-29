package com.uway.sports.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder {
  static Logger log = LoggerFactory.getLogger(MD5Encoder.class);
  private static String byte2hex(byte[] b) {
    StringBuffer hs = new StringBuffer();
    String stmp = "";
    for (int n = 0; n < b.length; n++) {
      stmp = Integer.toHexString(b[n] & 0xFF);
      if (stmp.length() == 1)
        hs = hs.append("0").append(stmp);
      else {
        hs = hs.append(stmp);
      }
    }
    return hs.toString().toUpperCase();
  }

  public static String encode(String input) {
    try {
      MessageDigest alga = MessageDigest.getInstance("MD5");
      alga.update(input.getBytes());
      return byte2hex(alga.digest());
    } catch (NoSuchAlgorithmException e) {
    	//throw new SystemException("0000", e);
    }
	return input;
  }
  
  public static void main(String[] args) {
	System.out.println(encode("123456"));
}
}