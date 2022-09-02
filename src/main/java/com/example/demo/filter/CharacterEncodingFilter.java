package com.example.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//Filter: servlet fileter, not logging fileter
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        //서블릿 실행전 해야할 것 doFilter 전에
        System.out.println("before filter");
        servletRequest.setCharacterEncoding("UTF-8");


        //서블릿 실행 흐름을 그대로 할거면 그냥 이렇게 아니면 다른 조건을 넣어서 바꿀 수도 있다
        filterChain.doFilter(servletRequest,servletResponse);

        //서블릿 실행 후 나오면서 해야할 것 doFilter 후에
        System.out.println("after filter");

    }
}
