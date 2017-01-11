
package com.websystique.springsecurity.mapper;

import com.websystique.springsecurity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface DBMapper {
    
    @Select("select * from vk_messenger.users where login=#{login}")
    User findUserByLogin(@Param("login")String login);
    
    @Select("select type FROM vk_messenger.users left join vk_messenger.user_profile " +
    "on vk_messenger.user_profile.user_id = vk_messenger.users.id " +
    "left join vk_messenger.profile " +
    "on vk_messenger.profile.id = vk_messenger.user_profile.profile_id where login=#{login}")
    String roleByLogin(@Param("login")String login);
    
    @Update("update vk_messenger.users set access_token=#{access_token},last_date=#{last_date} where login=#{login} and password=#{password}")
    void updateAccessToken(User user);
    
    @Insert("insert into vk_messenger.criteria (vk_messenger.criteria.condition, offset, user_id) values (#{condition},#{offset},#{user_id})")
    void saveCriteria(@Param("condition")String condition, @Param("offset")int offset, @Param("user_id")int userId);
    
    @Insert("insert into vk_messenger.messages (criteria_id, text) values (#{criteria_id},#{text})")
    void saveMessageForCriteria(@Param("criteria_id")int criteriaId, @Param("text")String message);
    
    @Select("select LAST_INSERT_ID()")
    int lastInsertedCriteriaId();
    
    //переделать запрос на только с объектом, а объект обновлять из кода
}
