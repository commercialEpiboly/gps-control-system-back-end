package org.yzh.web.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.yzh.commons.model.APIResult;
import org.yzh.web.exception.BusinessLogicException;
import org.yzh.web.model.po.SysCarBaseInfo;
import org.yzh.web.model.po.SysCarGpsInfo;
import org.yzh.web.model.vo.CarBaseInfoRespVo;
import org.yzh.web.model.vo.DeviceReqVo;
import org.yzh.web.model.vo.PageResult;
import org.yzh.web.model.vo.SaveCarBaseInfoVo;
import org.yzh.web.service.CarBaseService;
import org.yzh.web.service.CarDeviceGpsService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author libo
 * @date 2022年03月17日 17:05
 */
@RestController
@RequestMapping("/device")
@Api(tags = "设备管理后端API")
@RequiredArgsConstructor
@Slf4j
public class DeviceController {

    private final CarBaseService carBaseService;

    private final CarDeviceGpsService carDeviceGpsService;

    @PostMapping("/saveDeviceBase")
    @ApiOperation(value = "修改保存设备基础信息")
    @Transactional(rollbackFor = BusinessLogicException.class)
    public APIResult<Boolean> saveDeviceBase(@RequestBody SaveCarBaseInfoVo saveCarBaseInfoVo) {
        SysCarBaseInfo sysCarBaseInfo = SysCarBaseInfo.builder().build();
        BeanUtils.copyProperties(saveCarBaseInfoVo, sysCarBaseInfo);
        carBaseService.saveOrUpdate(sysCarBaseInfo);
        return APIResult.ok(Boolean.TRUE);
    }

    @PostMapping("/getDeviceInfo")
    @ApiOperation(value = "获取设备基础信息分页")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public APIResult<PageResult<CarBaseInfoRespVo>> getDeviceInfo(@Valid @RequestBody DeviceReqVo reqPage) {
        log.info("获取设备基础信息分页,请求参数:{}", JSONUtil.toJsonStr(reqPage));
        IPage<SysCarBaseInfo> page = new Page<>(reqPage.getPageNumber(), reqPage.getPageSize());
        LambdaQueryWrapper<SysCarBaseInfo> lambdaQuery = Wrappers.<SysCarBaseInfo>lambdaQuery();
        lambdaQuery.like(StringUtils.isNotBlank(reqPage.getPhoneNumber()), SysCarBaseInfo::getPhoneNumber, reqPage.getPhoneNumber());
        lambdaQuery.like(StringUtils.isNotBlank(reqPage.getEngineNumber()), SysCarBaseInfo::getEngineNumber, reqPage.getEngineNumber());
        lambdaQuery.like(StringUtils.isNotBlank(reqPage.getFrameNumber()), SysCarBaseInfo::getFrameNumber, reqPage.getFrameNumber());
        lambdaQuery.like(StringUtils.isNotBlank(reqPage.getIdCard()), SysCarBaseInfo::getIdCard, reqPage.getIdCard());
        lambdaQuery.like(StringUtils.isNotBlank(reqPage.getNumberPlate()), SysCarBaseInfo::getNumberPlate, reqPage.getNumberPlate());
        IPage<SysCarBaseInfo> carBaseInfoPage = carBaseService.getBaseMapper().selectPage(page, lambdaQuery);
        if (CollectionUtils.isEmpty(carBaseInfoPage.getRecords())) {
            return null;
        }

        List<CarBaseInfoRespVo> carBaseInfoRespVoList = carBaseInfoPage.getRecords().stream().map(po -> {
            CarBaseInfoRespVo respVo = CarBaseInfoRespVo.builder().build();
            BeanUtils.copyProperties(po, respVo);
            respVo.setUsePeriod(StringUtils.isNotBlank(po.getUsePeriod()) ? po.getUsePeriod() : "0");
            return respVo;
        }).collect(Collectors.toList());

        return APIResult.ok(PageResult.<CarBaseInfoRespVo>builder()
                .data(carBaseInfoRespVoList)
                .number(carBaseInfoPage.getCurrent())
                .size(carBaseInfoPage.getSize())
                .totalElements(carBaseInfoPage.getTotal())
                .build());
    }

    @GetMapping("/getDeviceGpsList")
    @ApiOperation(value = "获取车辆Gps数据")
    public APIResult<List<SysCarGpsInfo>> getDeviceGpsList(@RequestParam("deviceId") String deviceId) {
        List<SysCarGpsInfo> sysCarGpsInfoList = carDeviceGpsService.list(Wrappers.<SysCarGpsInfo>lambdaQuery()
                .eq(SysCarGpsInfo::getDeviceId, deviceId)
                .orderByAsc(SysCarGpsInfo::getCreateDateTime));
        return APIResult.ok(sysCarGpsInfoList);
    }

    @GetMapping("/getAllDeviceGpsOneData")
    @ApiOperation(value = "获取所有车辆的一条Gps数据")
    public APIResult<List<SysCarGpsInfo>> getAllDeviceGpsOneData() {
        List<SysCarGpsInfo> sysCarGpsInfoList = carDeviceGpsService.list(Wrappers.<SysCarGpsInfo>lambdaQuery()
                .select(SysCarGpsInfo::getUpdateTime,
                        SysCarGpsInfo::getDeviceId,
                        SysCarGpsInfo::getLongitude,
                        SysCarGpsInfo::getLatitude)
                .groupBy(SysCarGpsInfo::getDeviceId)
        );
        return APIResult.ok(sysCarGpsInfoList);
    }


}
