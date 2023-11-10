package com.rayliu.logistics.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 货物信息表
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cargoinfo对象", description="货物信息表")
public class Cargoinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "货票号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @ApiModelProperty(value = "货物名称")
    @TableField("cName")
    private String cName;

    @ApiModelProperty(value = "应到数量")
    @TableField("dueNum")
    private Integer dueNum;

    @ApiModelProperty(value = "实到数量")
    @TableField("actNum")
    private Integer actNum;

    @ApiModelProperty(value = "货物单价")
    private Double cost;

    @ApiModelProperty(value = "运费")
    private Double carriage;


    @ApiModelProperty(value = "是否收讫（0为到付，1为收讫）")
    @TableField("isPaid")
    private Integer isPaid;

    @TableLogic
    @ApiModelProperty(value="逻辑删除")
    private Integer deleted;


}
