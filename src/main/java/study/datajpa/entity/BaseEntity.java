package study.datajpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity{ // DataJpaApplication파일에 @EnableJpaAuditing넣고 쓸수있다.

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy // DataJpaApplication파일에 public AuditorAware<String> auditorProvider() 넣고 쓸수있다.
    private String lastModifiedBy;

}
