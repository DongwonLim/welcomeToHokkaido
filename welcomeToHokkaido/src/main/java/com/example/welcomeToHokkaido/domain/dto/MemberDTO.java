package com.example.welcomeToHokkaido.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    String memberId;
    String memberPw;
    String memberAddress;
    String memberGender;
    String memberPhone;
    String memberEmail;
}
