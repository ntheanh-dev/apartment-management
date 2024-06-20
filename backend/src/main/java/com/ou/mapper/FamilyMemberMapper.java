package com.ou.mapper;

import com.ou.dto.request.AddFamilyMemberRequest;
import com.ou.dto.response.FamilyMemberResponse;
import com.ou.pojo.FamilyMember;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // được quản lý boi spring theo DI
public interface FamilyMemberMapper {
    List<FamilyMemberResponse> toFamilyMemberResponse(List<FamilyMember> familyMembers);
    FamilyMemberResponse toFamilyMemberResponse(FamilyMember familyMember);
    FamilyMember toFamilyMember(AddFamilyMemberRequest familyMemberResponse);
}
