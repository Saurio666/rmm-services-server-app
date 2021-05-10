/**
 * 
 */
package rmm.crud;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import rmm.entity.CustomerOrder;

/**
 * @author usr
 *
 */
public interface CustomerOrderDao extends CrudRepository<CustomerOrder, Integer> {
	@Query("select co from CustomerOrder co where co.customerId = :customerId")
	List<CustomerOrder> findAllCustomerOrder(@Param("customerId")Integer customerId);
	
	@Query("select co from CustomerOrder co where co.customerId = :customerId and co.deviceId = :deviceId")
	CustomerOrder findCustomerOrderByDevice(@Param("customerId")Integer customerId, @Param("deviceId")Integer deviceId);
	
	@Query("select co from CustomerOrder co where co.customerId = :customerId and co.deviceServiceId = :deviceServiceId")
	CustomerOrder findCustomerOrderByService(@Param("customerId")Integer customerId, @Param("deviceServiceId")Integer deviceServiceId);
}
