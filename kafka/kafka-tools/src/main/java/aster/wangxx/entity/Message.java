package aster.wangxx.entity;

import lombok.Data;

/**
 * @ClassName Message
 * @Description TODO
 * @Author By wangxx
 * @Date 19:57 2021/9/23
 * @Version 1.0
 **/
@Data
public class Message {

    /** 消息序号 */
    private int messageId;
    /** 消息体 */
    private String data;
}
