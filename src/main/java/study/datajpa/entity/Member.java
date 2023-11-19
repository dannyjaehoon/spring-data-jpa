package study.datajpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    protected Member() { // jpa를 사용할때 기본생성자가 필수임. 최소 protected로 해야되는이유는 jpa가 프록시 기술을 사용할때 객체를 만들어내고 해야됨.
    }

    public Member(String username) {
        this.username = username;
    }

    public void changeUsername(String username) {
        this.username = username;
    }
}
