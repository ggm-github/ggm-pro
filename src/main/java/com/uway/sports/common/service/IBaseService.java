package com.uway.sports.common.service;

import javax.servlet.http.HttpServletRequest;

public interface IBaseService {
   public HttpServletRequest getRequest();
   public static final String HASH_ALGORITHM = "SHA-1";
   public static final int HASH_INTERATIONS = 1024;
   public static final int SALT_SIZE = 8;


}
