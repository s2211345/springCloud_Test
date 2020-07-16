package com.lwc.test.view.sys.response;

import com.lwc.test.enums.base.BaseCodeEnum;
import com.lwc.test.view.base.response.BaseResult;
import lombok.Data;

@Data
public class SysResult<T> extends BaseResult {
    private Integer count;
}
