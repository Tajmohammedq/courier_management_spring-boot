package jpapackage.ServiceTest;



import org.junit.jupiter.api.Test;

import jpapackage.entity.CancelledOrders;
import jpapackage.repo.CancelledOrdersRepo;
import jpapackage.service.CancelledOrdersService;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;

public class CancelledOrdersServiceTest {
	@Tested
	CancelledOrdersService service;
	@Injectable
	CancelledOrdersRepo repo;
	
	@Test
	public void AddToCancelOrder() {
		CancelledOrders order=new CancelledOrders();
		service.AddToCancelOrder(order);
		new Verifications() {{
			repo.save(order);	
		}};
	}

}
