package com.handey.web.member;


import java.util.List;
import java.util.Optional;

public interface MemberRepository{

    Member save(Member member);//회원이 저장소에 저장됨
    Optional<Member> findById(Long id);
    Optional<Member> findByUserIdAndPw(String email, String password);
    Optional <Member> findByUsername(String username);
    Optional <Member> findByUsernameAndPassword(String username, String password);
    List<Member> findAll();//저장된 모든 회원 list 반환
}
