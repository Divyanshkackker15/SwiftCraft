package in.divyansh.repository;
import in.divyansh.*;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.divyansh.entity.*;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    Optional<CartItem>findByCartIdAndProductId(Long cartId,Long productId);
    boolean existByCartIdAndProductId(Long cartId,Long productId);
}
