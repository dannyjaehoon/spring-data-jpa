package study.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username", "age"})
public class Member extends JpaBaseEntity{

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private Team team;

    //protected Member() {} // jpa를 사용할때 기본생성자가 필수임. 최소 protected로 해야되는이유는 jpa가 프록시 기술을 사용할때 객체를 만들어내고 해야됨.
    public Member(String username) {
        this.username = username;
    }
    public Member(String username,int age) {
        this.username = username;
        this.age = age;
    }
    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) changeTeam(team);
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
