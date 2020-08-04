package com.example.dubbo.provider.repository;
import com.example.dubbo.provider.entity.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stadpole on 2020/9/21.
 */
public interface OperationLogRepository extends JpaRepository<OperationLog, String> {

}