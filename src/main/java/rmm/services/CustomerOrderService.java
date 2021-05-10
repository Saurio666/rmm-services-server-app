/**
 * 
 */
package rmm.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rmm.crud.CustomerOrderDao;
import rmm.crud.DeviceDao;
import rmm.entity.CustomerOrder;
import rmm.entity.Device;
import rmm.entity.DeviceServiceEntity;
import rmm.entity.DeviceType;

/**
 * @author usr
 *
 */
@Service
public class CustomerOrderService {
	@Autowired
	private CustomerOrderDao customerOrderDao;
	@Autowired
	private Catalog catalog;
	@Autowired
	private DeviceDao deviceDao;
	
	public List<CustomerOrder> getOrders(Integer customerId) {
		return customerOrderDao.findAllCustomerOrder(customerId);
	}
	
	public CustomerOrder createOrder(Integer customerId, 
			Integer deviceId, Integer deviceServiceId,
			Integer quantity) {
		CustomerOrder co = null;
		BigDecimal unitCost = BigDecimal.ZERO;
		if (deviceId >= 0) {
			co = customerOrderDao.findCustomerOrderByDevice(customerId, deviceId);
		} else if (deviceServiceId >= 0) {
			co = customerOrderDao.findCustomerOrderByService(customerId, deviceServiceId);
		}
		
		if (co == null) {
			co = new CustomerOrder();
			co.setCustomerId(customerId);
			co.setCustomerOrderQuantity(quantity);
			co.setDeviceId(deviceId >= 0 ? deviceId : null);
			co.setDeviceServiceId(deviceServiceId >= 0 ? deviceServiceId : null);
			unitCost = getUnitCost(deviceId, deviceServiceId);
			co.setUnitCost(unitCost);
		} else {
			co.setCustomerOrderQuantity(quantity);
		}
		
		return customerOrderDao.save(co);		
	}
	
	public void deleteOrder(Integer customerId, 
			Integer deviceId, Integer deviceServiceId) {
		CustomerOrder co = null;
		if (deviceId >= 0) {
			co = customerOrderDao.findCustomerOrderByDevice(customerId, deviceId);
		} else if (deviceServiceId >= 0) {
			co = customerOrderDao.findCustomerOrderByService(customerId, deviceServiceId);
		}
		
		if (co != null) {
			customerOrderDao.deleteById(co.getId());
		}
	}
	
	private BigDecimal getUnitCost(Integer deviceId, Integer deviceServiceId) {
		BigDecimal unitCost = BigDecimal.ZERO;
		if (deviceId >= 0) {
			Device d = deviceDao.findById(deviceId).get();
			DeviceType dt = catalog.getDeviceType(d.getDeviceTypeId());
			unitCost = dt.getCost();
			
		} else if (deviceServiceId >= 0) {
			DeviceServiceEntity dse = catalog.getDeviceServiceEntity(deviceServiceId);			
			unitCost = dse.getDeviceServiceCost();
		}
		return unitCost;
	}
	
	public BigDecimal computeOrder(Integer customerId) {
		List<CustomerOrder> orders = customerOrderDao.findAllCustomerOrder(customerId);
		BigDecimal total = BigDecimal.ZERO;
		
		for(CustomerOrder co : orders) {
			total = total.add(co.getUnitCost().multiply(new BigDecimal(co.getCustomerOrderQuantity())));
		}
		
		
		return total;
	}
}
