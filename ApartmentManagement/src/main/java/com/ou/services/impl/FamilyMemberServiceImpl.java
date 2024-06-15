package com.ou.services.impl;

import com.ou.dto.request.AddFamilyMemberRequest;
import com.ou.dto.request.RemoveFamilymemberRequest;
import com.ou.dto.response.FamilyMemberResponse;
import com.ou.mapper.FamilyMemberMapper;
import com.ou.repositories.FamilyMemberRepository;
import com.ou.services.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyMemberServiceImpl implements FamilyMemberService {

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    @Autowired
    private FamilyMemberMapper familyMemberMapper;

    @Override
    public List<FamilyMemberResponse> findAll() {
        var response  = this.familyMemberRepository.findAll();
        return this.familyMemberMapper.toFamilyMemberResponse(response);
    }

    @Override
    public void removeRelativesById(RemoveFamilymemberRequest ids) {
        this.familyMemberRepository.removeRelativesById(ids);
    }

    @Override
    public FamilyMemberResponse add(AddFamilyMemberRequest familyMember) {
        var request = this.familyMemberMapper.toFamilyMember(familyMember);
        var response = this.familyMemberRepository.add(request);
        return this.familyMemberMapper.toFamilyMemberResponse(response);
    }
}
