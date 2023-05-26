package first.firstspring.repository;

import first.firstspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null 가능성 있을경우 optional.ofnullable
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // stream 객체 생성
                .filter(member -> member.getName().equals(name)) // filter로 가공하기
                // 여기서는 member라는 객체에서 getName()이름을 가져온 후, name와 동일한 것 탐색
                .findAny(); // 필터링된 결과를 1개 찾으면 바로 Optional로 감싸서 반환

        // store 해쉬 맵의 값들을(.values()) Loop를 돌며(.stream()) 검사(.filter()), 넘어온 name과
        // 값이 같은 경우에이 필터링, 찾으면 반환(.findAny()), 없으면 null -> Optional로 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // new ArrayList<>(store.values()); : values가 모두 담긴 ArrayList 생성됨
    }

    public void clearStore(){
        store.clear();
    }
}
