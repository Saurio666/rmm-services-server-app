package rmm.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import rmm.crud.DeviceDao;
import rmm.entity.Device;
import rmm.services.DeviceService;

@RunWith(SpringRunner.class)
public class DeviceServiceTest {
	@TestConfiguration
	static class DeviceServiceConfig {
		@Bean
		public DeviceService deviceService() {
			return new DeviceService();
		}
	};
	
	@Autowired
	private DeviceService deviceService;
	@MockBean
	private DeviceDao deviceDao;
	
	private Device createDevice(Integer customerId, Integer type, String name) {
		Device d = new Device();
		d.setCustomerId(customerId);
		d.setDeviceTypeId(type);
		d.setSystemName(name);

		return d;
	}
	
	@Before
	public void setUp() {
		List<Device> dLst = new ArrayList<Device>();
		dLst.add(createDevice(1, 1, "Windows Server"));
		dLst.add(createDevice(1, 1, "Windows Workstation"));
		dLst.add(createDevice(1, 2, "MAC"));

	    Mockito.when(deviceDao.findAllCustomerDevice(1))
	      .thenReturn(dLst);
	}
	
	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
		List<Device> customerDevices = deviceService.getAllByCustomer(1);		
		assertEquals(3, customerDevices.size());
	 }
}
