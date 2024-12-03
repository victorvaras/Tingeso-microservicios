package business_logic_service.business_logic.Controller;

import business_logic_service.business_logic.Services.Business_Logic_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business_logic/")
public class Business_Logic_Controller {

    @Autowired
    private Business_Logic_Service business_LogicService;

    @GetMapping("validate_r1/{ingreso_cliente}/{id_solicitud_Credito}")
    public boolean validate_R1(@PathVariable int ingreso_cliente, @PathVariable int id_solicitud_Credito){
        boolean R1 = business_LogicService.validate_R1(ingreso_cliente,id_solicitud_Credito);
        return business_LogicService.validate_R1(ingreso_cliente,id_solicitud_Credito);
    }
}
