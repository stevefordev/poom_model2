package com.coddington.poom.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 *  
 * @author Administrator
 *
 */
public class FieldUtil {

  /**
   * Class 객체를 인자로 받고 각 멤머필드의 변수명과 값을 hashMap 으로 저장하여 리턴
   * 
   * @param cls
   * @return
   */
  public static Map<String, Object> getAllFields(Object obj) {

    //이름과 값으로 된 데이터를 모아 리턴을 주기 위한 Map 변수 선언
    Map<String, Object> result = new HashMap<String, Object>();

    try {

      for (Field field : obj.getClass().getDeclaredFields()) {
        field.setAccessible(true); // You might want to set modifier to public first.
        
        // final 로 지정한 멤버필드는 skip
        if ((field.getModifiers() & Modifier.FINAL) == Modifier.FINAL) {
          continue;
        }

        Object value = field.get(obj);

        if (value != null) {
          result.put(field.getName(), value);
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }
}
