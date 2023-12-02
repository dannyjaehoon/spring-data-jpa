package study.datajpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item implements Persistable<String> {
    // id가 @GeneratedValue로 쓰이면 괜찮지만 아래와 같이 스트링으로 받는경우에 Spring data jpa가 주는 save를 사용할때 isNew 함수에서 엔티티가 있다고 판별됨
    // 엔티티가 있으면 persist가 아니라 merge로 데이터를 업데이트 하는데 merge는 detached된 애들을 다시 영속성 컨텍스트로 연결해 줄대만 사용함
    // 따라서 아래와 같이 isNew를 오버라이드 받아서 다시 사용해야됨
    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    public Item(String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() { //save 함수를 위해서 jpa
        return createdDate == null;
    }

}
