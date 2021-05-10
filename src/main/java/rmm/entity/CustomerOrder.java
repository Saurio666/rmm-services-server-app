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
@Table(name = "customer_order")
public class CustomerOrder {
	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_order_id", columnDefinition = "serial")
	private Integer id;
	
	@Getter
	@Setter
	@Column(name = "customer_id")
	private Integer customerId;
	
	@Getter
	@Setter
	@Column(name = "device_id")
	private Integer deviceId;
	
	@Getter
	@Setter
	@Column(name = "device_service_id")
	private Integer deviceServiceId;

	@Getter
	@Setter
	@Column(name = "customer_order_quantity")
	private Integer customerOrderQuantity;
	
	@Getter
	@Setter
	@Column(name="unit_cost")
	private BigDecimal unitCost;

	public BigDecimal getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getDeviceServiceId() {
		return deviceServiceId;
	}

	public void setDeviceServiceId(Integer deviceServiceId) {
		this.deviceServiceId = deviceServiceId;
	}

	public Integer getCustomerOrderQuantity() {
		return customerOrderQuantity;
	}

	public void setCustomerOrderQuantity(Integer customerOrderQuantity) {
		this.customerOrderQuantity = customerOrderQuantity;
	}
}
