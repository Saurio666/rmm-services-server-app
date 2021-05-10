/**
 * 
 */
package rmm.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import rmm.crud.DeviceServiceDao;
import rmm.crud.DeviceTypeDao;
import rmm.entity.DeviceServiceEntity;
import rmm.entity.DeviceType;

/**
 * @author usr
 *
 */
@Service
@Scope("singleton")
public class Catalog {
	@Autowired
	private DeviceServiceDao deviceServiceDao;
	@Autowired
	private DeviceTypeDao deviceTypeDao;
	
	private List<DeviceServiceEntity> deviceServiceEntityList = new ArrayList<DeviceServiceEntity>();
	private List<DeviceType> deviceTypeList = new ArrayList<DeviceType>();	
	
	public DeviceServiceEntity getDeviceServiceEntity (Integer id) {
		if (deviceServiceEntityList.isEmpty()) {
			Iterable<DeviceServiceEntity> i = deviceServiceDao.findAll();
			i.forEach(deviceServiceEntityList::add);
		}
		Optional<DeviceServiceEntity> ds = deviceServiceEntityList.stream().parallel().filter(dse -> dse.getDeviceServiceId().equals(id)).findFirst();
		
		if (ds.isPresent()) {
			return ds.get();
		}
		
		return null;
	}
	
	public DeviceType getDeviceType (Integer id) {
		if (deviceTypeList.isEmpty()) {
			Iterable<DeviceType> i = deviceTypeDao.findAll();
			i.forEach(deviceTypeList::add);
		}
		Optional<DeviceType> d = deviceTypeList.stream().parallel().filter(dt -> dt.getDeviceTypeId().equals(id)).findFirst();
		
		if (d.isPresent()) {
			return d.get();
		}
		
		return null;
	}
}
