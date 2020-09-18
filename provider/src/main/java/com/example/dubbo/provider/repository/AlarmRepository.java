package com.example.dubbo.provider.repository;

import com.example.dubbo.provider.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Stadpole on 2020/9/21.
 */
public interface AlarmRepository extends JpaRepository<Alarm, String> {
    @Query
    Alarm findById(Integer Id);

}