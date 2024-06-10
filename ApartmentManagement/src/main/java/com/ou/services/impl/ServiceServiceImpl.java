package com.ou.services.impl;

import com.ou.pojo.Service;
import com.ou.repositories.ServiceReposity;
import com.ou.services.ServiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceServices {

    @Autowired
    private ServiceReposity serviceReposity;
    @Override
    public List<Service> getServices() {
        return serviceReposity.getServices();
    }
}
