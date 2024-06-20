package com.ou.components;

import com.ou.services.BillService;
import com.ou.services.CabinetService;
import com.ou.services.ContractSerivces;
import com.ou.services.RoomServices;
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
    private RoomServices roomServices;

    @Autowired
    private BillService billService;

    //0s 0m 7h daily monthly
    @Scheduled(cron = "0 0 7 * * ?")
    public void checkExpireContract() {
        this.roomServices.activateRoomAfterContractClose();
        this.cabinetService.closeExpiredContractCabinets();
    }

    @Scheduled(cron = "0 0 7 1 * ?")
    public void runCreateBill(){billService.createAllBill();}
}
