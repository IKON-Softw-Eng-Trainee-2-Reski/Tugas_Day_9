package com.messages.database.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class MessagesController {
  @Autowired(required = false)
  MessageService messageService;

  //@Transactional(timeout = 1)
  @GetMapping(path="/v1/messages")
  public ResponseEntity<List<Message>> getMessage() {
    try {
      ResponseEntity<List<Message>> response = new ResponseEntity<>(messageService.getMessages(), HttpStatus.OK);

      if (response.getBody().size() < 1) {
        throw new EmptyTableException("Data doesn't exist in table!!!");
      } else {
        return response;
      }
    } catch (InvalidDataAccessResourceUsageException e) {
      log.info("[Users] exception message is: "+e);
      return new ResponseEntity<>(HttpStatus.GONE);
    } catch (CannotCreateTransactionException e) {
      log.info("[Users] exception message is: "+e);
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    } catch (EmptyTableException e) { //tbl exists but is empty
      log.info("[Users] exception message is: "+e);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) { //any other exceptions
      log.info("[Users] exception message is: "+e);
      return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
  }

  @GetMapping(path="/v1/messages/{messageID}")
  public ResponseEntity<Message> getMessagesWithId(@PathVariable Integer messageID) {
    try {
      return new ResponseEntity<>(messageService.getMessagesWithId(messageID), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
  }


  @PostMapping(path="v1/messages")
  public ResponseEntity<Message> addMessage(@RequestBody Message addedMessage) {
    try {
      return new ResponseEntity<>(messageService.addMessage(addedMessage), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
  }

  @PutMapping(path="v1/messages")
  public ResponseEntity<Message> updateMessage(@RequestBody Message updatedMessage) {
    try {
      return new ResponseEntity<>(messageService.updateMessage(updatedMessage), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
  }

  @DeleteMapping(path="v1/messages/{messageID}")
  public ResponseEntity<ResponseMessage> deleteCountry(@PathVariable Integer messageID) {
    try {
      return new ResponseEntity<>(messageService.deleteMessages(messageID), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
  }
}
