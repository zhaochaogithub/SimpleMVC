/*    */ package com.zchao.interceptor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultInterceptor
/*    */   implements Interceptor
/*    */ {
/*    */   public void intercept(Invocation invocation) {
/*    */     try {
/* 11 */       invocation.invoke();
/*    */     }
/* 13 */     catch (Exception e) {
/*    */       
/* 15 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zchao\interceptor\DefaultInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */