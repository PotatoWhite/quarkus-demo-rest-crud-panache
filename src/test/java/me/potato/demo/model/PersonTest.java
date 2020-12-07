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
    person.setStatus(Status.Alive);
    person.persist();

    Person personGone=new Person();
    personGone.setName("man one");
    personGone.setBirth(LocalDate.of(1911, Month.FEBRUARY, 2));
    personGone.setStatus(Status.Gone);
    personGone.persist();


    Person personMissing=new Person();
    personMissing.setName("man two");
    personMissing.setBirth(LocalDate.of(1912, Month.FEBRUARY, 3));
    personMissing.setStatus(Status.Missing);
    personMissing.persist();


    if(!person.isPersistent())
      throw new Exception("could not persist");
    if(!personGone.isPersistent())
      throw new Exception("could not persist");
    if(!personMissing.isPersistent())
      throw new Exception("could not persist");


    var alive=Person.findAlive();
    alive.stream()
         .forEach(item -> log.info(item.toString()));

    var test01=Person.listAll();
    test01.forEach(item -> log.info(item.toString()));

    var countAll=Person.count();
    assertEquals(countAll, test01.size());

    var countGone=Person.count("status", Status.Gone);
    assertEquals(countGone, 1l);

    var countAlive=Person.count("status", Status.Alive);
    assertEquals(countAlive, 1l);

    var manTwo=Person.findByName("man two");
    assertEquals(manTwo.getBirth(), LocalDate.of(1912, Month.FEBRUARY, 3));
  }
}
