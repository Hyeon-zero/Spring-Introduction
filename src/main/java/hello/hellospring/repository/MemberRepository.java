package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 정보가 저장소에 저장
    Optional<Member> findById(Long id); // 회원 아이디
    Optional<Member> findByName(String name); // 회원 이름
    List<Member> findAll();
}
/*
    Java8에서는 Optional<T> 클래스를 사용해 NPE(NullPointerException)를 방지할 수 있도록 도와준다.
    Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다.
 */