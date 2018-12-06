package com.jiang.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jiang
 * @date 2018/12/5
 * @time 12:14
 */
public interface UserJpa extends JpaRepository<UserTest,Integer> {
}
