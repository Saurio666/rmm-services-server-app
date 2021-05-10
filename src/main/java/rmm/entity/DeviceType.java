/**
 * 
 */
package rmm.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author usr
 *
 */
@Entity
@Table(name = "device_type")
public class DeviceType {
	@Id
	@Getter
	@Setter
	@Column(name="device_type_id")
	private Integer deviceTypeId;
	
	@Getter
	@Setter
	@Column(name="device_type_name")
	private String name;
	
	@Getter
	@Setter
	@Column(name="device_type_cost")
	private BigDecimal cost;
	
}
