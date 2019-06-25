package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByLoginId(String loginId);
}
