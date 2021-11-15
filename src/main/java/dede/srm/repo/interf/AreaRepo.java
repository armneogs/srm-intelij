package dede.srm.repo.interf;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dede.srm.models.AreaT;

public interface AreaRepo extends CrudRepository<AreaT, Long> {
    @Query("SELECT r FROM AreaT r JOIN FETCH r.areaType ")
    public List<AreaT> findAllRoomWithType();
}
