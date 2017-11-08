package com.lanou.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yugege on 17/11/7.
 */
public class CustomExcetionResolver implements HandlerExceptionResolver {


    //前端控制器DispatcherServlet在进行HandlerMapping,
    // 通过HandlerAdapter执行Handler的过程中,如果遇到异常,就执行这个方法;

    //Handler参数是最终要执行的方法(handler),真实身份是HandlerMethod;
    //Exception e 就是收到的异常信息;
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o,
                                         Exception e) {
        //输出异常信息;
        e.printStackTrace();

        CustomExcetion excetion = null;

        if (e instanceof CustomExcetion){
            //shiro的异常

            excetion = (CustomExcetion) e;

        }else {
            //其他异常
            excetion = new CustomExcetion("未知错误");
        }

        //获取异常信息
        String msg = excetion.getMessage();

        httpServletRequest.setAttribute("msg",msg);

        try {
            httpServletRequest.getRequestDispatcher("/WEB-INF/error.jsp").forward(httpServletRequest,httpServletResponse);
        } catch (ServletException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return new ModelAndView();
    }
}
