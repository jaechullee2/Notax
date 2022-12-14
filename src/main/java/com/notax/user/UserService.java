package com.notax.user;


import com.notax.vo.LoginVO;

import com.notax.vo.UserVO;
import lombok.AllArgsConstructor;
import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.util.HashMap;

import java.util.Collection;

import java.util.Map;
import java.util.Set;


@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserDAO userdao;

    public Map<String, String> validateHandling(Errors errors){
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
    //회원가입
    public void save(UserVO vo){
        userdao.userSave(vo);
    }
    //로그인
    public LoginVO login(@Valid LoginVO loginVO){
        return userdao.login(loginVO);

    }

    //아이디 중복체크
    public int checkId(String account) {
        int cnt = userdao.checkId(account);
         return cnt;
    }

}
