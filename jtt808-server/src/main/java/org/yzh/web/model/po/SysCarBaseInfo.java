package org.yzh.web.model.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author libo
 * @date 2022年03月17日 9:45
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_car_base_info",autoResultMap = true)
public class SysCarBaseInfo {

    /**
     * 主键ID
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 车架号
     */
    private String frameNumber;
    /**
     * 车牌号
     */
    private String numberPlate;
    /**
     * 发动机号
     */
    private String engineNumber;
    /**
     * 品牌
     */
    private String brand;

    private String color;
    /**
     * 经销商地址
     */
    private String dealerAddress;


    /**
     * 设备状态
     */
    private String status;
    /**
     * 备注
     */
    private String remark;


    private String usePeriod;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_date_time", fill = FieldFill.INSERT)
    private LocalDateTime createDateTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
