package com.ou.services.impl;

import com.ou.pojo.Contract;
import com.ou.pojo.MemberInRoom;
import com.ou.repositories.MemberInRoomRepository;
import com.ou.services.MemberInRoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberInRoomServicesImpl implements MemberInRoomServices {
    @Autowired
    private MemberInRoomRepository memberInRoomRepository;

    @Override
    public List<MemberInRoom> getMemberInRooms(Contract c) {
        return this.memberInRoomRepository.getListOfMemberInRoom(c);
    }
}
