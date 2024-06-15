package com.ou.controllers;

import com.ou.dto.request.AddFamilyMemberRequest;
import com.ou.dto.request.RemoveFamilymemberRequest;
import com.ou.dto.response.ApiResponse;
import com.ou.dto.response.FamilyMemberResponse;
import com.ou.services.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/relatives")
public class ApiFamilyMemberController {

    @Autowired
    private FamilyMemberService familyMemberService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<List<FamilyMemberResponse>> getFamilyMember() {
        var response = this.familyMemberService.findAll();
        return ApiResponse.<List<FamilyMemberResponse>>builder()
                .result(response)
                .build();
    }

    @PostMapping(path = "/",consumes = {
            MediaType.APPLICATION_JSON_VALUE,
    },produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Void> removeRelatives(
            @RequestBody RemoveFamilymemberRequest ids
    ) {
        familyMemberService.removeRelativesById(ids);
        return ApiResponse.<Void>builder()
                .build();
    }

    @PostMapping(path = "/add/",consumes = {
            MediaType.APPLICATION_JSON_VALUE,
    },produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<FamilyMemberResponse> createRelative(
            @RequestBody AddFamilyMemberRequest memberRequest
    ) {
        System.out.println(memberRequest.getName());
        System.out.println(memberRequest.getDob());
        System.out.println(memberRequest.getRelationshipType());
        var response = this.familyMemberService.add(memberRequest);
        return ApiResponse.<FamilyMemberResponse>builder()
                .result(response)
                .build();
    }
}
