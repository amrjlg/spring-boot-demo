package com.jiang.session.web;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Jiang
 * @date 2018/11/29
 * @time 13:45
 */
@Controller
public class ErrorController extends AbstractErrorController {

    private final ErrorProperties errorProperties;
    private final ErrorAttributes errorAttributes;

    public ErrorController(ErrorAttributes errorAttributes,
                           ServerProperties serverProperties,
                           List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
        this.errorAttributes = errorAttributes;
        this.errorProperties=serverProperties.getError();
    }


    @RequestMapping("/404.html")
    public ModelAndView errorPage(HttpServletRequest request) {
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(new ServletWebRequest(request), isIncludeStackTrace(request));
        errorAttributes.put("message", "你访问的页面不存在");
        return new ModelAndView("error", errorAttributes);
    }

    @RequestMapping("/500.html")
    public ModelAndView serverErrorPage(HttpServletRequest request) {
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(new ServletWebRequest(request), isIncludeStackTrace(request));
        return new ModelAndView("error", errorAttributes);
    }
    private boolean isIncludeStackTrace(HttpServletRequest request) {
        ErrorProperties.IncludeStacktrace include = errorProperties.getIncludeStacktrace();
        return include == ErrorProperties.IncludeStacktrace.ALWAYS || include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM && getTraceParameter(request);
    }

    @Override
    public String getErrorPath() {
        return errorProperties.getPath();
    }
}
