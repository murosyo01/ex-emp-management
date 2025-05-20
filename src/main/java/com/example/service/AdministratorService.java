package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理者リポジトリを操作するサービス
 */
@Service
@Transactional
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * 管理者に登録する
     * @param administrator 管理者オブジェクト
     */
    public void insert(Administrator administrator){
        administratorRepository.insert(administrator);
    }

    /**
     * メアドとパスワードがら管理者を検索する
     * @param mailAddress メアド
     * @param password パスワード
     * @return 一番最初に検索できた雇用者オブジェクト
     */
    public Administrator login(String mailAddress, String password) {
        return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
    }
}
