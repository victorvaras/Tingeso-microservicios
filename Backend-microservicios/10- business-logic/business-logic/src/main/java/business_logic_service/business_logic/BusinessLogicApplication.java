package business_logic_service.business_logic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
public class BusinessLogicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessLogicApplication.class, args);
	}

}
