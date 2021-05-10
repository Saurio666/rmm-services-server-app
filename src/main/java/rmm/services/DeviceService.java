/**
 * 
 */
package rmm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rmm.crud.DeviceDao;
import rmm.entity.Device;

/**
 * @author usr
 *
 */
@Service
public class DeviceService {
	@Autowired
	private DeviceDao deviceDao;
	
	public Device create(Integer customerId, Integer deviceTypeId, String systemName) {
		Device d = new Device();
		d.setCustomerId(customerId);
		d.setDeviceTypeId(deviceTypeId);
		d.setSystemName(systemName);
		
		return deviceDao.save(d);
	}
	
	public String deleteDevice(Integer deviceId) {
		Optional<Device> d = deviceDao.findById(deviceId);
		if (d.isPresent()) {
			d.get().setStatus(0);
			deviceDao.save(d.get());
			
			return "DELETED";
		}
		
		return "DEVICE NOT FOUND";
	}
	
	public Device updateDevice(Integer deviceId, Integer deviceTypeId, String systemName) {
		Optional<Device> d = deviceDao.findById(deviceId);
		if (d.isPresent()) {
			d.get().setDeviceTypeId(deviceTypeId);
			d.get().setSystemName(systemName);
			return deviceDao.save(d.get());
		}
		
		return null;
	}
	
	public List<Device> getAllByCustomer(Integer customerId) {
		return deviceDao.findAllCustomerDevice(customerId);
	}
}
