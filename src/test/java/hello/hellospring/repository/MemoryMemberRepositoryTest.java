package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { // callBack method
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("장현영");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        Member member3 = new Member();
        member3.setName("Spring1");
        repository.save(member3);

        Member member4 = new Member();
        member4.setName("Spring2");
        repository.save(member4);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}