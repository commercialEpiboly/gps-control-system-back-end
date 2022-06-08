package org.yzh.web.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * mybatis-plus 配置
 *
 * @author sharvalue
 * @date 2020/4/5
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "web.mybatis-plus.auto-fill")
public class MybatisPlusAutoFillProperties {
    /**
     * 是否开启自动填充字段
     */
    private Boolean enabled = true;
    /**
     * 是否开启了插入填充
     */
    private Boolean enableInsertFill = true;
    /**
     * 是否开启了更新填充
     */
    private Boolean enableUpdateFill = true;
    /**
     * 创建时间字段名
     */
    private String createTimeField = "createTime";
    /**
     * 最后操作时间字段名
     */
    private String lastTimeField = "lastTime";
    /**
     * 创建人ID
     */
    private String createUserIdField = "createUserId";
    /**
     * 创建人姓名
     */
    private String createUserNameField = "createUserName";
    /**
     * 修改人ID
     */
    private String lastUserIdField = "lastUserId";
    /**
     * 修改人姓名
     */
    private String lastUserNameField = "lastUserName";
}
