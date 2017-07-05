package com.cdrundle.rpc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.HttpServletBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpHessianServlet extends HttpServletBean {
    private Map<String, HttpRequestHandler> requestHandlerMap = new HashMap<String, HttpRequestHandler>();
    private static Logger logger = LoggerFactory.getLogger(HttpHessianServlet.class);
    private static final long serialVersionUID = -2409880638944395829L;

    public void put(String key, HttpRequestHandler value) {
        requestHandlerMap.put(key, value);
    }

    public void init(ServletConfig config)
            throws ServletException {
        logger.info("创建 hessian 服务对外的servlet");
        super.init(config);
        super.destroy();
    }

    public void setRequestHandlerMap(Map<String, HttpRequestHandler> requestHandlerMap) {
        this.requestHandlerMap = requestHandlerMap;
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("接收 hessian 服务对外的servlet");
        HttpRequestHandler httpRequestHandler = requestHandlerMap.get(req.getPathInfo());
        if (httpRequestHandler == null) {
            logger.info("访问的hessian对象不存在pathInfo:" + req.getPathInfo());
            ((ServletRequest) resp).getRequestDispatcher("404.html").forward(req, resp);
            return;
        }
        httpRequestHandler.handleRequest(req, resp);
    }
}