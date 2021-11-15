package dede.srm.repo.interf;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dede.srm.models.Device;
import dede.srm.models.AreaT;

public interface DeviceRepo extends CrudRepository<Device, Long> {
    @Query("SELECT dv FROM Device dv JOIN FETCH dv.deviceType ")
    public List<Device> findAllDeviceWithType();
    
    @Query("SELECT dv FROM Device dv JOIN FETCH dv.deviceType "
    		+ "WHERE dv.storage.id = ?1 ")
    public List<Device> findAllDeviceByStorage(Long storageId);
    
    @Query("SELECT dv FROM Device dv JOIN FETCH dv.deviceType "
    		+ "WHERE dv.storage.room.id in "
    		+ "(select r.id from AreaT r where r.id = ?1 ) ")
    public List<Device> findAllDeviceByRoom(Long roomId);
    
}
