/**
 * 
 */
package rmm.crud;

import org.springframework.data.repository.CrudRepository;

import rmm.entity.DeviceServiceEntity;


/**
 * @author usr
 *
 */
public interface DeviceServiceDao extends CrudRepository<DeviceServiceEntity, Integer> {

}
