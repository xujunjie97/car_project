//package com.bishe.consumer.config.security;
//
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//public class MySecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        //开启自动配置的登陆功能,若没有权限就跳到登陆页面
//        //默认post形式的/login代表处理登陆
//        //如果是定制loginpage，那么post请求是登陆url是定制路径
//        http.formLogin().usernameParameter("user").passwordParameter("pwd");
//
//        //开启自动配置的注销功能˙
//        //访问/logout请求
//        http.logout().logoutSuccessUrl("/");//注销成功后访问的页面
//
//        //记住我
//        http.rememberMe().rememberMeParameter("remeber");
//
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//        //认证规则
//        //必须要有一个自定义密码编码器
////        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder())
////                .withUser("zhangsan").password("123456").roles("VIP1","VIP2")
////                .and()
////                .withUser("lisi").password("123456").roles("VIP2","VIP3")
////                .and()
////                .withUser("wangwu").password("123456").roles("VIP1","VIP3");
//
//    }
//}
