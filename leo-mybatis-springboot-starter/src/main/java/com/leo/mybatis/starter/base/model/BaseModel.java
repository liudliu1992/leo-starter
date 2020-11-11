package com.leo.mybatis.starter.base.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 基类
 *
 * @author leo
 * @version 1.0.0
 * @date 2019/11/28:下午7:52
 */
@Data
public abstract class BaseModel {
    @TableId(type =IdType.AUTO)
    private Long id;

    private String createUser;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String modifyUser;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;
}
