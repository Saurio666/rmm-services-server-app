/**
 * 
 */
package rmm.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author usr
 *
 */
@Entity
@Table(name = "device_service")
public class DeviceServiceEntity {
	@Id
	@Getter
	@Setter
	@Column (name = "device_service_id")
	private Integer deviceServiceId;
	
	@Getter
	@Setter
	@Column (name = "device_service_name")
	private String deviceServiceName;
	
	@Getter
	@Setter
	@Column (name = "device_service_description")
	private String deviceServiceDescription;
	
	@Getter
	@Setter
	@Column (name = "device_service_cost")
	private BigDecimal deviceServiceCost;
	
}
