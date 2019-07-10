/*    */ package com.zchao.interceptor;
/*    */ 
/*    */ import com.zchao.config.Config;
/*    */ import com.zchao.util.ActionEntity;
/*    */ import com.zchao.util.ActionUtils;
/*    */ import java.io.PrintWriter;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Invocation
/*    */ {
/*    */   private HttpServletRequest request;
/*    */   private HttpServletResponse response;
/*    */   private String returnData;
/*    */   
/*    */   public Invocation() {}
/*    */   
/*    */   public Invocation(HttpServletRequest request, HttpServletResponse response) {
/* 26 */     this.request = request;
/* 27 */     this.response = response;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void invoke() {
/* 36 */     Object obj = null;
/*    */     
/* 38 */     Config config = Config.getInstance();
/* 39 */     ActionEntity actionEntity = ActionUtils.getActionEntity(this.request, this.response, config.getClassList());
/* 40 */     Method method = actionEntity.getMethod();
/* 41 */     Class<?> cls = actionEntity.getAction();
/* 42 */     int paramCount = method.getParameterCount();
/* 43 */     if (paramCount <= 0) {
/*    */       
/* 45 */       obj = method.invoke(cls.newInstance(), new Object[0]);
/*    */     }
/* 47 */     else if (paramCount == 1) {
/*    */       
/* 49 */       obj = method.invoke(cls.newInstance(), new Object[] { this.request });
/*    */     }
/* 51 */     else if (paramCount == 2) {
/*    */       
/* 53 */       obj = method.invoke(cls.newInstance(), new Object[] { this.request, this.response });
/*    */     }
/*    */     else {
/*    */       
/* 57 */       throw new RuntimeException("Execute Method Exception!");
/*    */     } 
/* 59 */     setReturnData((String)obj);
/* 60 */     PrintWriter printWriter = this.response.getWriter();
/* 61 */     printWriter.print(obj);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public HttpServletRequest getRequest() { return this.request; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 77 */   public HttpServletResponse getResponse() { return this.response; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 85 */   public String getReturnData() { return this.returnData; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 90 */   public void setReturnData(String returnData) { this.returnData = returnData; }
/*    */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zchao\interceptor\Invocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */