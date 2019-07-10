/*     */ package com.zchao.dao;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.zchao.config.Config;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.sql.DataSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Dao
/*     */ {
/*     */   private <T> T exec(PreparedStatement pstmt, int op) throws SQLException {
/*  23 */     ResultSet resultSet = pstmt.executeQuery();
/*  24 */     float f = 0.0F;
/*  25 */     if (resultSet.next()) {
/*     */       
/*  27 */       switch (op) {
/*     */         
/*     */         case 0:
/*  30 */           return (T)Integer.valueOf(resultSet.getInt(1));
/*     */         case 1:
/*  32 */           return (T)Float.valueOf(resultSet.getFloat(1));
/*     */         case 2:
/*  34 */           return (T)Double.valueOf(resultSet.getDouble(1));
/*     */         case 3:
/*  36 */           return (T)Long.valueOf(resultSet.getLong(1));
/*     */         case 4:
/*  38 */           return (T)resultSet.getString(1);
/*     */         case 5:
/*  40 */           return (T)Boolean.valueOf(resultSet.getBoolean(1));
/*     */         case 6:
/*  42 */           return (T)resultSet.getBigDecimal(1);
/*     */       } 
/*  44 */       return (T)Integer.valueOf(resultSet.getInt(1));
/*     */     } 
/*     */     
/*  47 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int findInt(String sql, Object... params) throws SQLException {
/*  52 */     PreparedStatement pstmt = getPreparedStatement(sql, params);
/*  53 */     return ((Integer)exec(pstmt, 0)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public float findFloat(String sql, Object... params) throws SQLException {
/*  58 */     PreparedStatement pstmt = getPreparedStatement(sql, params);
/*  59 */     return ((Float)exec(pstmt, 1)).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public double findDouble(String sql, Object... params) throws SQLException {
/*  64 */     PreparedStatement pstmt = getPreparedStatement(sql, params);
/*  65 */     return ((Double)exec(pstmt, 2)).doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public long findLong(String sql, Object... params) throws SQLException {
/*  70 */     PreparedStatement pstmt = getPreparedStatement(sql, params);
/*  71 */     return ((Long)exec(pstmt, 3)).longValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public String findStr(String sql, Object... params) throws SQLException {
/*  76 */     PreparedStatement pstmt = getPreparedStatement(sql, params);
/*  77 */     return (String)exec(pstmt, 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean findBoolean(String sql, Object... params) throws SQLException {
/*  82 */     PreparedStatement pstmt = getPreparedStatement(sql, params);
/*  83 */     return ((Boolean)exec(pstmt, 5)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public BigDecimal findBigDecimal(String sql, Object... params) throws SQLException {
/*  88 */     PreparedStatement pstmt = getPreparedStatement(sql, params);
/*  89 */     return (BigDecimal)exec(pstmt, 6);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T find(String sql, Class<?> clazz, Object... params) throws SQLException {
/*  94 */     PreparedStatement pstmt = getPreparedStatement(sql, params);
/*  95 */     ResultSet resultSet = pstmt.executeQuery();
/*  96 */     List<Map<String, Object>> list = getResult(resultSet);
/*  97 */     if (list.size() == 0)
/*     */     {
/*  99 */       return null;
/*     */     }
/* 101 */     if (list.size() > 1)
/*     */     {
/* 103 */       throw new SQLException("More than one record was found!");
/*     */     }
/* 105 */     return (T)JSONObject.parseObject(JSONObject.toJSONString(list.get(0)), clazz);
/*     */   }
/*     */ 
/*     */   
/*     */   public PreparedStatement getPreparedStatement(String sql, Object... params) throws SQLException {
/* 110 */     Config config = Config.getInstance();
/* 111 */     ThreadLocal<String> dbNameThreadLocal = config.getDbNameThreadLocal();
/* 112 */     String dbName_ThreadLocal = (String)dbNameThreadLocal.get();
/* 113 */     Connection connection = null;
/* 114 */     if (dbName_ThreadLocal == null || "".equals(dbName_ThreadLocal)) {
/*     */       
/* 116 */       connection = config.getDefaultDataSource().getConnection();
/*     */     }
/*     */     else {
/*     */       
/* 120 */       connection = ((DataSource)config.getDbMap().get(dbName_ThreadLocal)).getConnection();
/* 121 */       dbNameThreadLocal.remove();
/*     */     } 
/* 123 */     PreparedStatement pstmt = connection.prepareStatement(sql);
/* 124 */     for (int i = 1; i <= params.length; i++)
/*     */     {
/* 126 */       pstmt.setObject(i, params[0]);
/*     */     }
/* 128 */     return pstmt;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Map<String, Object>> getResult(ResultSet resultSet) throws SQLException {
/* 133 */     ResultSetMetaData metaData = resultSet.getMetaData();
/* 134 */     int columnCount = metaData.getColumnCount();
/* 135 */     List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(Collections.emptyList());
/* 136 */     Map<String, Object> map = null;
/* 137 */     while (resultSet.next()) {
/*     */       
/* 139 */       map = new HashMap<String, Object>(Collections.emptyMap());
/* 140 */       for (int i = 1; i <= columnCount; i++)
/*     */       {
/* 142 */         map.put(metaData.getColumnName(i), resultSet.getObject(i));
/*     */       }
/* 144 */       list.add(map);
/*     */     } 
/* 146 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dao use(String dbName) throws Exception {
/* 151 */     Config config = Config.getInstance();
/* 152 */     if (dbName != null && !"".equals(dbName)) {
/*     */       
/* 154 */       ThreadLocal<String> dbNameThreadLocal = config.getDbNameThreadLocal();
/* 155 */       String dbName_ThreadLocal = (String)dbNameThreadLocal.get();
/* 156 */       if (dbName_ThreadLocal == null || "".equals(dbName_ThreadLocal))
/*     */       {
/* 158 */         dbNameThreadLocal.set(dbName);
/*     */       }
/*     */     } 
/* 161 */     return (Dao)Dao.class.newInstance();
/*     */   }
/*     */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zchao\dao\Dao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */