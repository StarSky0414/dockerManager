import config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


public class WebStart {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}
