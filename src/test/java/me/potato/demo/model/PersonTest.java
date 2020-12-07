package me.potato.demo.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@QuarkusTest
public class PersonTest {

  private static void pt(PanacheEntityBase item) {
    log.info(item.toString());
  }

  @Test
  @Transactional
  public void create() throws Exception {
    Person person=new Person();
    person.setName("Stef");
    person.setBirth(LocalDate.of(1910, Month.FEBRUARY, 1));
    person.setPersonStatus(PersonStatus.Alive);
    person.persist();

    Person persoGone=new Person();
    persoGone.setName("man one");
    persoGone.setBirth(LocalDate.of(1911, Month.FEBRUARY, 1));
    persoGone.setPersonStatus(PersonStatus.Gone);
    persoGone.persist();


    Person personMissing=new Person();
    personMissing.setName("man two");
    personMissing.setBirth(LocalDate.of(1912, Month.FEBRUARY, 1));
    personMissing.setPersonStatus(PersonStatus.Missing);
    personMissing.persist();


    if(!person.isPersistent())
      throw new Exception("could not persist");

    var alive=Person.findAlive();
    alive.stream()
         .forEach(item -> log.info(item.toString()));

    var test01=Person.listAll();
    test01.forEach(item -> log.info(item.toString()));

    var countAll=Person.count();
    assertEquals(countAll, 1);

    var countGone=Person.count("status", "Gone");
    assertEquals(countGone, 0);

    var countAlive=Person.count("status", "Alive");
    assertEquals(countAlive, 1);
  }
}
