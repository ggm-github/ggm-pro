package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.dbentity.MessagePush;
import com.uway.sports.web.entity.dbentity.User;

public class MessageInUser extends User {

    private MessagePush messagePush;

    public MessagePush getMessagePush() {
        return messagePush;
    }

    public void setMessagePush(MessagePush messagePush) {
        this.messagePush = messagePush;
    }
}
