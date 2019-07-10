/*    */ package com.zchao.server;
/*    */ 
/*    */ import com.zchao.config.Config;
/*    */ import java.util.EnumSet;
/*    */ import javax.servlet.DispatcherType;
/*    */ import org.eclipse.jetty.server.Connector;
/*    */ import org.eclipse.jetty.server.Server;
/*    */ import org.eclipse.jetty.server.ServerConnector;
/*    */ import org.eclipse.jetty.servlet.FilterHolder;
/*    */ import org.eclipse.jetty.servlet.ServletContextHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JettyServer
/*    */ {
/*    */   public static void start(Config config) throws Exception {
/* 22 */     Server server = new Server();
/* 23 */     ServerConnector connector = new ServerConnector(server);
/* 24 */     connector.setPort((config.getPort() == null) ? 8080 : config.getPort().intValue());
/* 25 */     server.setConnectors(new Connector[] { connector });
/*    */     
/* 27 */     EnumSet<DispatcherType> dispatches = EnumSet.of(DispatcherType.ASYNC, DispatcherType.ERROR, 
/* 28 */         DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.REQUEST);
/* 29 */     ServletContextHandler context = new ServletContextHandler();
/*    */     
/* 31 */     if (config.getFilter() != null) {
/*    */       
/* 33 */       FilterHolder filterHolder = new FilterHolder(config.getFilter());
/* 34 */       context.addFilter(filterHolder, "/*", dispatches);
/*    */     } 
/*    */     
/* 37 */     context.addServlet(com.zchao.servlet.BaseServlet.class, "/");
/*    */     
/* 39 */     server.setHandler(context);
/*    */     
/* 41 */     server.start();
/* 42 */     server.join();
/*    */   }
/*    */ }


/* Location:              H:\eclipse-workspace\Jetty_demo\lib\zchao.jar!\com\zchao\server\JettyServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.6
 */