package com.DemoSocialMediaApplication.respository;

import com.DemoSocialMediaApplication.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDetail, UUID> {

    @Query("select u from UserDetail u where u.id=:id")
    public Optional<UserDetail> findById(UUID id);

    @Query("select u from UserDetail u where u.email=:email")
    public Optional<UserDetail> findByUserEmail(String email);
}
