package com.java.basic.setup.domain.player.entity;


import com.java.basic.setup.domain.player.dto.CreateUserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id; // 이 부분을 수정하세요
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
//@Data 는 양방향 관계일때 toString에 StackOverFlow가 발생
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue
    @Column(name = "Player_id")
    private Long id;

    // 플레이어 로그인 ID
    @Column(nullable = false)
    private String userid;

    // 플레이어 로그인 비밀번호
    @Column(nullable = false)
    private String password;

    private Integer age;

    public void setCreatePlayer(CreateUserDto dto){
        this.userid = dto.getUserid();
        this.password = dto.getPassword();
        this.age = dto.getAge();
    }
}
