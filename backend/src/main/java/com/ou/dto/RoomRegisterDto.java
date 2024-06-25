package com.ou.dto;

import com.ou.pojo.Contract;
import com.ou.pojo.MemberInRoom;
import com.ou.pojo.Resident;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Getter
public class RoomRegisterDto {
    public RoomRegisterDto(int numberMem){
        listMember = new ArrayList<>();
        for(int i=0;i<=numberMem -1;i++){
            listMember.add(new Resident());
        }
    }
    public RoomRegisterDto(Contract contract,List<MemberInRoom> residentList,int numberMem){
        listMember = new ArrayList<>();
        for(int i=0;i<=numberMem - 1;i++){
            if(i <residentList.size()){
                listMember.add(residentList.get(i).getResidentUser());
            }else{
                listMember.add(new Resident());
            }
        }
        id = contract.getResidentUser().getId();
        fullName = contract.getResidentUser().getFullName();
        dateOfBirth = contract.getResidentUser().getDateOfBirth();
        city = contract.getResidentUser().getCity();
        ward = contract.getResidentUser().getWard();
        address = contract.getResidentUser().getAddress();
        gender = contract.getResidentUser().getGender();
        identity = contract.getResidentUser().getIdentity();
        phone = contract.getResidentUser().getPhone();
        email = contract.getResidentUser().getEmail();
        numberPlate = contract.getResidentUser().getNumberPlate();
        securityDeposit = contract.getSecurityDeposit();
        startedDate = contract.getStartedDate();
        totalMonth = contract.getTotalMonth();
        idContract = contract.getId();
    }
    private Integer id;
    private Integer idContract;
    @NotEmpty(message = "(*): Thông tin bắt buộc")
    private String fullName;
    private LocalDate dateOfBirth;
    private String city;
    private String ward;
    @NotEmpty(message = "(*): Thông tin bắt buộc")
    private String address;
    private Boolean gender;
    @NotEmpty(message = "(*): Thông tin bắt buộc")
    private String identity;
   @NotEmpty(message = "(*): Thông tin bắt buộc")
    private String phone;
    private String email;
    @NotNull(message = "(*): Thông tin bắt buộc")
    private String numberPlate;
    private long securityDeposit;
    private String startedDate;
    private Integer totalMonth;
    private List<Resident> listMember;
}
