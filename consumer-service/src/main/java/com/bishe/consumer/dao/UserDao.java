package com.bishe.comsumer.dao;

import com.bishe.comsumer.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getOneById(Long id);

    User getOneByNicName(String nicName);

    boolean insertOne(@Param("user") User user);

    boolean deleteOne(Long id);

    Boolean update(@Param("user")User user);
}
