# SimpleMVC
这是一个仅仅支持JSON交互的MVC框架

目前该框架还有很大的地方需要改进，由于时间有限，可能要暂且搁置一段时间

Action.java：用来标记要扫描的action【或controller】层，并且被标记的类可以默认支持两个属性：HttpServletRequest和HttpServletResponse

Tx.java：用来标记Service层里面需要事务处理的方法【暂时不支持作用到类上面，仅仅支持作用到方法上面】

Dao.java：用来数据访问层操作的，里面的use方法是用来进行数据库的切换的

DefaultInterceptor.java：是默认拦截器，也可以实现自定义的拦截器，只需要实现Interceptor.java这个接口即可

ExceptionInterceptor.java：统一异常处理拦截器，也是实现的实现Interceptor.java这个接口

ProxyService.java：代理类，用来生成Service的实例，如果使用这个类得到的Service类，会开始事务的标记

JettyServer.java：用来启动Jetty服务器的，里面封装了一些实现

BaseServlet.java：用来处理所有的请求，并进行转发到具体的类进行处理，它继承HttpServlet.java

ClassScanUtil.java：用来扫描所有的Action的

DataSourceUtils.java：用来获取连接和关闭连接的

Config.java：用来配置初始化启动的时候，把所有必须的配置添加上，例如：数据库、端口号、扫描的Action包路径、自定义拦截器等

############################################################

所有的Action方法都支持两个参数:

第一个参数：HttpServletRequest

第二个参数：HttpServletResponse

默认可以不需要

############################################################

#使用用例

Main层【用来启动服务的】

public class JettyTest
{

	public static void main(String[] args) throws Exception
	{
	
		ConfigPro configPro = new ConfigPro();
		
		configPro.setActionPackagePath("com.zchao.action");
		
//		configPro.setInterceptor(new ExceptionInterceptor());

//		configPro.setDbMap(DataSourceFactory.initDataSourcePool());

//		configPro.setDefaultDataSource(DataSourceFactory.getDefaultDataSource());

		ConfigPro.setConfigPro(configPro);
		
		configPro.setPort(9999);
		
		Config config = Config.getInstance();
		
		config.setConfigPro(configPro);
		
		JettyServer.start(config);
		
	}
	
}

controller层

@Action(value = "/A")
public class AController
{

	private AService aService = ProxyService.getProxy(AService.class);
	
	public String t(HttpServletRequest request) throws Exception
	{
	
		return "恭喜你，成功了！";
		
	}
	
}

Service层

public class AService
{

	private Dao dao = new Dao();
	
	//@Tx表示是否开始事务的标记
	@Tx
	public double t(int n) throws Exception
	{
	
  		Map<String, String> map = dao.find("select * from  ykb_hotel_log limit 1", Map.class);	
		
		System.out.println(JSONUtils.toJSONString(map));
		
		return 1d;
		
	}
	
}
