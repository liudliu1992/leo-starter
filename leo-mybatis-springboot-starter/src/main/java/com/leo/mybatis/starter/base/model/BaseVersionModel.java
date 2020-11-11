package com.leo.mybatis.starter.base.model;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author liusl
 * @date 2020/8/13 上午11:11 
 * @version 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseVersionModel extends BaseModel {
    @Version
    private Integer version;
}
