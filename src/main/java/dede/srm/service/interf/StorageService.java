package dede.srm.service.interf;

import java.util.List;

import dede.srm.models.AreaT;
import dede.srm.models.Storage;

public interface StorageService {
	public Iterable<Storage> findAllStorage();
	public List<Storage> findAllStorageWithType();
	public Storage saveStorage(Storage storage) throws Exception;
	public Storage replaceStorage(Storage storage) throws Exception;
	public Storage updateStorage(Storage storage) throws Exception;
	public void deleteStorage(Storage storage) throws Exception;
	public void deleteStorageAndEverythingWithin(Storage storage) throws Exception;
	
	public Storage findStorageById(Long storageId) throws Exception;
	public Iterable<Storage> findStoragesByName() throws Exception;
}
