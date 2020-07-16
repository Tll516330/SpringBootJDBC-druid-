package com.data.springjdbc.pojo;

import lombok.Data;

/**
 * @author tll
 * @create 2020/7/16 14:00
 */
@Data
public class User {
    private int id;
    private String name;
    private String password;
    private String perms;
}
