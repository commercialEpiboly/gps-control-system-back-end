package org.yzh.web.config;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.yzh.web.context.OperatorContextHolder;
import org.yzh.web.properties.MybatisPlusAutoFillProperties;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * 自定义填充公共字段
 *
 * @author sharvalue
 * @date 2018/12/11
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
public class DateMetaObjectHandler implements MetaObjectHandler {
    private final MybatisPlusAutoFillProperties autoFillProperties;

    public DateMetaObjectHandler(MybatisPlusAutoFillProperties autoFillProperties) {
        this.autoFillProperties = autoFillProperties;
    }

    /**
     * 是否开启了插入填充
     */
    @Override
    public boolean openInsertFill() {
        return autoFillProperties.getEnableInsertFill();
    }

    /**
     * 是否开启了更新填充
     */
    @Override
    public boolean openUpdateFill() {
        return autoFillProperties.getEnableUpdateFill();
    }

    /**
     * 插入填充，字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName(autoFillProperties.getCreateTimeField(), metaObject);
        Object lastTime = getFieldValByName(autoFillProperties.getLastTimeField(), metaObject);
        Object updateTime = getFieldValByName("updateTime", metaObject);
        Object createDateTime = getFieldValByName("createDateTime", metaObject);
        if (createTime == null || lastTime == null) {
            Date date = new Date();
            if (createTime == null) {
                setFieldValByName(autoFillProperties.getCreateTimeField(), date, metaObject);
            }
            if (lastTime == null) {
                setFieldValByName(autoFillProperties.getLastTimeField(), date, metaObject);
            }
            if (updateTime == null) {
                setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            }
            if (createDateTime == null) {
                setFieldValByName("createDateTime", LocalDateTime.now(), metaObject);
            }
        }
        operatorAdd(metaObject, autoFillProperties.getCreateUserIdField(), autoFillProperties.getCreateUserNameField());
    }

    private void operatorAdd(MetaObject metaObject, String userId, String userName) {
        Map<String, String> operator = OperatorContextHolder.getOperator();
        if (operator != null) {
            String id = operator.get("id");
            String name = operator.get("name");
            if (StrUtil.isNotEmpty(id) && StrUtil.isNotEmpty(name)) {
                setFieldValByName(userId, Long.valueOf(id), metaObject);
                setFieldValByName(userName, name, metaObject);
            }
        }

    }

    /**
     * 更新填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(autoFillProperties.getLastTimeField(), new Date(), metaObject);
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        operatorAdd(metaObject, autoFillProperties.getLastUserIdField(), autoFillProperties.getLastUserNameField());
    }
}