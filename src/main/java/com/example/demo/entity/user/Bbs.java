package com.example.demo.entity.user;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Bbs extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임
    private long id;

    private String Title;

    @ManyToOne
    @JoinColumn(name = "user_id") // FK
    private User user;

    private String Content;

    private String Datetime;    // 자동생성된 datetime 'T' 제거를 위한 필드

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // 기본적으로 Lazy 로 설정
    @JoinColumn(name = "bbs_id")    // 양방향 매핑 (지연 로딩 문제)
    @ToString.Exclude   // toString() 결과에서 제외
    private List<Comment> comments = new ArrayList<>();

}