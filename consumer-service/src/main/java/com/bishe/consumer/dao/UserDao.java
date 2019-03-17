package com.bishe.consumer.dao;


import com.bishe.consumer.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getOneById(Long id);

    User getOneByOpenId(String openId);

    User getOneByNickName(String nickName);

    boolean insertOne(@Param("user") User user);

    boolean deleteOne(Long id);

    boolean deleteOneByOpenId(String openId);

    Boolean update(@Param("user")User user);

}
