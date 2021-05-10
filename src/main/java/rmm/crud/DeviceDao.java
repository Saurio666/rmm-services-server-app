/**
 * 
 */
package rmm.crud;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import rmm.entity.Device;

/**
 * @author usr
 *
 */
public interface DeviceDao extends CrudRepository<Device, Integer> {
	@Query("select d from Device d where d.customerId = :customerId and  d.status = 1")
	List<Device> findAllCustomerDevice(@Param("customerId")Integer customerId);
	
}
