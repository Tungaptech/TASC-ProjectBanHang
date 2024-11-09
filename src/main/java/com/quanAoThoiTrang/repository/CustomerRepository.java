package com.quanAoThoiTrang.repository;

import com.quanAoThoiTrang.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Tìm kiếm customer theo email
    Optional<Customer> findByEmail(String email);

    // Kiểm tra email có tồn tại không
    boolean existsByEmail(String email);
}
