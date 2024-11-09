package com.quanAoThoiTrang.services;

import com.quanAoThoiTrang.entities.Customer;
import com.quanAoThoiTrang.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Tìm kiếm khách hàng trong database theo email
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Trả về đối tượng User từ Spring Security, với thông tin khách hàng
        return User.builder()
                .username(customer.getEmail())
                .password(customer.getPassword())
                .roles("USER")  // Tùy chỉnh vai trò nếu cần
                .build();
    }
}
