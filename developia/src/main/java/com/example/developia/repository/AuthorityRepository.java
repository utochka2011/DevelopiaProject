package com.example.developia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.developia.entity.AuthorityEntity;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
}
