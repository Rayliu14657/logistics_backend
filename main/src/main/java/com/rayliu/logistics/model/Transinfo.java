package com.rayliu.logistics.model;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 运单信息表（记录运输中的信息）
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Transinfo对象", description="运单信息表（记录运输中的信息）")
public class Transinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Integer id;

    @ApiModelProperty(value = "发货地点")
    @TableField("fromSpot")
    private String fromSpot;

    @ApiModelProperty(value = "收货地点")
    @TableField("toSpot")
    private String toSpot;

    @ApiModelProperty(value = "发出省")
    @TableField("fromProvince")
    private String fromProvince;

    @ApiModelProperty(value = "到达省")
    @TableField("toProvince")
    private String toProvince;

    @TableField(fill= FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发货时间")
    private Date fromTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "收货时间")
    @TableField("toTime")
    private Date toTime;

    @ApiModelProperty(value = "司机ID")
    @TableField("driverID")
    private Integer driverID;

    @ApiModelProperty(value = "货物状态（1.运输中，2.配送中，3.确认收货，0退货）")
    private Integer status;

    @TableLogic
    @ApiModelProperty(value="逻辑删除")
    private Integer deleted;


}
