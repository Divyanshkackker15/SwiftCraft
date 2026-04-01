package in.divyansh.repository;
import in.divyansh.entity.*;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrderRepository extends JpaRepository<Order, Long> {
    //Returns List of Order of a specific user in desc order of Date
	List<Order> findByUserIdOrderByOrderDateDesc(Long userId);
	
	//Return Orders of a specific user based on Pagination
	Page<Order> findByUserId(Long userId,Pageable pageable);
	
	//List of orders based on status
	List<Order>findByStatus(String status);
	
	//Returns Order with items on the basis orderid
	@Query("Select o From Order o"+
	        "LEFT JOIN FETCH o.orderItems oi"+
			"LEFT JOIN FETCH oi.products "+
	        "Where o.id=:orderId"
			)
	Optional<Order> findByIdWithItems(@Param("orderId") Long orderId);
	
	//Returns Order with items on the basis ordernumber
		
	
	@Query("Select o From Order o"+
		        "LEFT JOIN FETCH o.orderItems oi"+
				"LEFT JOIN FETCH oi.products "+
		        "Where o.orderNumber=:orderNumber"
				)
		Optional<Order> findByOrderNumberWithItems(@Param("orderId") String orderNumber);
	
	@Query("SELECT o FROM Order o.orderNumber like %:keyword%"+
            "OR o.user.fullName like  %:keyword%"+
			"OR o.user.email like %:keyword%")
    public Page<Order> searchOrders(@Param("keyword")String keyword,Pageable pageable);
}
