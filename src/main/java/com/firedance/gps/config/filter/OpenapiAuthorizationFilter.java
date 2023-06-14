package com.firedance.gps.config.filter;

import com.alibaba.fastjson.JSONObject;
import com.firedance.gps.dao.AccessClientMapper;
import com.firedance.gps.handler.result.Result;
import com.firedance.gps.handler.result.ResultHelper;
import com.firedance.gps.model.AccessClient;
import com.firedance.gps.model.enums.ExceptionEnum;
import com.google.common.hash.Hashing;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author tangqi
 */
@WebFilter(urlPatterns={"/openapi/*"})
public class OpenapiAuthorizationFilter implements Filter {


    AccessClientMapper accessClientMapper;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
        accessClientMapper = ctx.getBean(AccessClientMapper.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] access_keys = parameterMap.get("access_key");
        String[] signs = parameterMap.get("sign");
        String[] ts = parameterMap.get("t");
        if(access_keys == null || access_keys.length == 0){
            Result<Boolean> fail =
                ResultHelper.fail(ExceptionEnum.C0001.getCode(), ExceptionEnum.C0001.getMessage(), false);
            response.getOutputStream().write(fail.toString().getBytes(StandardCharsets.UTF_8));
            return;
        }
        if(signs == null || signs.length == 0){
            Result<Boolean> fail =
                ResultHelper.fail(ExceptionEnum.C0002.getCode(), ExceptionEnum.C0002.getMessage(), false);
            response.getOutputStream().write(fail.toString().getBytes(StandardCharsets.UTF_8));
            return;
        }
        String accessKey = access_keys[0];
        String sign = signs[0];
        AccessClient accessClient = accessClientMapper.selectByKey(accessKey);
        if(accessClient == null || accessClient.getEnable().equals(false)){
            Result<Boolean> fail =
                ResultHelper.fail(ExceptionEnum.C0001.getCode(), ExceptionEnum.C0001.getMessage(), false);
            response.getOutputStream().write(fail.toString().getBytes(StandardCharsets.UTF_8));
            return;
        }
        String param = "access_key="+accessKey+"&t="+ts[0]+"&access_secret="+accessClient.getAccessSecret();
        String encrypt = Hashing.md5().hashBytes((param).getBytes()).toString();
        if(sign.equals(encrypt)){
            chain.doFilter(request,response);
        }else{
            Result<Boolean> fail =
                ResultHelper.fail(ExceptionEnum.C0002.getCode(), ExceptionEnum.C0002.getMessage(), false);
            response.getOutputStream().write(fail.toString().getBytes(StandardCharsets.UTF_8));
            return;
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
