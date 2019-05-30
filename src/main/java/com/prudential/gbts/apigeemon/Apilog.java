package com.prudential.gbts.apigeemon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Getter
@Setter
@ToString(exclude = "id")
@NoArgsConstructor
public class Apilog {

  @Id private String id;
  private String transactionid;
  private String payload;

  public Apilog(final String transactionid, final String payload) {
    this.transactionid = transactionid;
    this.payload = payload;
  }
}
