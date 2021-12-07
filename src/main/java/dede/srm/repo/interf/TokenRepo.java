package dede.srm.repo.interf;

import java.util.List;

import dede.srm.models.UserToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepo  extends CrudRepository<UserToken, Long> {
    @Query("SELECT tk FROM UserToken tk "
    		+ "WHERE tk.user.id = ?1") 
    public List<UserToken> findByUserId(Long userId);
    
    @Query("SELECT tk FROM UserToken tk "
    		+ "WHERE tk.user.username = ?1 and tk.jwtHash = ?2") 
    public List<UserToken> findByUserNameAndJWT(String userName, String jwtHash);
    
}
