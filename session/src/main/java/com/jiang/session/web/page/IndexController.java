package com.jiang.session.web.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiang
 * @date 2018/11/29
 * @time 13:46
 */
@RestController
public class IndexController {
    @Autowired
    private HttpServletRequest request;
    @GetMapping("/session/id.json")
    public Map<String,Object> sessionId(){
        HttpSession session = request.getSession();
        return new HashMap<String, Object>(){{
            put("sessionId",session.getId());
        }};
    }
}
