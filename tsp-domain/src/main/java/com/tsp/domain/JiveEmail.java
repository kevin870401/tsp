package com.tsp.domain;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class JiveEmail {

  protected enum EmailTypes {
    WORK_TYPE("work"), HOME_TYPE("home");


    @Getter
    String name;

    EmailTypes(String name) {
      this.name = name;
    }
  }

  private boolean primary;
  private String type;
  private String value;
  private int displayOrder;
  
  public boolean isWorkEmail() {
    return EmailTypes.WORK_TYPE.name.equals(getType());
}
}
