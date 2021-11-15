package dede.srm.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dede.srm.exception.PkIsEmptyException;
import dede.srm.models.Device;
import dede.srm.models.Storage;
import dede.srm.models.StorageType;
import dede.srm.repo.interf.DeviceRepo;
import dede.srm.repo.interf.StorageRepo;
import dede.srm.repo.interf.StorageTypeRepo;
import dede.srm.service.interf.StorageService;
import dede.srm.util.ObjectCheckerUtil;
import dede.srm.util.PropertyUtil;
import dede.srm.util.TypeConvertionUtil;

import java.util.Optional;

@Service
public class ImplStorageService implements StorageService {

	@Autowired
	private StorageRepo storageRepo;
	
	@Autowired
	private DeviceRepo deviceRepo;
	
	@Override
	public Iterable<Storage> findAllStorage() {
		return storageRepo.findAll();
	}
	
	@Override
	public List<Storage> findAllStorageWithType() {
		return storageRepo.findAllStorageWithType();
	}


	@Override
	public Storage saveStorage(Storage storage) throws Exception {
		
		if(storage == null) {
			throw new NullPointerException();
		}
		
//		StorageType storageType = storage.getStorageType();
//		if(storageType == null) {
//			throw new NullPointerException();
//		}
		
		if(storage.getId() != null) {
			throw new PkIsEmptyException();
		}
		
		Date nowDate = new Date();
		storage.setCreateDate(nowDate);
		storage.setUpdateDate(nowDate);

		Storage storageEntity = storageRepo.save(storage);
		
		return storageEntity;
	}
	
	@Override
	public Storage replaceStorage(Storage storage) throws Exception {
		if(ObjectCheckerUtil.isObjectPkNull(storage, storage::getId)) {
			throw new NullPointerException();
		}
		
		Optional<Storage> storageOptional = storageRepo.findById(storage.getId());
		if(storageOptional.isEmpty()) {
			throw new NullPointerException();
		}
		
		Storage entityStorage = storageRepo.save(storage);
		return entityStorage;
	}
	
	@Override
	public Storage updateStorage(Storage storage) throws Exception{
		if(ObjectCheckerUtil.isObjectPkNull(storage, storage::getId)) {
			throw new NullPointerException();
		}
		
		Optional<Storage> storageOptional = storageRepo.findById(storage.getId());
		if(storageOptional.isEmpty()) {
			throw new NullPointerException();
		}
		
		Storage entityStorage = storageOptional.get();
		BeanUtils.copyProperties(storage, entityStorage, PropertyUtil.getNullPropertyArray(entityStorage));
		entityStorage = storageRepo.save(entityStorage);
		return entityStorage;
	}

	@Override
	@Transactional
	public void deleteStorage(Storage storage) throws Exception{
		if(ObjectCheckerUtil.isObjectPkNull(storage, storage::getId)) {
			throw new NullPointerException();
		}
		List<Device> deviceList = deviceRepo.findAllDeviceByStorage(storage.getId());
		for(Device device :deviceList) {
			device.setStorage(null);
		}
		deviceRepo.saveAll(deviceList);
//		detailService.deleteDetailByRef(Storage.class.getSimpleName(), storage.getId());
		storageRepo.delete(storage);
	}
	
	@Override
	@Transactional
	public void deleteStorageAndEverythingWithin(Storage storage) throws Exception {
		if(ObjectCheckerUtil.isObjectPkNull(storage, storage::getId)) {
			throw new NullPointerException();
		}
		List<Device> deviceList = deviceRepo.findAllDeviceByStorage(storage.getId());
		for(Device device : deviceList) {

		}
		
		deviceRepo.deleteAll(deviceList);

		storageRepo.delete(storage);
	}
	

	@Override
	public Storage findStorageById(Long storageId) throws Exception{
		if(storageId == null) {
			throw new NullPointerException();
		}
		Optional<Storage> storage = storageRepo.findById(storageId);
		return storage.get();
	}

	@Override
	public List<Storage> findStoragesByName() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

}
