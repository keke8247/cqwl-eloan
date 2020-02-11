package com.rongdu.eloan.service.impl;

import com.rongdu.eloan.domain.AccountInfo;
import com.rongdu.eloan.repository.AccountRepository;
import com.rongdu.eloan.service.AccountService;
import com.rongdu.eloan.service.dto.AccountDTO;
import com.rongdu.eloan.service.mapper.AccountMapper;
import com.rongdu.eloan.service.mapper.AccountMybatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/9 14:08
 * @Since version 1.0.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true,rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountMybatisMapper accountMybatisMapper;

    @Override
    public AccountDTO findById(Long id) {
        Optional<AccountInfo> account = accountRepository.findById(id);
        return accountMapper.toDto(account.get());
    }

    @Override
    public AccountDTO create(AccountInfo resources) {
        return accountMapper.toDto(accountRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(AccountDTO resources) {
        return accountMybatisMapper.updateByUserId(resources);
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDTO> queryAccounts() {
        return accountMybatisMapper.queryAccounts();
    }
}
