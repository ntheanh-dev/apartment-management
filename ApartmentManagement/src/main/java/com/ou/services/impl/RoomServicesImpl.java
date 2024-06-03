package com.ou.services.impl;

import com.ou.pojo.Room;
import com.ou.repositories.RoomRepository;
import com.ou.services.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoomServicesImpl implements RoomServices {

    @Autowired
    private RoomRepository roomRepository;
    @Override
    public void addOrUpdateRoom(Room room) {
        if(room.getId() == null){
            room.setStatus("còn trống");
        }
        this.roomRepository.addOrUpdateRoom(room);
    }

    @Override
    public List<Room> getAllRooms(Map<String, String> params) {
        return this.roomRepository.getAllRooms(params);
    }

    @Override
    public Room getRoomById(int id) {
        return this.roomRepository.getRoomById(id);
    }

    @Override
    public void deleteRoomById(int id) {
        Room room = this.roomRepository.getRoomById(id);
        this.roomRepository.deleteRoomById(room);
    }
}
