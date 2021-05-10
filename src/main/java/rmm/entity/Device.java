/**
 * 
 */
package rmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * @author usr
 *
 */
@Entity
public class Device {
	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "device_id", columnDefinition = "serial")
	private Integer id;
	
	@Getter
	@Setter
	@Column(name = "system_name")
	private String systemName;
	
	@Getter
	@Setter
	@Column(name = "device_type_id")
	private Integer deviceTypeId;
	
	@Getter
	@Setter
	@Column(name = "status", insertable = false)
	private Integer status;
	
	@Getter
	@Setter
	@Column(name = "customer_id")
	private Integer customerId;

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getSystemName() {
//		return systemName;
//	}
//
//	public void setSystemName(String systemName) {
//		this.systemName = systemName;
//	}
//
//	public Integer getDeviceTypeId() {
//		return deviceTypeId;
//	}
//
//	public void setDeviceTypeId(Integer deviceTypeId) {
//		this.deviceTypeId = deviceTypeId;
//	}
//
//	public Integer getStatus() {
//		return status;
//	}
//
//	public void setStatus(Integer status) {
//		this.status = status;
//	}
//
//	public Integer getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(Integer customerId) {
//		this.customerId = customerId;
//	}
//	
	
}
