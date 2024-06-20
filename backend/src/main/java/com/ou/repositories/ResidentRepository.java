package com.ou.repositories;

import com.ou.pojo.Resident;
import com.ou.pojo.User;

import java.util.List;

public interface ResidentRepository {
    void addResident(Resident resident);
    List<Resident> getAllResidents();
    Resident getResidentById(int id);
    Resident getResident();
    void changeAvatar(Resident resident);

}
