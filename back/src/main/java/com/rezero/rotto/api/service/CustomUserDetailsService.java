//package com.rezero.rotto.api.service;
//
//import com.rezero.rotto.dto.dto.CustomUserDetailsDto;
//import com.rezero.rotto.entity.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import com.rezero.rotto.repository.UserRepository;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
//        User user = userRepository.findByUserCode(Integer.parseInt(userCode));
//
//        return new CustomUserDetailsDto(user);
//    }
//
//}
