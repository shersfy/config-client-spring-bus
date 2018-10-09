package org.shersfy.bus.rest;

import java.util.Date;

import org.shersfy.bus.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RefreshScope
public class JettyController extends BaseController{
    
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());
    
    @Value("${version}")
    private String version;
    
    @GetMapping("/index")
    public Object index() {
        return "Welcom Jetty Application "+version;
    }
    
    @PostMapping("/user")
    public Object getUser(String name) {
    	User user = new User();
    	user.setId(System.nanoTime());
    	user.setName(name);
    	user.setBirthday(new Date());
    	return user;
    }
    
    @PostMapping("/remote/info")
    public Object getRemote() {
    	JSONObject data = new JSONObject();
    	data.put("remoteHost", getRequest().getRemoteHost());
    	data.put("remoteAddr", getRequest().getRemoteAddr());
    	data.put("servletPath", getRequest().getServletPath());
    	return data;
    }
    
    
}
