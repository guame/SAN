package earth.guam.santool;

import org.greenrobot.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@ComponentScan("earth.guam.santool")
@SpringBootApplication
public class SanApplication extends SpringBootServletInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(SanApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SanApplication.class);
	}
	
	@Bean(name = "appEventBus")
	EventBus appEventBus() {
		return EventBus.builder().installDefaultEventBus();
	}
}