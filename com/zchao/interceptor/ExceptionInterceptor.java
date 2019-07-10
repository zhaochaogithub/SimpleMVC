/*    */ package com.zchao.interceptor;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExceptionInterceptor
/*    */   implements Interceptor
/*    */ {
/*    */   public void intercept(Invocation invocation) {
/* 14 */     HttpServletRequest request = invocation.getRequest();
/* 15 */     String url = request.getRequestURL().toString();
/*    */     
/*    */     try {
/* 18 */       invocation.invoke();
/* 19 */       String data = invocation.getReturnData();
/* 20 */       System.out.println(String.valueOf(url) + data);
/*    */     
/*    */     }
/* 23 */     catch (Exception e) {
/*    */       
/* 25 */       StringWriter sw = new StringWriter();
/* 26 */       e.printStackTrace(new PrintWriter(sw, true));
/*    */ 
/*    */       
/*    */       try {
/* 30 */         PrintWriter printWriter = invocation.getResponse().getWriter();
/* 31 */         printWriter.print("系统异常" + sw.toString());
/*    */       }
/* 33 */       catch (IOException iOException) {}
/*    */     } 
/*    */   }
/*    */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zchao\interceptor\ExceptionInterceptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */