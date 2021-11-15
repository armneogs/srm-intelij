package dede.srm.service.interf;

import java.util.List;

import org.springframework.stereotype.Service;

import dede.srm.models.Device;

@Service
public interface DeviceService {
	public Iterable<Device> findAllDevice();
	public List<Device> findAllDeviceWithType();
	public Device saveDevice(Device device) throws Exception;
	public Device replaceDevice(Device device) throws Exception;
	public Device updateDevice(Device device) throws Exception;
	public void deleteDevice(Device device) throws Exception;
	public void deleteDeviceList(List<Device> deviceList) throws Exception;
	
	
	public Device findDeviceById(Long deviceId) throws Exception;
	public Iterable<Device> findDevicesByName(String deviceName) throws Exception;
	
	
}
