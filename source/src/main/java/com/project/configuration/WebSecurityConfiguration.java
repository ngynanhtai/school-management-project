//package vn.com.hdsaison.digital.configuration;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//@EnableWebMvc
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    public WebSecurityConfiguration() {
//        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.cors()
//                .and()
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/v2/api-docs","/v3/api-docs", "/swagger-resources/**", "/swagger-ui/**", "/swagger-ui/index.html**", "/webjars/**", "/actuator/health")
//                .permitAll()
//                .antMatchers("/**")
////            .hasRole("view_contract")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        return (request, response, ex) -> {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            ServletOutputStream out = response.getOutputStream();
//            objectMapper.writeValue(out, ResponseDTO.builder().status(ResponseStatusEnum.ERROR)
//                    .error(new ErrorDTO("Access Denied")).build());
//            out.flush();
//        };
//    }
//}
