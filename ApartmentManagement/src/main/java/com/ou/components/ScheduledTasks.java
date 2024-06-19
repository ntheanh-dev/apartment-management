package com.ou.components;

import com.ou.services.BillService;
import com.ou.services.CabinetService;
import com.ou.services.ContractSerivces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduledTasks {

    @Autowired
    private CabinetService cabinetService;

    @Autowired
    private BillService billService;

    @Scheduled(cron = "0 0 7 * * ?")
    public void checkExpireContract() {
        this.cabinetService.closeExpiredContractCabinets();
    }

    @Scheduled(cron = "0 0 7 1 * ?")
    public void runCreateBill(){billService.createAllBill();}
}
