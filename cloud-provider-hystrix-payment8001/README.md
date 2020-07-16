




## hystrix-payment8001添加服务监控hystrixDashBoard

```
    //springcloud的坑,被监控的项目启动类添加下面的配置
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet servlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(servlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

```