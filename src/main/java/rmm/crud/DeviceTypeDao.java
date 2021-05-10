/**
 * 
 */
package rmm.crud;

import org.springframework.data.repository.CrudRepository;

import rmm.entity.DeviceType;

/**
 * @author usr
 *
 */
public interface DeviceTypeDao extends CrudRepository<DeviceType, Integer> {

}
