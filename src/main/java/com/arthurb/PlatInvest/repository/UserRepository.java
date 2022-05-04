package com.arthurb.PlatInvest.repository;

import com.arthurb.PlatInvest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(String cpf);
}
