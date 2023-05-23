package com.conference.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conference.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
