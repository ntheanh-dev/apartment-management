package com.ou.services;

import com.ou.pojo.Contract;
import com.ou.pojo.MemberInRoom;

import java.util.List;

public interface MemberInRoomServices {
    List<MemberInRoom> getMemberInRooms(Contract c);
}
