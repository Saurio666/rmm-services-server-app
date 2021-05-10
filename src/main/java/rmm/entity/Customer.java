/**
 * 
 */
package rmm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * @author usr
 *
 */
@Entity
public class Customer {
	@Id
	@Getter
	@Setter
	@Column(name = "customer_id")
	private Integer id;
	
	@Getter
	@Setter
	@Column(name = "customer_name")
	private String customerName;
}
