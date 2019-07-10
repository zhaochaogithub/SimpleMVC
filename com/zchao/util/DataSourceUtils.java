/*    */ package com.zchao.util;
/*    */ 
/*    */ import com.zchao.config.Config;
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataSourceUtils
/*    */ {
/*    */   public Connection getConnection() throws Exception {
/* 15 */     Config config = Config.getInstance();
/* 16 */     ThreadLocal<Connection> defaultThreadLocal = config.getDefaultThreadLocal();
/* 17 */     Connection connection = (Connection)defaultThreadLocal.get();
/* 18 */     if (connection == null) {
/*    */       
/* 20 */       connection = config.getDefaultDataSource().getConnection();
/* 21 */       defaultThreadLocal.set(connection);
/*    */     } 
/* 23 */     return connection;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void closeConnection() {
/* 31 */     Config config = Config.getInstance();
/* 32 */     ThreadLocal<Connection> defaultThreadLocal = config.getDefaultThreadLocal();
/* 33 */     Connection connection = (Connection)defaultThreadLocal.get();
/* 34 */     if (connection != null) {
/*    */ 
/*    */       
/*    */       try {
/* 38 */         connection.close();
/*    */       }
/* 40 */       catch (SQLException e) {
/*    */         
/* 42 */         throw new RuntimeException("连接关闭失败");
/*    */       } 
/* 44 */       defaultThreadLocal.remove();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zcha\\util\DataSourceUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */