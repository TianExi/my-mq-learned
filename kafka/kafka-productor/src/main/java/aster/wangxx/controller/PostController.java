package aster.wangxx.controller;

import aster.wangxx.entity.Message;
import aster.wangxx.productor.KafkaSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PostController
 * @Description TODO
 * @Author By wangxx
 * @Date 19:56 2021/9/23
 * @Version 1.0
 **/
@RestController
public class PostController {

    @Autowired
    private KafkaSendMessage kafkaSendMessage;

    @PostMapping("/addMessage")
    public void productor (@RequestBody Message message) {
        kafkaSendMessage.Send(message.getData());
    }


}
