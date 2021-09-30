package com.handey.web.repository.join;

import com.handey.web.domain.join.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByUsernameAndPassword(String username, String password) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}