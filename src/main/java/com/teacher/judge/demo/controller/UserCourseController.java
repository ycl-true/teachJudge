package com.teacher.judge.demo.controller;

import com.teacher.judge.demo.bean.Result;
import com.teacher.judge.demo.bean.UserCourseBean;
import com.teacher.judge.demo.enums.ResultEnum;
import com.teacher.judge.demo.exception.TeachException;
import com.teacher.judge.demo.service.UserCourseService;
import com.teacher.judge.demo.util.ApplyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/userCourse")
@Api(value = "权限控制器")
@Slf4j
public class UserCourseController {
    @Autowired
    private UserCourseService userCourseService;

    @DeleteMapping(value = "/userCourse")
    @ApiOperation(value = "删除一条权限", notes = "返回成功与否")
    @ApiImplicitParam(paramType = "header", name = "token", value = "token值", required = true, dataType = "String")
    public Result deleteUserCourse(@RequestBody UserCourseBean userCourseBean) {
        if(StringUtils.isEmpty(userCourseBean.getId())){
            throw new TeachException(ResultEnum.PARAM_NOT_EXIST);
        }
        // 删除前先判断是否有评论，如果有则置为失效

        userCourseService.deleteById(userCourseBean.getId());
        return ApplyUtil.success();
    }
}
