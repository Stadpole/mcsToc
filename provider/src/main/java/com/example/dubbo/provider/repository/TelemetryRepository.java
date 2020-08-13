package com.example.dubbo.provider.repository;
import com.example.dubbo.provider.entity.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Stadpole on 2020/9/21.
 */
public interface TelemetryRepository extends JpaRepository<Telemetry, String> {

}