package com.rongdu.eloan.domain;


import lombok.Data;
import org.hibernate.sql.Update;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/9 13:51
 * @Since version 1.0.0
 */
@Entity
@Data
@Table(name="account_tbl")
public class AccountInfo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @NotNull(groups = Update.class,message = "id不能为空")
    private Long id;

    @Column(name = "user_id")
    @NotBlank
    private String userId;

    @Column(name = "money")
    private int money;
}
