package com.jiang.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Jiang
 */
@Entity
@Table(name = "tb_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "salt")
    private String salt;
    @Column(name = "pass_word")
    private String passWord;
    @Column(name = "email")
    private String email;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "company_id")
    private String companyId;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "user_info_remark")
    private String userInfoRemark;
    @Column(name = "last_login_time")
    private Date lastLoginTime;
    @Column(name = "last_login_ip")
    private String lastLoginIp;
    @Column(name = "creater")
    private Integer creater;
}
