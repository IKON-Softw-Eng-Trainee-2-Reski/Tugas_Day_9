package com.messages.database.demo;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message_tbl")
@NoArgsConstructor
@EqualsAndHashCode
public class Message {
  @Id
  @Column(name="id")
  private int id;

  @Column(name="textmessages")
  private String textmessages;

  public Message(int id, String textmessages) {
    this.id = id;
    this.textmessages = textmessages;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMessageText() {
    return textmessages;
  }

  public void setMessageText(String textmessages) {
    this.textmessages = textmessages;
  }
}
