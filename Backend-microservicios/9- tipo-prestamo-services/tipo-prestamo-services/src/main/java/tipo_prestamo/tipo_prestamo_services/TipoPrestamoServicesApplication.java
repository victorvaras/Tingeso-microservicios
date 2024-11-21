package tipo_prestamo.tipo_prestamo_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TipoPrestamoServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipoPrestamoServicesApplication.class, args);
	}

}
