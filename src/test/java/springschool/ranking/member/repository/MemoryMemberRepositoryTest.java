package springschool.ranking.member.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springschool.ranking.member.domain.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class MemoryMemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        if (memberRepository instanceof MemoryMemberRepository) {
            ((MemoryMemberRepository) memberRepository).clearStore();
        }
    }

    @Test
    void save() {
        // given
        Member member = new Member();
        member.setName("memberA");
        member.setUserId("abc");
        member.setPassword("aaaaaaaa");

        // when
        memberRepository.save(member);

        // then
        log.info("member id={}", member.getId());
        Member findMember = memberRepository.findById(member.getId());

        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void findById() {

    }

    @Test
    void findByUserId() {
        // given
        Member member1 = new Member();
        member1.setName("memberA");
        member1.setUserId("abc");
        member1.setPassword("aaaaaaaa");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("memberB");
        member2.setUserId("bbb");
        member2.setPassword("aaaaaaa");
        memberRepository.save(member2);

        // when
        Optional<Member> noneMember = memberRepository.findByUserId("aaa");
        Optional<Member> findMember = memberRepository.findByUserId("abc");

        // then
        log.info("noneMember={}", noneMember);
        assertThat(noneMember).isEmpty();

        log.info("findMember={}", findMember);
        assertThat(findMember.get()).isEqualTo(member1);

    }

    @Test
    void findAll() {

        // given
        Member member1 = new Member();
        member1.setName("memberA");
        member1.setUserId("abc");
        member1.setPassword("aaaaaaaa");

        Member member2 = new Member();
        member2.setName("memberB");
        member2.setUserId("bbb");
        member2.setPassword("aaaaaaa");

        // when
        memberRepository.save(member1);
        memberRepository.save(member2);

        // then
        List<Member> memberList = memberRepository.findAll();
        log.info("member List={}", memberList);
        assertThat(memberList.size()).isEqualTo(2);
        assertThat(memberList).containsExactly(member1, member2);
    }
}