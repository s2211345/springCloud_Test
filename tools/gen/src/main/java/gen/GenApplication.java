package gen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


@ServletComponentScan
@SpringBootApplication
public class GenApplication {
	public static void main(String[] args) {
		SpringApplication.run(GenApplication.class, args);
	}
}
