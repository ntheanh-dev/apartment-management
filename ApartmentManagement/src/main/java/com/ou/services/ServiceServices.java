package com.ou.services;

import com.ou.pojo.Service;

import java.util.List;

public interface ServiceServices {
    List<Service> getServices();
    Service getServiceById(int id);
    void addService(Service service);
}
