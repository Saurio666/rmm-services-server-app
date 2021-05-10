/**
 * 
 */
package rmm.rest;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rmm.entity.CustomerOrder;
import rmm.entity.Device;
import rmm.services.CustomerOrderService;
import rmm.services.DeviceService;

/**
 * @author usr
 *
 */
@SpringBootApplication
@RestController
@ComponentScan({"rmm.crud", "rmm.services", "rmm.entity"})
@EntityScan("rmm.entity")
@EnableJpaRepositories(basePackages="rmm.crud")
@RequestMapping("/rmm")
@Validated
public class CustomerService {
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private CustomerOrderService customerOrderService; 
	
	
	@GetMapping("/getDeviceList")
	public List<Device> getDevice(@RequestParam(value = "customerId", defaultValue = "-1") Integer customerId) {		
		return deviceService.getAllByCustomer(customerId);
	}
	
	@GetMapping("/addDevice")
	public Device addDevice(@RequestParam(value = "customerId") Integer customerId, 
			@RequestParam(value = "deviceTypeId") Integer deviceTypeId, 
			@RequestParam(value = "systemName") String systemName) {
		return deviceService.create(customerId, deviceTypeId, systemName);
	}	
	
	@GetMapping("/updateDevice")
	public Device updateDevice(@RequestParam(value = "deviceId") Integer deviceId, 
			@RequestParam(value = "deviceTypeId") Integer deviceTypeId, 
			@RequestParam(value = "systemName") String systemName) {
		return deviceService.updateDevice(deviceId, deviceTypeId, systemName);
	}
	
	@GetMapping("/deleteDevice")
	public String deleteDevice(@RequestParam(value = "deviceId") Integer deviceId) {
		return deviceService.deleteDevice(deviceId);
	}
	
	@GetMapping("/getOrderList")
	public List<CustomerOrder> getOrders(@RequestParam(value = "customerId", defaultValue = "-1") Integer customerId) {
		return customerOrderService.getOrders(customerId);				
	}
	
	@GetMapping("/addOrderDevice")
	public CustomerOrder addOrderDevice(@RequestParam(value = "customerId") Integer customerId,
			@RequestParam(value = "deviceId", defaultValue = "-1") Integer deviceId,
			@RequestParam(value = "deviceServiceId", defaultValue = "-1") Integer deviceServiceId,
			@RequestParam(value = "quantity", defaultValue = "1") Integer quantity) {
		if (quantity > 0) {
			return customerOrderService.createOrder(customerId, deviceId, deviceServiceId, quantity);
		} else {
			customerOrderService.deleteOrder(customerId, deviceId, deviceServiceId);			
		}
		
		return null;
	}
	
	@GetMapping("/computeOrder")
	public BigDecimal computeOrder(@RequestParam(value = "customerId") Integer customerId) {
		return customerOrderService.computeOrder(customerId);
	}
}
