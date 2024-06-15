package com.ou.repositories;

import com.ou.dto.request.RemoveFamilymemberRequest;
import com.ou.pojo.FamilyMember;

import java.util.List;

public interface FamilyMemberRepository {
    List<FamilyMember> findAll();
    void removeRelativesById(RemoveFamilymemberRequest ids);
    FamilyMember add(FamilyMember familyMember);
}
