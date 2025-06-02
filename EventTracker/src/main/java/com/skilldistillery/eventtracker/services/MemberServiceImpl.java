package com.skilldistillery.eventtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.eventtracker.entities.Member;
import com.skilldistillery.eventtracker.repositories.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepo;

	@Override
	public Member findById(int memberId) {
		return memberRepo.findById(memberId).orElse(null);
	}

	@Override
	public List<Member> getAllMembers() {
		return memberRepo.findAll();
	}

	@Override
	public Member create(Member member) {
		return memberRepo.saveAndFlush(member);
	}

	@Override
	public Member update(Member member) {
		return memberRepo.save(member);
	}

	@Override
	public boolean delete(int memberId) {
		if(memberRepo.findById(memberId) != null) { 
			memberRepo.deleteById(memberId);
			return true;
		}
		return false;
	}

}
