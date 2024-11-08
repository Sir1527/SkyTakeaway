package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "员工修改密码传递模型")
public class EmployeePassWordDTO {

    @ApiModelProperty("员工新密码")
    private String newPassword;
    @ApiModelProperty("员工旧密码")
    private String oldPassword;
}
