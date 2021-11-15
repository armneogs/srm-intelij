package dede.srm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dede.srm.models.Storage;
import dede.srm.service.interf.StorageService;
//import dede.srm.service.interf.Storage;

@RestController
@RequestMapping("/storage")
public class StorageController {
	@Autowired
	private StorageService storageService;


	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<?> getAllStorage() {
		try {
			List<Storage> storageList = storageService.findAllStorageWithType();
			return ResponseEntity.status(HttpStatus.OK).body(storageList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> addStorage (@RequestBody Storage storage){
		try {
			storage = storageService.saveStorage(storage);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	public ResponseEntity<?> updateStorage (@RequestBody Storage storage){
		try {
			storage = storageService.updateStorage(storage);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/replace", method = RequestMethod.PUT)
	public ResponseEntity<?> replaceStorage (@RequestBody Storage storage){
		try {
			storage = storageService.replaceStorage(storage);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStorage (@RequestBody Storage storage) {
		try {
			storageService.deleteStorage(storage);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStorageAndEvethingWithin (@RequestBody Storage storage) {
		try {
			storageService.deleteStorageAndEverythingWithin(storage);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
