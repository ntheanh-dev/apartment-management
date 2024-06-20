package com.ou.services;

import com.ou.dto.response.ResidentResponse;
import com.ou.pojo.Resident;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResidentService {
    ResidentResponse getResident();
    String changeAvatar(MultipartFile[] file);
    List<Resident> getAllResidents();

}
