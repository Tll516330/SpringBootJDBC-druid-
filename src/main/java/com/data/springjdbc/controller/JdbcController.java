package com.data.springjdbc.controller;

import com.data.springjdbc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author tll
 * @create 2020/7/16 13:58
 * 编写一个Controller
 */
@RestController
@RequestMapping("/jdbc")
public class JdbcController {
    /**
     * Spring Boot 默认提供了数据源，默认提供了 org.springframework.jdbc.core.JdbcTemplate
     * JdbcTemplate 中会自己注入数据源，用于简化 JDBC操作
     * 还能避免一些常见的错误,使用起来也不用再自己来关闭数据库连接
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 查询t_user 表中的所有数据
     * 查询employee表中所有数据
     * List 中的1个 Map 对应数据库的 1行数据
     * Map 中的 key 对应数据库的字段名，value 对应数据库的字段值
     * @return
     */
    @RequestMapping("/list")
    public List<Map<String, Object>> userList(){
        String sql = "select * from t_user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    /**
     * 新增一个用户
     * @return
     */
    @RequestMapping("/add")
    public String add(){
        String sql = "insert into t_user(name,password,perms) value('张三','123','hahah')";
        jdbcTemplate.update(sql);
        return "Add_OK";
    }

    /**
     * 根据用户id更新数据
     * @return
     */
    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id")int id){
        String sql = "update t_user set name = ?,password = ?,perms = ? where id= "+id;
        //数据
        Object[] objects = new Object[3];
        objects[0] = "李四";
        objects[1] = "123321";
        objects[2] = "下一个是谁";
        jdbcTemplate.update(sql,objects);
        return "Update_OK";
    }

    /**
     * 删除一个用户
     * @param id
     * @return
     */
    @RequestMapping("/del/{id}")
    public String del(@PathVariable("id")int id){
        String sql = "delete from t_user where id = "+id;
        jdbcTemplate.update(sql);
        return "Delete_OK";
    }

}
