package com.swallow.weixin.work.result;

import com.swallow.weixin.work.param.DeptParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeptListResult extends BaseResult {

    private List<DeptParam> department;

}
