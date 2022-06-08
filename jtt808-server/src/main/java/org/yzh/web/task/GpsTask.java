package org.yzh.web.task;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.yzh.web.model.po.SysCarGpsInfo;
import org.yzh.web.service.CarDeviceGpsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author libo
 * @date 2022年03月20日 22:49
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class GpsTask {

    private final CarDeviceGpsService carDeviceGpsService;

    @Async
    @Scheduled(cron = "0 37 3 * * ? ")
    public void cacheTopic() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(3);
        log.info("执行定时任务，删除3天前的所有定位数据");
        List<Integer> ids = carDeviceGpsService.list(Wrappers.<SysCarGpsInfo>lambdaQuery()
                .le(SysCarGpsInfo::getCreateDateTime, localDateTime))
                .stream().map(SysCarGpsInfo::getId)
                .collect(Collectors.toList());
        log.info("删除数据数量为:{}", CollectionUtils.isEmpty(ids) ? 0 : ids.size());
        carDeviceGpsService.removeByIds(ids);
    }
}
