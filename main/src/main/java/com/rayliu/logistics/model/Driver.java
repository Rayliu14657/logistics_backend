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
 * 司机表
 * </p>
 *
 * @author Rayliu
 * @since 2022-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Driver对象", description="司机表")
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "司机ID")
    @TableId(value = "driverID", type = IdType.ID_WORKER_STR)
    private Integer driverID;

    @ApiModelProperty(value = "司机姓名")
    @TableField("dName")
    private String dName;

    @ApiModelProperty(value = "车牌号")
    private String license;

    @ApiModelProperty(value = "司机电话号码")
    private String tel;

    @ApiModelProperty(value = "是否正在运输（1.在运输，0.不在运输）")
    private Integer busy;

    @TableLogic
    @ApiModelProperty(value="逻辑删除")
    private Integer deleted;


}
