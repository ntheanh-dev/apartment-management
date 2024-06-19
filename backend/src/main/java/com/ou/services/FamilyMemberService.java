package com.ou.services;

import com.ou.dto.request.AddFamilyMemberRequest;
import com.ou.dto.request.RemoveFamilymemberRequest;
import com.ou.dto.response.FamilyMemberResponse;

import java.util.List;

public interface FamilyMemberService {
    List<FamilyMemberResponse> findAll();
    void removeRelativesById(RemoveFamilymemberRequest ids);
    FamilyMemberResponse add(AddFamilyMemberRequest familyMember);
}
