package com.junzeng.communtiy_01.dao;

import com.junzeng.communtiy_01.entity.LoginTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
@Deprecated
public interface LoginTicketMapper {

    @Select({
            "select id,user_id,ticket,status,expired ",
            "from login_ticket where ticket=#{ticket}"
    })
    LoginTicket selectByTicket(String ticker);

    @Update({
            "update login_ticket set status=#{status} ",
            "where ticket=#{ticket}"
    })
    int updateStatus(String ticket,int status);

    @Insert({
            "insert into login_ticket(user_id,ticket,status,expired) ",
            "values(#{userId},#{ticket},#{status},#{expired})"
    })
    int insertLoginTicket(LoginTicket loginTicket);

}
