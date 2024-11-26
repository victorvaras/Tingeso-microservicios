package evaluacion_credito.evaluacion_credito_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EvaluacionCreditoServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluacionCreditoServicesApplication.class, args);
	}

}
