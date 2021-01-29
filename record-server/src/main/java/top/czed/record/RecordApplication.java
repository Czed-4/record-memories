package top.czed.record;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author Czed
 * @Date 2021-1-11
 * @Description
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
public class RecordApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext run = SpringApplication.run(RecordApplication.class, args);
        ConfigurableEnvironment env = run.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Druid: http://{}:{}/druid\n\t" +
                        "Swagger: http://{}:{}/doc.html\n" +
                        "----------------------------------------------------------",
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

}
