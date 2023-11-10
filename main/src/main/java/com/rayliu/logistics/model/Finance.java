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
 * 财务表
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Finance对象", description="财务表")
public class Finance implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "货物ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Integer id;

    @ApiModelProperty(value = "运单总价")
    private Double sum;

    @ApiModelProperty(value = "赔付金额")
    private Double compensate;

    @TableLogic
    @ApiModelProperty(value="逻辑删除")
    private Integer deleted;


}
