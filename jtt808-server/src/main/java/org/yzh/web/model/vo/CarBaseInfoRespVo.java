package org.yzh.web.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author libo
 * @date 2022年03月17日 16:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "返回车辆基础详细分页列表")
public class CarBaseInfoRespVo implements Serializable {

    @ApiModelProperty(value = "主键id，新增不传")
    private Integer id;

    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id")
    private String deviceId;
    /**
     * 身份证
     */
    @ApiModelProperty(value = "身份证")
    private String idCard;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
    /**
     * 车架号
     */
    @ApiModelProperty(value = "车架号")
    private String frameNumber;
    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String numberPlate;
    /**
     * 发动机号
     */
    @ApiModelProperty(value = "发动机号")
    private String engineNumber;
    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌")
    private String brand;

    @ApiModelProperty(value = "颜色")
    private String color;
    /**
     * 经销商地址
     */
    @ApiModelProperty(value = "经销商地址")
    private String dealerAddress;
    /**
     * 设备状态
     */
    @ApiModelProperty(value = "设备状态")
    private String status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDateTime;
    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "设备使用年限")
    private String usePeriod;

}
