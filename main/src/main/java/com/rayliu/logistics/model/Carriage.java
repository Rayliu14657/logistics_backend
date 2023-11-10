package com.rayliu.logistics.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Carriage对象", description="")
public class Carriage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "运费等级")
    @TableId(value = "status", type = IdType.AUTO)
    private Integer status;

    @ApiModelProperty(value = "运价")
    private Double cost;

    @TableLogic
    @ApiModelProperty(value="逻辑删除")
    private Integer deleted;


}
