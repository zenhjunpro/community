package com.junzeng.communtiy_01.dao;

import com.junzeng.communtiy_01.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectById(int id);

    User selectByName(String name);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id,int status);

    int updatePassword(int id,String password);

    int updateHeader(int id,String headerUrl);
}
