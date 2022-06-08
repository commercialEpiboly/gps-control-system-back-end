package org.yzh.web.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author libo
 * @date 2022年03月17日 16:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "设备信息分页查询请求")
public class DeviceReqVo implements Serializable {
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

    @NotNull(message = "分页页码参数不能为空！")
    private int pageNumber;

    @NotNull(message = "分页大小参数不能为空！")
    private int pageSize;
}
