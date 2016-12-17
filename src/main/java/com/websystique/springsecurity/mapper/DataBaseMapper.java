
package com.websystique.springsecurity.mapper;

import com.websystique.springsecurity.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface DataBaseMapper {
    
    @Select("select * from vk_messenger.users where login=#{login}")
    User findUserByLogin(@Param("login")String login);
    
    @Select("SELECT type FROM vk_messenger.users left join vk_messenger.user_profile " +
    "on vk_messenger.user_profile.user_id = vk_messenger.users.id " +
    "left join vk_messenger.profile " +
    "on vk_messenger.profile.id = vk_messenger.user_profile.profile_id where login=#{login}")
    String roleByLogin(@Param("login")String login);
}
