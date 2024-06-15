package com.ou.repositories;

import com.ou.pojo.Resident;

public interface ResidentRepository {
    void addResident(Resident resident);
    Resident getResidentById(int id);
    Resident getResident();

}
