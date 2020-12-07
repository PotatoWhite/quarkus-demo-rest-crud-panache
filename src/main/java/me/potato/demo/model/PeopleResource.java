package me.potato.demo.model;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.MethodProperties;
import io.quarkus.rest.data.panache.ResourceProperties;

import java.util.List;

@ResourceProperties(path="people", hal=true)
public interface PeopleResource extends PanacheEntityResource<Person, Long> {
  @MethodProperties(path="all", exposed=true)
  List<Person> findAll();

  @MethodProperties(exposed=false)
  boolean delete(Long id);

}
