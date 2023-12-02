package study.datajpa.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }
    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member) {
        // domain converter는 위에처럼 Member의 데이터를 가져오는것을 자동으로 해준다.
        // 딱 조회용으로만 사용해야된다. 수정 변경 등록에는 사용 x
        return member.getUsername();
    }
    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size=5, sort="username") Pageable pagable) {
        //http://localhost:8080/members?page=0&size=3&sort= 로 호출하면 처음3개를 가져옴 사이즈를 안넣으면 20개를 가져옴
        Page<Member> page = memberRepository.findAll(pagable);
        Page<MemberDto> map = page.map(member-> new MemberDto(member.getId(), member.getUsername(),null));
        return map;
    }
    //@PostConstruct
    public void init() {
        //테스트 데이터 저장
        for(int i = 0; i<100;i++) {
            memberRepository.save(new Member("user" + i, i));
        }
    }
}
