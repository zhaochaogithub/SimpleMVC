/*    */ package com.zchao.servlet;
/*    */ 
/*    */ import com.zchao.config.Config;
/*    */ import com.zchao.interceptor.DefaultInterceptor;
/*    */ import com.zchao.interceptor.Interceptor;
/*    */ import com.zchao.interceptor.Invocation;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BaseServlet
/*    */   extends HttpServlet
/*    */ {
/* 22 */   public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { doPost(req, resp); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 28 */     Config config = Config.getInstance();
/* 29 */     response.setCharacterEncoding(config.getEncoding());
/* 30 */     response.setContentType("application/json");
/*    */     
/* 32 */     Interceptor interceptor = config.getInterceptor();
/*    */     try {
/*    */       DefaultInterceptor defaultInterceptor;
/* 35 */       if (interceptor == null)
/*    */       {
/* 37 */         defaultInterceptor = new DefaultInterceptor();
/* 38 */         defaultInterceptor.intercept(new Invocation(request, response));
/*    */       }
/*    */       else
/*    */       {
/* 42 */         defaultInterceptor.intercept(new Invocation(request, response));
/*    */       }
/*    */     
/* 45 */     } catch (Exception e) {
/*    */       
/* 47 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zchao\servlet\BaseServlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */