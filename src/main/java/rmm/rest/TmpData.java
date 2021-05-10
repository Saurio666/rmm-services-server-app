/**
 * 
 */
package rmm.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import rmm.entity.DeviceType;

/**
 * @author usr
 *
 */
public class TmpData {
	private static TmpData instance;
	
	@Getter
	private List<DeviceType> deviceTypes;
	
	private TmpData() {
		deviceTypes = new ArrayList<DeviceType>();
		
		deviceTypes.add(creatDeviceType(1, "Windows Workspace", BigDecimal.valueOf(2)));
		deviceTypes.add(creatDeviceType(2, "Windows Server", BigDecimal.valueOf(10)));
		deviceTypes.add(creatDeviceType(3, "Windows Server", BigDecimal.valueOf(4)));
	}
	
	private DeviceType creatDeviceType(Integer id, String name, BigDecimal cost) {
		DeviceType dt = new DeviceType();
		dt.setDeviceTypeId(id);
		dt.setName(name);
		dt.setCost(cost);
		return dt;
	}
	
	public List<DeviceType> getAll() {
		return deviceTypes;
	}
	
	public static TmpData getInstance() {
		if (instance == null) {
			instance = new TmpData();
		}
		
		return instance;
	}
}
