package com.example.department.services.ministry;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "MINISTRY-SERVICE")
public interface MinistryRestClient {

    @GetMapping("/ministries")
    List<Ministry> findAllMinistries();

    @GetMapping("/ministries/{uuid}")
    Ministry findMinistryByUUID(@PathVariable String uuid);

}
