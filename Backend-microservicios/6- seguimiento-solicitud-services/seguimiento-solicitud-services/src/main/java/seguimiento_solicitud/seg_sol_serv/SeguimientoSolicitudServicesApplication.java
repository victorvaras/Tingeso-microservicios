package seguimiento_solicitud.seg_sol_serv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SeguimientoSolicitudServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeguimientoSolicitudServicesApplication.class, args);
	}

}
