package simulacion_credito.sim_cre_ser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SimulacionCreditoServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulacionCreditoServicesApplication.class, args);
	}

}
