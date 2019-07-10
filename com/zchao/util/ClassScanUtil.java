/*     */ package com.zchao.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.IOException;
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClassScanUtil
/*     */ {
/*     */   public static List<Class<?>> scan(String path, Class cls) {
/*  26 */     List<Class<?>> list = getClasses(path);
/*  27 */     if (list == null || list.size() <= 0)
/*     */     {
/*  29 */       list = new ArrayList<Class<?>>(Collections.emptyList());
/*     */     }
/*  31 */     Iterator<Class<?>> it = list.iterator();
/*  32 */     while (it.hasNext()) {
/*     */       
/*  34 */       Annotation annotation = ((Class)it.next()).getAnnotation(cls);
/*  35 */       if (annotation == null)
/*     */       {
/*  37 */         it.remove();
/*     */       }
/*     */     } 
/*  40 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Class<?>> getClasses(String pack) {
/*  46 */     List<Class<?>> classes = new ArrayList<Class<?>>();
/*     */     
/*  48 */     boolean recursive = true;
/*     */     
/*  50 */     String packageName = pack;
/*  51 */     String packageDirName = packageName.replace('.', '/');
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  56 */       Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
/*     */       
/*  58 */       while (dirs.hasMoreElements()) {
/*     */ 
/*     */         
/*  61 */         URL url = (URL)dirs.nextElement();
/*     */         
/*  63 */         String protocol = url.getProtocol();
/*     */         
/*  65 */         if ("file".equals(protocol))
/*     */         {
/*     */           
/*  68 */           String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
/*     */           
/*  70 */           findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
/*     */         }
/*     */       
/*     */       } 
/*  74 */     } catch (IOException e) {
/*     */       
/*  76 */       e.printStackTrace();
/*     */     } 
/*  78 */     return classes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes) {
/*  84 */     File dir = new File(packagePath);
/*     */     
/*  86 */     if (!dir.exists() || !dir.isDirectory()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  92 */     File[] dirfiles = dir.listFiles(new FileFilter()
/*     */         {
/*     */           
/*     */           public boolean accept(File file)
/*     */           {
/*  97 */             return !((!recursive || !file.isDirectory()) && !file.getName().endsWith(".class")); } });
/*     */     byte b;
/*     */     int i;
/*     */     File[] arrayOfFile;
/* 101 */     for (i = arrayOfFile = dirfiles.length, b = 0; b < i; ) { File file = arrayOfFile[b];
/*     */ 
/*     */       
/* 104 */       if (file.isDirectory()) {
/*     */         
/* 106 */         findAndAddClassesInPackageByFile(String.valueOf(packageName) + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 111 */         String className = file.getName().substring(0, file.getName().length() - 6);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 117 */           classes.add(Thread.currentThread().getContextClassLoader().loadClass(String.valueOf(packageName) + '.' + className));
/*     */         }
/* 119 */         catch (ClassNotFoundException e) {
/*     */ 
/*     */           
/* 122 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */       b++; }
/*     */   
/*     */   }
/*     */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zcha\\util\ClassScanUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */