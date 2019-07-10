/*    */ package com.zchao.util;
/*    */ 
/*    */ import com.zchao.annotation.Action;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ActionUtils
/*    */ {
/*    */   public static ActionEntity getActionEntity(HttpServletRequest request, HttpServletResponse response, List<Class<?>> list) {
/* 18 */     ActionEntity actionEntity = null;
/*    */     
/* 20 */     String port = (new StringBuilder(String.valueOf(request.getServerPort()))).toString();
/* 21 */     String url = request.getRequestURL().toString();
/*    */     
/* 23 */     int index = url.indexOf(port);
/* 24 */     String actionAndMethod = url.substring(index + port.length());
/* 25 */     index = actionAndMethod.lastIndexOf("/");
/* 26 */     String actionUrl = actionAndMethod.substring(0, index);
/* 27 */     if (actionUrl == null || "".equals(actionUrl.trim()))
/*    */     {
/* 29 */       throw new RuntimeException("Not Match Action!");
/*    */     }
/* 31 */     String methodName = actionAndMethod.substring(index + 1);
/* 32 */     if (methodName == null || "".equals(methodName.trim()))
/*    */     {
/* 34 */       throw new RuntimeException("Not Match Method of Action!");
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     boolean actionFlag = false;
/* 41 */     boolean methodFlag = false;
/* 42 */     for (Class<?> cls : list) {
/*    */       
/* 44 */       Action action = (Action)cls.getAnnotation(Action.class);
/* 45 */       if (action.value().equals(actionUrl)) {
/*    */         
/* 47 */         actionFlag = true;
/*    */         
/* 49 */         Method[] methods = cls.getMethods();
/* 50 */         if (methods.length <= 0)
/*    */         {
/* 52 */           throw new RuntimeException("UnKnown Method!"); }  byte b; int i;
/*    */         Method[] arrayOfMethod;
/* 54 */         for (i = arrayOfMethod = methods.length, b = 0; b < i; ) { Method method = arrayOfMethod[b];
/*    */           
/* 56 */           if (method.getName().equals(methodName)) {
/*    */             
/* 58 */             methodFlag = true;
/*    */             
/* 60 */             actionEntity = new ActionEntity();
/* 61 */             actionEntity.setAction(cls);
/* 62 */             actionEntity.setMethod(method); break;
/*    */           } 
/*    */           b++; }
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 69 */     if (!actionFlag)
/*    */     {
/* 71 */       throw new RuntimeException("Not Match Value Of @Action is " + actionUrl);
/*    */     }
/* 73 */     if (!methodFlag)
/*    */     {
/* 75 */       throw new RuntimeException("Not Match Method of Action!");
/*    */     }
/* 77 */     return actionEntity;
/*    */   }
/*    */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zcha\\util\ActionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */