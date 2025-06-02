package com.skilldistillery.eventtracker.services;

import java.util.List;

import com.skilldistillery.eventtracker.entities.Member;

public interface MemberService {
	
	Member findById(int memberId);

	List<Member> getAllMembers();

	Member create(Member member);

	Member update(Member member);

	boolean delete(int memberId);

}
