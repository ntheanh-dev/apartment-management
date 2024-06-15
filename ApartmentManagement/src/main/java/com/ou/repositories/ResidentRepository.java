package com.ou.repositories;

import com.ou.pojo.Resident;

import java.util.List;

public interface ResidentRepository {
    void addResident(Resident resident);
    List<Resident> getAllResidents();
}
