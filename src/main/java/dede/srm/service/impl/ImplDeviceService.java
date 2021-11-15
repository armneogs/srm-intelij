package dede.srm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dede.srm.exception.PkIsEmptyException;
import dede.srm.models.Device;
import dede.srm.models.DeviceType;
import dede.srm.models.Storage;
import dede.srm.repo.interf.DeviceRepo;
import dede.srm.repo.interf.DeviceRepo;
import dede.srm.repo.interf.DeviceTypeRepo;
import dede.srm.repo.interf.StorageRepo;
import dede.srm.service.interf.DeviceService;
import dede.srm.util.ObjectCheckerUtil;
import dede.srm.util.PropertyUtil;

@Service
public class ImplDeviceService implements DeviceService {

	@Autowired
	private DeviceRepo deviceRepo;
	
	@Override
	public Iterable<Device> findAllDevice() {
		return deviceRepo.findAll();
	}
	
	@Override
	public List<Device> findAllDeviceWithType() {
		return deviceRepo.findAllDeviceWithType();
	}
	

	@Override
	public Device saveDevice(Device device) throws Exception {
		
		if(device == null) {
			throw new NullPointerException();
		}
		
		DeviceType deviceType = device.getDeviceType();
		if(deviceType == null) {
			throw new NullPointerException();
		}
		
		
		Date nowDate = new Date();
		device.setCreateDate(nowDate);
		device.setUpdateDate(nowDate);

		Device deviceEntity = deviceRepo.save(device);
		
		return deviceEntity;
	}
	
	@Override
	public Device replaceDevice(Device device) throws Exception {
		if(ObjectCheckerUtil.isObjectPkNull(device, device::getId)) {
			throw new NullPointerException();
		}
		
		Optional<Device> deviceOptional = deviceRepo.findById(device.getId());
		if(deviceOptional.isEmpty()) {
			throw new NullPointerException();
		}
		
		Device entityDevice = deviceRepo.save(device);
		return entityDevice;
	}
	
	@Override
	public Device updateDevice(Device device) throws Exception{
		if(ObjectCheckerUtil.isObjectPkNull(device, device::getId)) {
			throw new NullPointerException();
		}
		
		Optional<Device> deviceOptional = deviceRepo.findById(device.getId());
		if(deviceOptional.isEmpty()) {
			throw new NullPointerException();
		}
		
		Device entityDevice = deviceOptional.get();
		BeanUtils.copyProperties(device, entityDevice, PropertyUtil.getNullPropertyArray(entityDevice));
		entityDevice = deviceRepo.save(entityDevice);
		return entityDevice;
	}

	@Override
	@Transactional
	public void deleteDevice(Device device) throws Exception{
		if(ObjectCheckerUtil.isObjectPkNull(device, device::getId)) {
			throw new NullPointerException();
		}

		deviceRepo.delete(device);
	}
	
	
	@Override
	@Transactional
	public void deleteDeviceList(List<Device> deviceList) throws Exception {
//		deviceRepo.deleteAll(deviceList);
	}

	@Override
	public Iterable<Device> findDevicesByName(String deviceName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Device findDeviceById(Long DeviceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}




}
