/*    */ package com.zchao.util;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ActionEntity
/*    */ {
/*    */   private Class<?> action;
/*    */   private Method method;
/*    */   
/* 13 */   public Class<?> getAction() { return this.action; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public void setAction(Class<?> action) { this.action = action; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public Method getMethod() { return this.method; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void setMethod(Method method) { this.method = method; }
/*    */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zcha\\util\ActionEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */