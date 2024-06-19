package com.ou.repositories;

import com.ou.pojo.Room;

import java.util.List;
import java.util.Map;

public interface RoomRepository{
    void addOrUpdateRoom(Room room);
    List<Room> getAllRooms(Map<String, String> params);
    Room getRoomById(int id);
    void deleteRoomById(Room room);
    void updateStatus(Room room);
    Long getRoomCount();
    void activateRoomAfterContractClose();
}
