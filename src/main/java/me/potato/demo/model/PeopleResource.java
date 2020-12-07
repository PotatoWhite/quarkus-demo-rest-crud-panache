package me.potato.demo.model;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(path="people", hal=true)
public interface PeopleResource extends PanacheEntityResource<Person, Long> {
}
