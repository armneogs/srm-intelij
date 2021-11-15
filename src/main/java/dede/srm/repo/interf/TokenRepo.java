package dede.srm.repo.interf;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dede.srm.models.Device;
import dede.srm.models.Token;

public interface TokenRepo  extends CrudRepository<Token, Long> {
    @Query("SELECT tk FROM Token tk "
    		+ "WHERE tk.user.id = ?1") 
    public List<Token> findByUserId(Long userId);
    
    @Query("SELECT tk FROM Token tk "
    		+ "WHERE tk.user.username = ?1 and tk.jwtHash = ?2") 
    public List<Token> findByUserNameAndJWT(String userName, String jwtHash);
    
}
