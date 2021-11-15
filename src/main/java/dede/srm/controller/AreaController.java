package dede.srm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dede.srm.models.AreaT;
import dede.srm.service.interf.RoomService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/room")
public class AreaController {

	
	@Autowired
	private RoomService roomService;


	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<?> getAllRoom() {
		try {
			List<AreaT> roomList = roomService.findAllRoomWithType();
			return ResponseEntity.status(HttpStatus.OK).body(roomList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> addRoom (@RequestBody AreaT room){
		try {
			room = roomService.saveArea(room);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	public ResponseEntity<?> updateRoom (@RequestBody AreaT room){
		try {
			room = roomService.updateRoom(room);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/replace", method = RequestMethod.PUT)
	public ResponseEntity<?> replaceRoom (@RequestBody AreaT room){
		try {
			room = roomService.replaceArea(room);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRoom (@RequestBody AreaT room) {
		try {
			roomService.deleteRoom(room);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRoomAndEvethingWithin (@RequestBody AreaT room) {
		try {
			roomService.deleteRoomAndEverythingWithin(room);
			return ResponseEntity.status(HttpStatus.OK).body("suc");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
