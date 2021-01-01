package com.vano.repository;

import com.vano.entity.AddToCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddToCartRepository extends JpaRepository<AddToCart, Long> {
}
