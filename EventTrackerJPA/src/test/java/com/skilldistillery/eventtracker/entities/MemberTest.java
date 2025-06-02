package com.skilldistillery.eventtracker.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale.Category;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MemberTest {

	private static EntityManagerFactory emf; // expensive
	private EntityManager em;
	private Member member;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("EventTrackerJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		member = em.find(Member.class,1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		member = null;
	}

	@Test
	void test_Member_entity_mapping() {
		assertNotNull(member);
		assertNotNull("James", member.getFirstName());
		
	}
	
}
