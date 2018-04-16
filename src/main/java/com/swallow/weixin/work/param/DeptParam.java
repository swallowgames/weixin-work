package com.swallow.weixin.work.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeptParam extends BaseParam {

    private String name;

    private Long parentid;

    private Integer order;

    private Long id;

}
