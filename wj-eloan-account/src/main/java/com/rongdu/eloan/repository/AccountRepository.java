package com.rongdu.eloan.repository;

import com.rongdu.eloan.domain.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/9 14:03
 * @Since version 1.0.0
 */
public interface AccountRepository extends CrudRepository<AccountInfo,Long>,JpaSpecificationExecutor {
}
