package first.firstspring.service;

import first.firstspring.domain.Member;
import first.firstspring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test // test는 한글로 입력해도 됨
    void 회원가입() {

        //Given
        Member member = new Member();
        member.setName("hello");

        //When
        Long saveId = memberService.join(member);

        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);
        // command + option + v
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
         assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //
        //        try{
        //            memberService.join(member2);
        //            fail("예외가 발생해야 합니다");
        //        } catch (IllegalStateException e){
        //            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //        }

        //then
    }



    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}