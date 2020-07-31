package com.example.dubbo.provider.repository;
import com.example.dubbo.provider.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Stadpole on 2020/9/21.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    @Query
    UserInfo findByUsername(String username);
    @Query
    UserInfo findById(Integer id);
    @Query
    List<UserInfo> findByRole(String role);
    @Query
    List<UserInfo> findByJob(String job);
    @Query
    List<UserInfo> findByRoleAndJob(String role, String job);

}