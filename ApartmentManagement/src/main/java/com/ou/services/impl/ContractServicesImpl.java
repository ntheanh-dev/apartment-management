package com.ou.services.impl;

import com.ou.pojo.Contract;
import com.ou.pojo.Room;
import com.ou.repositories.ContractRepository;
import com.ou.services.ContractSerivces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServicesImpl implements ContractSerivces {
    @Autowired
    private ContractRepository contractRepository;
    @Override
    public Contract getContract(Room room) {
        return this.contractRepository.findContractByRoomID(room);
    }
}
