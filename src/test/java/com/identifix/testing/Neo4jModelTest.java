package com.identifix.testing;

import com.identifix.testing.models.Family;
import com.identifix.testing.models.Person;
import com.identifix.testing.repositories.FamilyRepository;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Fail.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Testcontainers
public class Neo4jModelTest {

    @Autowired
    FamilyRepository repository;

    @Container
    private static Neo4jContainer neo4jContainer = new Neo4jContainer("neo4j:3.5.0")
            .withAdminPassword(null);

    @Test
    void infoIsSavedCorrectly() {
        Person personOne = new Person();
        Person personTwo = new Person();
        personOne.setAge(28);
        personOne.setFirstName("Andrew");
        personOne.setLastName("Wagstaff");

        personTwo.setAge(36);
        personTwo.setFirstName("James");
        personTwo.setLastName("Wagstaff");

        Family family = new Family();
        family.setLastname("Wagstaff");
        family.setFamilyMembers(Set.of(personOne, personTwo));
        Family savedModel = repository.save(family);
        Family loadedModel = repository.findById(savedModel.getId()).get();

        assertEquals(savedModel, loadedModel);

    }

    @After
    void tearDown() {
        repository.deleteAll();
    }

}
