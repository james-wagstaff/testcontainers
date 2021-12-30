package com.identifix.testing.models;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
@Data
public class Family {

    @Id
    @GeneratedValue
    Long id;

    String lastname;

    @Relationship
    Set<Person> familyMembers;

}
