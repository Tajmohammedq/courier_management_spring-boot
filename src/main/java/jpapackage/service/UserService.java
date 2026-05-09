package jpapackage.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import jpapackage.repo.UserRepo;
import lombok.RequiredArgsConstructor;

public interface UserService {
	
	UserDetailsService userDetailsService();
}
