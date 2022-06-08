package org.yzh.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yzh.web.mapper.CarBaseMapper;
import org.yzh.web.model.po.SysCarBaseInfo;
import org.yzh.web.service.CarBaseService;

/**
 * @author libo
 * @date 2022年02月17日 14:46
 */
@Service
@Slf4j
public class CarBaseServiceImpl extends SuperServiceImpl<CarBaseMapper, SysCarBaseInfo> implements CarBaseService {

}
