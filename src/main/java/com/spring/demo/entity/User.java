package com.spring.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhougang
 */
@Data
@TableName("t_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 7270760261045664509L;
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String userName;
    private String email;

    private LocalDateTime createTime;
}