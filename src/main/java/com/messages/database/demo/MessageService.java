package com.messages.database.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageService {
  @Autowired(required = false)
  MessageRepository messageRepository;
  public List<Message> getMessages() {
    return (List<Message>) messageRepository.findAll();
  }

  public Message getMessagesWithId(Integer countryId) {
    return messageRepository.findById(countryId).get();
  }


  public Message addMessage(Message addedMessage) {
    long size = messageRepository.count();
    log.info("[FLO] REPOSITORY SIZE IS: " + size);
    Integer newId = (int) size + 1;
    log.info("[FLO] newId IS: " + newId);
    Message message = new Message(newId, addedMessage.getMessageText());
    log.info("[FLO] newly added message is: " + message.getId() + message.getMessageText());
    messageRepository.save(message);
    log.info("[FLO] newly added message in repository is: " + messageRepository.findById(newId).get());
    return messageRepository.findById(newId).get();
  }

  public Message updateMessage(Message updatedMessage) {
    messageRepository.save(updatedMessage);
    return messageRepository.findById(updatedMessage.getId()).get();
  }

  public ResponseMessage deleteMessages(Integer messageID) {
    messageRepository.deleteById(messageID);
    return new ResponseMessage("Message deleted");
  }
}
