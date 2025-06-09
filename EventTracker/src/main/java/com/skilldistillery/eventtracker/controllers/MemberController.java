package com.skilldistillery.eventtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.eventtracker.entities.Member;
import com.skilldistillery.eventtracker.services.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class MemberController {
	
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping({"members","members/"})
	public List<Member> index(){
		return memberService.getAllMembers();
	}
	
	@GetMapping("members/{memberId}")
	public Member findById(@PathVariable("memberId")int memberId, HttpServletResponse resp) {
		System.out.println(memberId);
		Member member = memberService.findById(memberId);
		if(member == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return member;
		
	}
	@PostMapping({"members","members/"})
	public Member addMember(@RequestBody Member	member,
			HttpServletRequest req,HttpServletResponse res) 
	{

		try {
			member = memberService.create(member);
			if (member == null)
			{
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				res.setStatus(HttpServletResponse.SC_CREATED);
				res.setHeader("Location", req.getRequestURL().append("/").append(member.getId()).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			member = null;
		}
		return member;
	}
	
	
	@PutMapping("members/{memberId}")
	public Member updateMember(@PathVariable("memberId")int memberId, @RequestBody Member member,
			HttpServletRequest req,HttpServletResponse res) {
		Member memberToUpdate = memberService.findById(memberId);
		if(memberToUpdate == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		member.setId(memberId);
		return memberService.update(member);
		
	}
	
	@DeleteMapping("members/{memberId}")
	public boolean deleteMember(@PathVariable("memberId")int memberId,
			HttpServletRequest req,HttpServletResponse res) {
		Member memberToDelete = memberService.findById(memberId);
		if(memberToDelete == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return memberService.delete(memberId);
		
	}
	
	
}

/*### Endpoints

| HTTP Verb | URI             | Request Body | Response Body | Status |
|-----------|-----------------|--------------|---------------|---------|
| GET       | `/api/dives`    |              | List of dives | 200   |
| GET       | `/api/dives/17` |              | Single dive   | 200 or 404 |
| POST      | `/api/dives`    | JSON of new dive       | JSON of created dive | 201 or 400 |
| PUT       | `/api/dives/17` | JSON for updating dive | JSON of updated dive | 200, 404, or 400 |
| DELETE    | `/api/dives/17` |              | | 204, 404, or 400 |
*/