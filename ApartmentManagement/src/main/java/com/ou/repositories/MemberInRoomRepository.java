package com.ou.repositories;

import com.ou.pojo.Contract;
import com.ou.pojo.MemberInRoom;
import com.ou.pojo.Receipt;
import com.ou.pojo.Resident;

import java.util.List;

public interface MemberInRoomRepository {
    void addMemberInRoom(MemberInRoom memberInRoom);
    List<MemberInRoom> getListOfMemberInRoom(Contract contract);
    boolean checkExistence(Contract contract, Resident resident);
}
