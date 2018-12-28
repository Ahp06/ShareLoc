package shareloc.dao;

import shareloc.model.Message;

public class MessageDao extends AbstractDao<Message> {

    public MessageDao() {
        super(Message.class);
    }
}
