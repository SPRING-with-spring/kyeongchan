package springschool.ranking.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springschool.ranking.member.domain.Member;
import springschool.ranking.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository repository;

    @Override
    public void register(Member member) {
        repository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return repository.findById(memberId);
    }

    /**
     * @return null이면 로그인 실패
     */
    @Override
    public Member login(String userId, String password) {
        return repository.findByUserId(userId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    @Override
    public void logout() {

    }
}
