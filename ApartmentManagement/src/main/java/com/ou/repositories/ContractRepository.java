package com.ou.repositories;

import com.ou.pojo.Contract;
import com.ou.pojo.Room;

public interface ContractRepository {
    void addContract(Contract contract);
    Contract findContractByRoomID(Room roomId);
}
