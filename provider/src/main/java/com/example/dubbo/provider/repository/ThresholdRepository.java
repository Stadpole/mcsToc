package com.example.dubbo.provider.repository;
import com.example.dubbo.provider.entity.Threshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Stadpole on 2020/9/21.
 */
public interface ThresholdRepository extends JpaRepository<Threshold, String> {
    @Query
    Threshold findById(Integer id);
}