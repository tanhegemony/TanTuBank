///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.ivt.spring_project_internship_tantubank.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//
///**
// *
// * @author TanHegemony
// */
//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackages = {"com.ivt.spring_project_internship_tantubank"})
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
//    }
//
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
////    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/", "/login", "/logout", "/home").permitAll();
//
////        http.authorizeRequests().antMatchers("/addVoteMovie","/booking","/booking_seat",
////                "/view_checkout","/checkout","/manage_user", "/viewBookingDetail","/addVoteReviewMovie",
////                "/likeReviewMovie").access("hasAnyRole('ROLE_ADMIN,ROLE_USER,ROLE_MANAGER,ROLE_RECEPTIONIST')")
////                .antMatchers("/admin/home").access("hasAnyRole('ROLE_ADMIN,ROLE_MANAGER,ROLE_RECEPTIONIST')")
////                .antMatchers("/admin/accounts","/admin/accountsBanking").access("hasRole('ROLE_ADMIN')")
////                .antMatchers("/admin/payments").access("hasAnyRole('ROLE_MANAGER, ROLE_RECEPTIONIST')")
////                .antMatchers("/admin/bookings","/admin/customers","/admin/categories", "/admin/cinemaRooms",
////                        "/admin/cinemas", "/admin/cinemaMovies", "/admin/foods", "/admin/movies",
////                        "/admin/promotions", "/admin/reviews", "/admin/seats", "/admin/tickets",
////                        "/admin/votes").access("hasRole('ROLE_MANAGER')");
//
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//
//        http.authorizeRequests().and().formLogin()
//                .loginProcessingUrl("/j_spring_security_check")
//                .loginPage("/login")
//                .defaultSuccessUrl("/home")
//                .failureUrl("/login?error=true")
//                .usernameParameter("userName")
//                .passwordParameter("password")
//                .and().logout().logoutUrl("/logout1?logoutStatus=true")
//                .logoutSuccessUrl("/home").deleteCookies("JSESSIONID");
//        
//        http.authorizeRequests().and().rememberMe().tokenRepository(this.persistentTokenRepository())
//                .tokenValiditySeconds(24*60*60);
//    }
//    // Ta lưu tạm remember me trong memory (RAM).
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
//        return memory;
//    }
//}
