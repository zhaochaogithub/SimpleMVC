/*    */ package com.zchao.proxy;
/*    */ 
/*    */ import com.zchao.util.DataSourceUtils;
/*    */ import java.lang.annotation.Annotation;
/*    */ import java.lang.reflect.Method;
/*    */ import java.sql.Connection;
/*    */ import net.sf.cglib.proxy.Enhancer;
/*    */ import net.sf.cglib.proxy.MethodInterceptor;
/*    */ import net.sf.cglib.proxy.MethodProxy;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProxyService
/*    */   implements MethodInterceptor
/*    */ {
/*    */   public static <T> T getProxy(Class clazz) {
/* 20 */     Enhancer enhancer = new Enhancer();
/* 21 */     enhancer.setSuperclass(clazz);
/*    */     
/* 23 */     ProxyService proxyService = new ProxyService();
/* 24 */     enhancer.setCallback(proxyService);
/*    */     
/* 26 */     return (T)enhancer.create();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object intercept(Object target, Method method, Object[] params, MethodProxy proxy) throws Throwable {
/* 32 */     DataSourceUtils dataSourceUtils = new DataSourceUtils();
/* 33 */     Object object = null;
/* 34 */     Connection connection = dataSourceUtils.getConnection();
/*    */     
/* 36 */     Annotation annotation = method.getAnnotation(com.zchao.annotation.Tx.class);
/* 37 */     if (annotation != null) {
/*    */ 
/*    */       
/* 40 */       connection.setAutoCommit(false);
/*    */ 
/*    */       
/*    */       try {
/* 44 */         object = proxy.invokeSuper(target, params);
/* 45 */         connection.commit();
/*    */       }
/* 47 */       catch (Exception e) {
/*    */         
/* 49 */         connection.rollback();
/* 50 */         dataSourceUtils.closeConnection();
/* 51 */         throw new RuntimeException(e.getMessage());
/*    */       } 
/*    */     } else {
/*    */ 
/*    */       
/*    */       try {
/*    */         
/* 58 */         object = proxy.invokeSuper(target, params);
/*    */       }
/* 60 */       catch (Exception e) {
/*    */         
/* 62 */         dataSourceUtils.closeConnection();
/* 63 */         throw new RuntimeException(e.getMessage());
/*    */       } 
/*    */     } 
/* 66 */     dataSourceUtils.closeConnection();
/* 67 */     return object;
/*    */   }
/*    */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zchao\proxy\ProxyService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */