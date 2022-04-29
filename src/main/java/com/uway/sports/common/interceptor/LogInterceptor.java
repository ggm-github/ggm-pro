package com.uway.sports.common.interceptor;


import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.common.utils.StringUtils;
import com.uway.sports.web.entity.dbentity.SysLog;
import com.uway.sports.web.service.ISysUserService;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * 日志拦截器
 */
public class LogInterceptor extends BaseServiceImpl implements HandlerInterceptor {

	private static final ThreadLocal<Long> startTimeThreadLocal =
			new NamedThreadLocal<Long>("ThreadLocal StartTime");
	public String defultLogin="/user/login";//默认登录页面

	@Resource
	private ISysUserService iSysUserService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {

		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());

		if(url.startsWith("/user/login") || url.startsWith("/user/userLogin")
                || url.startsWith("/static")|| url.startsWith("/news/getNewsDetails")
				|| url.startsWith("/comm")
                ){
			return true;
		}


		String seesionId = request.getSession().getId();

		String userid = iSysUserService.getToken(seesionId);
		String token = iSysUserService.getToken(userid);

		if(userid ==null || StringUtils.isEmpty(token) || !token.equals(seesionId))
		{
			response.sendRedirect(request.getContextPath()+defultLogin);
			return false;
		}else {
			return true;
		}

//		if (logger.isDebugEnabled()){
//			long beginTime = System.currentTimeMillis();//1、开始时间
//	        startTimeThreadLocal.set(beginTime);		//线程绑定变量（该数据只有当前请求的线程可见）
//	        logger.debug(String.format("开始计时: %s  URI: %s", new SimpleDateFormat("hh:mm:ss.SSS")
//	        	.format(beginTime), request.getRequestURI()));
//		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null){
			logger.info("ViewName: " + modelAndView.getViewName());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {

		// 保存日志
//		LogUtils.saveLog(request, handler, ex, null);
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());

		if(url.startsWith("/user/login") || url.startsWith("/user/userLogin")
				|| url.startsWith("/static")|| url.startsWith("/news/getNewsDetails")
				|| url.startsWith("/comm")
				){

		}else{
			addsysUserLog( request,  ex);
//			String seesionId = request.getRequestedSessionId();
//			String userid = iSysUserService.getToken(seesionId);
			// 打印JVM信息。
//			if (logger.isDebugEnabled()){
//				long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
//				long endTime = System.currentTimeMillis(); 	//2、结束时间
//				logger.debug(String.format("用户id：%s  计时结束：%s  耗时：%s  URI: %s  最大内存: %sm  已分配内存: %sm  已分配内存中的剩余空间: %sm  最大可用内存: %sm",
//						userid==null?"0000":userid,new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateUtils.formatDateTime(endTime - beginTime),
//						request.getRequestURI(), Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024,
//						(Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024));
//			}
		}
	}

	private void addsysUserLog(HttpServletRequest request, Exception ex){
		SysLog sysLog = new SysLog();
		sysLog.setOperateName("");
		sysLog.setType(ex == null ? SysLog.TYPE_ACCESS : SysLog.TYPE_EXCEPTION);
		sysLog.setOperateIp(StringUtils.getIpAddr(request));
		sysLog.setTerminalType(1);
		sysLog.setRequestUrl(request.getRequestURI());
		Map paramMap = request.getParameterMap();
		StringBuilder params = new StringBuilder();

		if (paramMap == null){
			params.append("");
		}
		else {
			for (Map.Entry<String, String[]> param : ((Map<String, String[]>)paramMap).entrySet()){
				params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
				String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
				params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
			}
		}
		sysLog.setParams(params.toString());
		sysLog.setMethod(request.getMethod());
		sysLog.setOperateTime(new Date());
		sysLog.setUserAgent(request.getHeader("user-agent"));
		String seesionId = request.getSession().getId();
		String userid = iSysUserService.getToken(seesionId);
		sysLog.setUserId(userid==null?"0000":userid);
		iSysUserService.addsysUserLog(sysLog);
	}

}
