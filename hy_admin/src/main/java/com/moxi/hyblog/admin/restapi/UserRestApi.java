package com.moxi.hyblog.admin.restapi;


import com.moxi.hyblog.admin.annotion.AuthorityVerify.AuthorityVerify;
import com.moxi.hyblog.admin.annotion.OperationLogger.OperationLogger;
import com.moxi.hyblog.admin.global.SysConf;
import com.moxi.hyblog.utils.ResultUtil;
import com.moxi.hyblog.xo.service.UserService;
import com.moxi.hyblog.xo.vo.UserVO;
import com.moxi.hyblog.base.exception.ThrowableUtils;
import com.moxi.hyblog.base.validator.group.Delete;
import com.moxi.hyblog.base.validator.group.GetList;
import com.moxi.hyblog.base.validator.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 RestApi
 * </p>
 *
 * @author 陌溪
 * @since 2020年1月4日21:29:09
 */
@RestController
@Api(value = "用户相关接口", tags = {"用户相关接口"})
@RequestMapping("/user")
@Slf4j
public class UserRestApi {

    @Autowired
    UserService userService;

    @AuthorityVerify
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表", response = String.class)
    @PostMapping("/getList")
    public String getList(@Validated({GetList.class}) @RequestBody UserVO userVO, BindingResult result) {

        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        log.info("获取用户列表");
        return ResultUtil.result(SysConf.SUCCESS, userService.getPageList(userVO));
    }

    @AuthorityVerify
    @OperationLogger(value = "编辑用户")
    @ApiOperation(value = "编辑用户", notes = "编辑用户", response = String.class)
    @PostMapping("/edit")
    public String edit(@Validated({Update.class}) @RequestBody UserVO userVO, BindingResult result) {

        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return userService.editUser(userVO);
    }

    @AuthorityVerify
    @OperationLogger(value = "删除用户")
    @ApiOperation(value = "删除用户", notes = "删除用户", response = String.class)
    @PostMapping("/delete")
    public String delete(@Validated({Delete.class}) @RequestBody UserVO userVO, BindingResult result) {

        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return userService.deleteUser(userVO);
    }

    @AuthorityVerify
    @OperationLogger(value = "重置用户密码")
    @ApiOperation(value = "重置用户密码", notes = "重置用户密码", response = String.class)
    @PostMapping("/resetUserPassword")
    public String resetUserPassword(@Validated({Delete.class}) @RequestBody UserVO userVO, BindingResult result) {

        // 参数校验
        ThrowableUtils.checkParamArgument(result);
        return userService.resetUserPassword(userVO);
    }
}