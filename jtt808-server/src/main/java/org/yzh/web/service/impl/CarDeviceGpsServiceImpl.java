package org.yzh.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yzh.web.mapper.CarGpsMapper;
import org.yzh.web.model.po.SysCarGpsInfo;
import org.yzh.web.service.CarDeviceGpsService;

/**
 * @author libo
 * @date 2022年02月17日 14:46
 */
@Service
@Slf4j
public class CarDeviceGpsServiceImpl extends SuperServiceImpl<CarGpsMapper, SysCarGpsInfo> implements CarDeviceGpsService {

}
