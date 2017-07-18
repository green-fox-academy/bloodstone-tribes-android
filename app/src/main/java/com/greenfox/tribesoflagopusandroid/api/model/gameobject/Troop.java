package com.greenfox.tribesoflagopusandroid.api.model.gameobject;

import com.greenfox.tribesoflagopusandroid.api.model.response.BaseResponse;

/**
 * Created by User on 2017. 06. 14..
 */

public class Troop extends BaseResponse {

  private long id;
  private int level;
  private int hp;
  private int attack;
  private int defence;
  private long startedAt;
  private long finishedAt;

  public Troop() {
  }

  public Troop(long id, int level, int hp, int attack, int defence) {
    this.id = id;
    this.level = level;
    this.hp = hp;
    this.attack = attack;
    this.defence = defence;
    this.startedAt = System.currentTimeMillis();
    this.finishedAt = System.currentTimeMillis() + 600000;    //10 min
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public int getAttack() {
    return attack;
  }

  public void setAttack(int attack) {
    this.attack = attack;
  }

  public int getDefence() {
    return defence;
  }

  public void setDefence(int defence) {
    this.defence = defence;
  }

  public long getStartedAt() {
    return startedAt;
  }

  public long getFinishedAt() {
    return finishedAt;
  }
}

