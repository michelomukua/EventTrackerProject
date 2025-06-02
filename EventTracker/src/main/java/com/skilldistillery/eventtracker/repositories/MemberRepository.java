package com.skilldistillery.eventtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.eventtracker.entities.Member;

public interface MemberRepository extends JpaRepository <Member, Integer>{

}
