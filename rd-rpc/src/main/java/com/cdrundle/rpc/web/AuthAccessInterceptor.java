/**
 * @copyright 成都市润东实业有限公司 软件开发部
 */
package com.cdrundle.rpc.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 认证拦截器
 * <p>
 *
 * @author tjee
 * @CreateDate 2017年5月22日 下午4:45:09
 */
public class AuthAccessInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthAccessInterceptor.class);


    @Autowired
    Environment env;


    /*
     * (non-Javadoc)
     *
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#
     * preHandle(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestUrl = request.getRequestURL().toString();
        logger.debug("request method={}", request.getMethod());
        logger.debug("request url={}", requestUrl);
        /** 静态资源不拦截 **/
        if (handler instanceof DefaultServletHttpRequestHandler) {
            return true;
        }
        String ip = resolverIP(request);
        boolean isAllow = checkIp(getips(), ip);
        // 保存信息到请求中
        return isAllow;
    }

    //获取配置文件ip
    private List<String> getips() {
        List<String> ipLi = new ArrayList<String>();
        String ips = env.getProperty("not.allow.ip");
        if (StringUtils.isNoneBlank(ips)) {
            String[] ipList = ips.split(",");
            for (String ip : ipList) {
                ipLi.add(ip);
            }
        }
        return ipLi;
    }

    //检查ip
    private boolean checkIp(List<String> ips, String requestIp) {
        return ips.contains(requestIp);
    }

    /**
     * 解析调用者IP
     */
    private String resolverIP(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return (ip.indexOf(",") > 0) ? StringUtils.split(ip, "\\,")[0] : ip;
    }

}
