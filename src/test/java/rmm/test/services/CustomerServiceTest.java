package rmm.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import rmm.crud.CustomerOrderDao;
import rmm.crud.DeviceDao;
import rmm.crud.DeviceTypeDao;
import rmm.entity.CustomerOrder;
import rmm.entity.Device;
import rmm.rest.App;
import rmm.rest.CustomerService;
import rmm.services.Catalog;
import rmm.services.CustomerOrderService;
import rmm.services.DeviceService;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {
	@TestConfiguration
	static class CustomerServiceConfig{
		@Bean
		public CustomerOrderService customerOrderService() {
			return new CustomerOrderService();
		}		
	}
	
	@MockBean
	private CustomerOrderDao customerOrderDao;
	@MockBean
	private Catalog catalog;
	@MockBean
	private DeviceDao deviceDao;	
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	private CustomerOrder createOrder(Integer customerId, 
			Integer deviceId, Integer deviceServiceId,
			Integer quantity, BigDecimal unitCost) {
		CustomerOrder co = new CustomerOrder();
		co.setCustomerId(customerId);
		co.setCustomerOrderQuantity(quantity);
		co.setDeviceId(deviceId >= 0 ? deviceId : null);
		co.setDeviceServiceId(deviceServiceId >= 0 ? deviceServiceId : null);
		co.setUnitCost(unitCost);
		
		return co;
	}	
	
	@Before
	public void setUp() {		
		List<CustomerOrder> orders = new ArrayList<CustomerOrder>();		
		//Create order devices
		//2 windows
		orders.add(createOrder(1, 1, -1, 2, BigDecimal.valueOf(4)));
		//3 mac
		orders.add(createOrder(1, 2, -1, 3, BigDecimal.valueOf(4)));
		
		//create order services
		//2 windows antivirus (id: 1)
		orders.add(createOrder(1, -1, 1, 2, BigDecimal.valueOf(5)));
		//3 mac antivirus (id: 2)
		orders.add(createOrder(1, -1, 2, 3, BigDecimal.valueOf(7)));
		//5 Cloudberry (id: 3)
		orders.add(createOrder(1, -1, 3, 5, BigDecimal.valueOf(3)));
		//5 TeamViewer (id: 5)
		orders.add(createOrder(1, -1, 5, 5, BigDecimal.valueOf(1)));
		
		Mockito.when(customerOrderDao.findAllCustomerOrder(1))
	      .thenReturn(orders);
	}
	
	@Test
	public void testDeleteDevice() {
		List<CustomerOrder> orders = customerOrderService.getOrders(1);
		assertEquals(6, orders.size());
		
		BigDecimal cost = customerOrderService.computeOrder(1);		
		assertEquals(BigDecimal.valueOf(71), cost);
	}
}
