package com.ou.services;

import com.ou.pojo.Room;

import java.util.List;
import java.util.Map;

public interface RoomServices {
    void addOrUpdateRoom(Room room);
    List<Room> getAllRooms(Map<String, String> params);
    Room getRoomById(int id);
    void deleteRoomById(int id);
}
