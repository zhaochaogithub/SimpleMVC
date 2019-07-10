package com.zchao.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
  String value();
}


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zchao\annotation\Action.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */