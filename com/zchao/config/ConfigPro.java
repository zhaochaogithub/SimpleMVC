/*     */ package com.zchao.config;
/*     */ 
/*     */ import com.zchao.interceptor.Interceptor;
/*     */ import com.zchao.util.ClassScanUtil;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.Filter;
/*     */ import javax.sql.DataSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfigPro
/*     */ {
/*     */   private String actionPackagePath;
/*  19 */   private String encoding = "UTF-8";
/*     */ 
/*     */   
/*     */   private Filter filter;
/*     */   
/*     */   private Interceptor interceptor;
/*     */   
/*     */   private Map<String, DataSource> dbMap;
/*     */   
/*     */   private DataSource dataSource;
/*     */   
/*     */   private Integer port;
/*     */ 
/*     */   
/*  33 */   public DataSource getDataSource() { return this.dataSource; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public void setDataSource(DataSource dataSource) { this.dataSource = dataSource; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public Integer getPort() { return this.port; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public void setPort(Integer port) { this.port = port; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public static ConfigPro getConfigPro() { return configPro; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public static void setConfigPro(ConfigPro configPro) { ConfigPro.configPro = configPro; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public Map<String, DataSource> getDbMap() { return this.dbMap; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public void setDbMap(Map<String, DataSource> dbMap) { this.dbMap = dbMap; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public DataSource getDefaultDataSource() { return this.dataSource; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setDefaultDataSource(DataSource dataSource) { this.dataSource = dataSource; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public Interceptor getInterceptor() { return this.interceptor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public void setInterceptor(Interceptor interceptor) { this.interceptor = interceptor; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public Filter getFilter() { return this.filter; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public void setFilter(Filter filter) { this.filter = filter; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public String getActionPackagePath() { return this.actionPackagePath; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public void setActionPackagePath(String actionPackagePath) { this.actionPackagePath = actionPackagePath; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public String getEncoding() { return this.encoding; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public void setEncoding(String encoding) { this.encoding = encoding; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public List<Class<?>> getClassList() { return ClassScanUtil.getClasses(this.actionPackagePath); }
/*     */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zchao\config\ConfigPro.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */