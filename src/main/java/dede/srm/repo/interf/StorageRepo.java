package dede.srm.repo.interf;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dede.srm.models.Storage;

public interface StorageRepo extends CrudRepository<Storage, Long> {
    @Query("SELECT st FROM Storage st JOIN FETCH st.storageType ")
    public List<Storage> findAllStorageWithType();
    
    @Query("SELECT st FROM Storage st JOIN FETCH st.storageType "
    		+ "WHERE st.room.id = ?1 ")
    public List<Storage> findAllStorageByRoom(Long roomId);
    
    
}
