package com.uway.sports.web.controller;

import com.uway.sports.common.controller.BaseController;
import com.uway.sports.common.utils.DateUtils;
import com.uway.sports.common.utils.JSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/19 19:47
 * @Discription
 */
@Controller
@RequestMapping(value = "/ueditor")
public class UEditorController extends BaseController {
    @RequestMapping(value = "toUEditorDemo")
    public String toUEditorDemo(){
        return "ueditor/demo";
    }
}
