package me.potato.demo.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Cacheable
public class Person extends PanacheEntity {

  @Column(unique=true)
  private String    name;
  private LocalDate birth;

  @Enumerated(EnumType.STRING)
  private Status status;

  public static Person findByName(String name) {
    return find("name", name).firstResult();
  }

  public static List<Person> findAlive() {
    return list("status", Status.Alive);
  }

  public static void deleteStefs() {
    delete("name", "Stef");
  }
}
