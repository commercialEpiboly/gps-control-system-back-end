package org.yzh.web.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类
 *
 * @author libo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {

    /**
     * 大小
     */
    private Long size;
    /**
     * 总数c
     */
    private Long totalElements;

    private Long number;
    /**
     * 当前页结果集
     */
    private List<T> data;
}
