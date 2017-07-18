package com.greenfox.tribesoflagopusandroid.api.model.gameobject;

/**
 * Created by User on 2017. 07. 15..
 */

public class BuildingDTO {

  private String type;

  public BuildingDTO(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
