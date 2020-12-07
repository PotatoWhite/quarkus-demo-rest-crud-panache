package me.potato.demo.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Cacheable
public class Person extends PanacheEntity {

  private String    name;
  private LocalDate birth;

  @Enumerated(EnumType.STRING)
  private PersonStatus personStatus;

  public static Person findByName(String name) {
    return find("name", name).firstResult();
  }

  public static List<Person> findAlive() {
    return list("status", PersonStatus.Alive);
  }

  public static void deleteStefs() {
    delete("name", "Stef");
  }

}
