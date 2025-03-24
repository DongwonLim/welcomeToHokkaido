package com.example.welcomeToHokkaido.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "member")
public class MemberEntity {

    @Id
    @Column(name = "member_id", nullable=false)
    private String memberId;

    @Column(name = "member_pw", nullable=false)
    private String memberPw;

    @Column(name = "member_address", nullable=false)
    private String memberAddress;

    @Column(name = "member_phone", nullable=false)
    private String memberPhone;

    @Column(name = "member_gender", nullable=false)
    private String memberGender;

    @Column(name = "member_email", nullable=false)
    private String memberEmail;

}
