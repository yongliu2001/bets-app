package com.yong.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by yongliu on 26/6/18.
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "USER_TB")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;


    @JsonProperty("username")
    @NotNull
    private String userName;

    @JsonProperty("password")
    @NotNull
    private String password;

    public User() {
    }

    public User(@NotNull String userName, @NotNull String password) {
        this.userName = userName;
        this.password = password;
    }
}
