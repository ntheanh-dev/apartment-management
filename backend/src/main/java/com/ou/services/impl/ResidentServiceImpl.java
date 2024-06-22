package com.ou.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.dto.response.ResidentResponse;
import com.ou.exception.AppException;
import com.ou.exception.ErrorCode;
import com.ou.mapper.ResidentMapper;
import com.ou.pojo.Resident;
import com.ou.pojo.User;
import com.ou.repositories.ResidentRepository;
import com.ou.repositories.UserRepository;
import com.ou.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private ResidentMapper residentMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary  cloudinary;

    @Override
    public ResidentResponse getResident() {
        var resident = residentRepository.getResident();
        var response = residentMapper.toResidentResponse(resident);
        response.setId(resident.getUser().getId());
        return response;
    }

    @Override
    public String changeAvatar(MultipartFile[] file) {
        if(file.length ==0){
            throw new AppException(ErrorCode.INVALID_FILE);
        }
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.getUserByUsername(username);

        Resident r = residentRepository.getResidentById(user.getId());

        try {
            Map res = this.cloudinary.uploader().upload(file[0].getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            String url = res.get("secure_url").toString();
            r.setAvatar(url);
            this.residentRepository.changeAvatar(r);
            return url;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw new AppException(ErrorCode.CANNOT_UPLOAD_FILE);
        }
    }

    @Override
    public List<Resident> getAllResidents() {
        return this.residentRepository.getAllResidents();
    }
}
