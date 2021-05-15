package com.bl4kee.coronavirustracker.controllers;

import com.bl4kee.coronavirustracker.entities.LocationStats;
import com.bl4kee.coronavirustracker.services.CoronavirusDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/coronavirus")
public class CoronavirusController {

    private final CoronavirusDataService coronavirusDataService;

    @GetMapping("/")
    public List<LocationStats> fetchCoronavirusData() {
        return coronavirusDataService.getAllStats();
    }
}
