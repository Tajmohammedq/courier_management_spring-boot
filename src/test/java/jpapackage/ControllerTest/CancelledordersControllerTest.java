package jpapackage.ControllerTest;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import jpapackage.controller.CancelledordersController;
import jpapackage.entity.CancelledOrders;
import jpapackage.service.CancelledOrdersService;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;





public class CancelledordersControllerTest {
	@Tested
	CancelledordersController controllerss;
	@Injectable
	CancelledOrdersService service;
	@Injectable
	private PasswordEncoder encoder;
	@Test
	public void delete() {
		  CancelledOrders orders=new CancelledOrders();
		  System.out.println("came in");
		  controllerss.delete(orders);
		  new Verifications() {{
	            service.AddToCancelOrder(orders);
	        }};

	}



}
