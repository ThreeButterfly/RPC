package com.cdrundle.rpc.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tjee on 2017/6/23.
 */
public class IpAddressInInterceptor extends AbstractPhaseInterceptor<Message> {
    @Autowired
    Environment env;
    private static final Logger logger = LoggerFactory
            .getLogger(IpAddressInInterceptor.class);

    public IpAddressInInterceptor() {
        super(Phase.RECEIVE);
    }

    public void handleMessage(Message message) throws Fault {
        HttpServletRequest request = (HttpServletRequest) message
                .get(AbstractHTTPDestination.HTTP_REQUEST);
        if (request == null) {
            return;
        }
        String ipAddress = resolverIP(request);
        boolean isAllow = checkIp(getips(), ipAddress);
        if (!isAllow) {
            logger.warn("***********************ip地址不被允许,IP地址:" + ipAddress
                    + "**************");
            throw new Fault(new IllegalAccessException("IP address "
                    + ipAddress + " is not allowed"));
        }
    }
    @Override
    public void handleFault(Message message) {
        super.handleFault(message);

        logger.info("=================================："+"handleFault");
    }
    private String getIP(HttpServletRequest request) {
        String IP = request.getHeader("X-Client-Address"); // 取clientIP地址
        if (StringUtils.isEmpty(IP)) {
            IP = request.getRemoteAddr();
        }
        return IP;
    }
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
}
