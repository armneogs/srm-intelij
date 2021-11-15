package dede.srm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dede.srm.models.Device;
import dede.srm.service.interf.DeviceService;

@RestController
@RequestMapping("/device")
public class DeviceContoller {

	
	@Autowired
	private DeviceService deviceService;


	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<?> getAllDevice() {
		try {
			List<Device> deviceList = deviceService.findAllDeviceWithType();
			return ResponseEntity.status(HttpStatus.OK).body(deviceList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> addDevice (@RequestBody Device device){
		try {
			device = deviceService.saveDevice(device);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	public ResponseEntity<?> updateDevice (@RequestBody Device device){
		try {
			device = deviceService.updateDevice(device);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/replace", method = RequestMethod.PUT)
	public ResponseEntity<?> replaceDevice (@RequestBody Device device){
		try {
			device = deviceService.replaceDevice(device);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteDevice (@RequestBody Device device) {
		try {
			deviceService.deleteDevice(device);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
