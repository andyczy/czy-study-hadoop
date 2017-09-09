package com.czy.hadoop.controller;

import com.czy.hadoop.model.Message;
import com.czy.hadoop.service.AnEnService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * @Auther 陈郑游
 * @Data 2017/9/9 0009
 * @Description:  基础controller类
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public abstract class AnEnBaseController {

    // 日志
    private static final Logger log = Logger.getLogger(AnEnBaseController.class);

    @Resource(name = "aeService")
    private AnEnService aeService;



    /**
     * 全局的处理异常方法
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Message.MessageEntry handleThrowable(Throwable e) {
        log.error("服务器发现异常", e);
        return Message.error(e.getMessage());
    }


}
