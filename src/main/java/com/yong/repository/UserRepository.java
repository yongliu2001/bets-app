package com.yong.repository;

import com.yong.domain.BetRecord;
import com.yong.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by yongliu on 25/6/18.
 */
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUserName(String userName);
}
