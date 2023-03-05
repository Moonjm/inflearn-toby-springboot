package jmspring.helloboot;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class HellobootApplication {


    private final JdbcTemplate jdbcTemplate;

    public HellobootApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init() {
        jdbcTemplate.execute("""
                            create table if not exists hello(
                            name varchar(50) primary key,
                            count int
                            )
                """);
    }

    public static void main(String[] args) {
        SpringApplication.run(HellobootApplication.class, args);
    }


    // 프론트 컨트롤러 사용
//    public static void main(String[] args) {
//        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
//        WebServer webServer = serverFactory.getWebServer(servletContext -> {
//            HelloController helloController = new HelloController();
//            servletContext.addServlet("frontcontroller", new HttpServlet() {
//                @Override
//                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                    // 인증, 보안, 다국어, 공통 기능
//                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
//                        String name = req.getParameter("name");
//
//                        String ret = helloController.hello(name);
//
//                        resp.setStatus(HttpStatus.OK.value());
//                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
//                        resp.getWriter().println(ret);
//                    } else {
//                        resp.setStatus(HttpStatus.NOT_FOUND.value());
//                    }
//                }
//            }).addMapping("/*");
//        });
//        webServer.start();
//    }

    // 서블릿 사용
//    public static void main(String[] args) {
//        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
//        WebServer webServer = serverFactory.getWebServer(servletContext -> {
//            servletContext.addServlet("hello", new HttpServlet() {
//                @Override
//                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                    String name = req.getParameter("name");
//                    resp.setStatus(HttpStatus.OK.value());
//                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
//                    resp.getWriter().println("Hello " + name);
//                }
//            }).addMapping("/hello");
//        });
//        webServer.start();
//    }

}
