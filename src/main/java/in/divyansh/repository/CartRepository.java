package in.divyansh.repository;


import in.divyansh.entity.*;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CartRepository extends JpaRepository<Cart, Long> {
	
	
	Optional<Cart> findByUserId(Long userId);
	
	
	boolean existsByUserId(Long userId);
	
	
	void deleteByUserId(Long userId);
	@Query("Select c From Cart c"+
	        "LEFT JOIN FETCH c.cartItems ci"+
			"LEFT JOIN FETCH ci.products "+
	        "Where c.user.id=:userId"
			)
	
	Optional<Cart> findByUserIdWithItems(@Param("userId")Long userId);
	

}
