package dede.srm.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dede.srm.exception.PkIsEmptyException;
import dede.srm.models.Device;
import dede.srm.models.AreaT;
import dede.srm.models.AreaType;
import dede.srm.models.Storage;
import dede.srm.repo.interf.DeviceRepo;
import dede.srm.repo.interf.AreaRepo;
import dede.srm.repo.interf.StorageRepo;
import dede.srm.service.interf.RoomService;
import dede.srm.util.ObjectCheckerUtil;
import dede.srm.util.PropertyUtil;

import java.util.Optional;

@Service
public class ImplRoomService implements RoomService {

	@Autowired
	private AreaRepo areaRepo;
	
	@Autowired
	private DeviceRepo deviceRepo;
	
	@Autowired
	private StorageRepo storageRepo;
	
	@Override
	public Iterable<AreaT> findAllRoom() {
		return areaRepo.findAll();
	}
	
	@Override
	public List<AreaT> findAllRoomWithType() {
		return areaRepo.findAllRoomWithType();
	}

	@Override
	public AreaT saveArea(AreaT area) throws Exception {
		
		if(area == null) {
			throw new NullPointerException();
		}
		
		AreaType areaType = area.getAreaType();
		if(areaType == null) {
			throw new NullPointerException();
		}
		
		if(area.getId() != null) {
			throw new PkIsEmptyException();
		}
		
		Date nowDate = new Date();
		area.setCreateDate(nowDate);
		area.setUpdateDate(nowDate);

		AreaT roomEntity = areaRepo.save(area);
		
		return roomEntity;
	}
	
	@Override
	public AreaT replaceArea(AreaT area) throws Exception {
		if(ObjectCheckerUtil.isObjectPkNull(area, area::getId)) {
			throw new NullPointerException();
		}
		
		Optional<AreaT> areaOptional = areaRepo.findById(area.getId());
		if(areaOptional.isEmpty()) {
			throw new NullPointerException();
		}
		
		AreaT entityRoom = areaRepo.save(area);
		return entityRoom;
	}
	
	@Override
	public AreaT updateRoom(AreaT room) throws Exception{
		if(ObjectCheckerUtil.isObjectPkNull(room, room::getId)) {
			throw new NullPointerException();
		}
		
		Optional<AreaT> areaOptional = areaRepo.findById(room.getId());
		if(areaOptional.isEmpty()) {
			throw new NullPointerException();
		}
		
		AreaT entityRoom = areaOptional.get();
		BeanUtils.copyProperties(room, entityRoom, PropertyUtil.getNullPropertyArray(entityRoom));
		entityRoom = areaRepo.save(entityRoom);
		return entityRoom;
	}

	@Override
	@Transactional
	public void deleteRoom(AreaT room) throws Exception{
		if(ObjectCheckerUtil.isObjectPkNull(room, room::getId)) {
			throw new NullPointerException();
		}
		
		List<Storage> storageList =  storageRepo.findAllStorageByRoom(room.getId());
		
		
		for(Storage storage :storageList) {
			storage.setRoom(null);
		}
		storageRepo.saveAll(storageList);
//		detailService.deleteDetailByRef(Storage.class.getSimpleName(), room.getId());
		areaRepo.delete(room);
	}
	

	@Override
	@Transactional
	public void deleteRoomAndEverythingWithin(AreaT room) throws Exception {
		if(ObjectCheckerUtil.isObjectPkNull(room, room::getId)) {
			throw new NullPointerException();
		}
		
		List<Storage> stroageList = storageRepo.findAllStorageByRoom(room.getId());
		
		
		for(Storage storage : stroageList) {
			List<Device> deviceList = deviceRepo.findAllDeviceByStorage(storage.getId());
			
			for(Device device : deviceList ) {

			}

			deviceRepo.deleteAll(deviceList);
			
		}
		

		areaRepo.delete(room);
		
	}
	
	


	@Override
	public AreaT findRoomById(Long roomId) throws Exception{
		if(roomId == null) {
			throw new NullPointerException();
		}
		Optional<AreaT> room = areaRepo.findById(roomId);
		return room.get();
	}

	@Override
	public List<AreaT> findRoomsByName() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AreaT> findRoomsByStoragesInside(List<Storage> storageList) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AreaT> findRoomsByDevicesInside(List<Device> storageList) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}





	

}
