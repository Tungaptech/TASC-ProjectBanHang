package com.quanAoThoiTrang.services;

import com.quanAoThoiTrang.dto.SignupRequest;
import com.quanAoThoiTrang.entities.Customer;
import com.quanAoThoiTrang.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createCustomer(SignupRequest signupRequest) {
        // Kiểm tra xem email đã tồn tại chưa
        if (customerRepository.existsByEmail(signupRequest.getEmail())) {
            return false; // Nếu email đã tồn tại, trả về false
        }

        // Tạo đối tượng Customer mới từ SignupRequest
        Customer customer = new Customer();
        customer.setEmail(signupRequest.getEmail());
        customer.setName(signupRequest.getName());

        // Mã hóa mật khẩu trước khi lưu vào DB
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        customer.setPassword(encodedPassword);

        // Lưu vào cơ sở dữ liệu
        customerRepository.save(customer);

        return true; // Đăng ký thành công
    }
}
