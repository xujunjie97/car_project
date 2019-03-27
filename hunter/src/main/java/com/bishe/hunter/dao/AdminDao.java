package com.bishe.hunter.dao;

import com.bishe.hunter.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xujunjie
 */
@Repository
public interface AdminDao {

    boolean insertOne(@Param("admin") Admin admin);

    boolean deleteOne(String adminNum);

    Admin getOne(String adminNum);



}
