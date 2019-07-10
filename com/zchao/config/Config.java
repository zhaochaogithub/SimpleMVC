/*     */ package com.zchao.config;
/*     */ 
/*     */ import com.zchao.interceptor.Interceptor;
/*     */ import java.sql.Connection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.Filter;
/*     */ import javax.sql.DataSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Config
/*     */ {
/*  16 */   private ConfigPro configPro = ConfigPro.getConfigPro();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   private final ThreadLocal<Connection> defaultThreadLocal = new ThreadLocal();
/*  29 */   private final ThreadLocal<String> dbNameThreadLocal = new ThreadLocal();
/*  30 */   private final String encoding = this.configPro.getEncoding();
/*  31 */   private final Filter filter = this.configPro.getFilter();
/*  32 */   private final List<Class<?>> classList = this.configPro.getClassList();
/*  33 */   private final Interceptor interceptor = this.configPro.getInterceptor();
/*  34 */   private final Map<String, DataSource> dbMap = this.configPro.getDbMap();
/*  35 */   private final DataSource dataSource = this.configPro.getDefaultDataSource();
/*  36 */   private final Integer port = this.configPro.getPort();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public List<Class<?>> getClassList() { return this.classList; }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Config getInstance() {
/*  46 */     if (config == null)
/*     */     {
/*  48 */       synchronized (Config.class) {
/*     */         
/*  50 */         if (config == null)
/*     */         {
/*  52 */           config = new Config();
/*     */         }
/*     */       } 
/*     */     }
/*  56 */     return config;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  61 */   public Integer getPort() { return this.port; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public ThreadLocal<String> getDbNameThreadLocal() { return this.dbNameThreadLocal; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public ThreadLocal<Connection> getDefaultThreadLocal() { return this.defaultThreadLocal; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public Map<String, DataSource> getDbMap() { return this.dbMap; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public DataSource getDefaultDataSource() { return this.dataSource; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public Filter getFilter() { return this.filter; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public String getEncoding() { return this.encoding; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public Interceptor getInterceptor() { return this.interceptor; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConfigPro(ConfigPro configPro) {
/* 101 */     if (configPro == null)
/*     */     {
/* 103 */       this.configPro = configPro;
/*     */     }
/*     */   }
/*     */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zchao\config\Config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */