package com.guardx.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guardx.auth_service.model.UserLogs;

@Repository
public interface UserLogsRepository extends JpaRepository<UserLogs, Long>{

}
