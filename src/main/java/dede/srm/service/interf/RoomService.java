package dede.srm.service.interf;

import java.util.List;

import org.springframework.stereotype.Service;

import dede.srm.models.Device;
import dede.srm.models.AreaT;
import dede.srm.models.Storage;

@Service
public interface RoomService {
	
	public Iterable<AreaT> findAllRoom();
	public List<AreaT> findAllRoomWithType();
	public AreaT saveArea(AreaT room) throws Exception;
	public AreaT replaceArea(AreaT room) throws Exception;
	public AreaT updateRoom(AreaT room) throws Exception;
	public void deleteRoom(AreaT room) throws Exception;
	public void deleteRoomAndEverythingWithin(AreaT room) throws Exception;
	
	public AreaT findRoomById(Long roomId) throws Exception;
	public Iterable<AreaT> findRoomsByName() throws Exception;
	
	
	public Iterable<AreaT> findRoomsByStoragesInside(List<Storage> storageList) throws Exception;
	public Iterable<AreaT> findRoomsByDevicesInside(List<Device> storageList) throws Exception;
	
}
