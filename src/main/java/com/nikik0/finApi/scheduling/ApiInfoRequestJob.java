package com.nikik0.finApi.scheduling;

import com.nikik0.finApi.services.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiInfoRequestJob {

    private final CompanyService companyService;

    //@Scheduled(fixedRate = 60*60*1000)
    public void requestDataFromApi(){
        companyService.getCompanies4();
    }

    @Scheduled(fixedRate = 60*60*1000)
    public void requestDataFromFinIoApi(){
        companyService.getCompaniesFin();
    }
}
