package com.example.orderhw.Controller;

import com.example.orderhw.DTO.Create.MemberDto;
import com.example.orderhw.DTO.ToBody.MemberOutDto;
import com.example.orderhw.Domain.Member;
import com.example.orderhw.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("members")
    public ResponseEntity<Long> addMember(@RequestBody MemberDto memberDto) {
        Long memberId = memberService.addMember(memberDto);
        return ResponseEntity.created(URI.create("members/" + memberId)).body(memberId);

    }

    @PutMapping("members/{memberId}")
    public ResponseEntity<Void> editMember(@PathVariable Long memberId, @RequestBody MemberDto memberDto) throws Exception {
        memberService.editMember(memberDto,memberId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("members/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) throws Exception {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("memers/{memberId}")
    public ResponseEntity<MemberOutDto> findOneMember(@PathVariable Long memberId) throws Exception {
        MemberOutDto memberOutDto = memberService.findOneMember(memberId);
        return ResponseEntity.ok().body(memberOutDto);
    }

    @GetMapping("memberlist")
    public ResponseEntity<List<MemberOutDto>> findAllMembers(
            @RequestParam(value = "pageNum",required = false, defaultValue = "0") int pagenum,
            @RequestParam(value = "pageSize",required = false, defaultValue = "10") int pagesize){
        Pageable pageable = PageRequest.of(pagenum,pagesize);
        List<MemberOutDto> members = memberService.getMemberPage(pageable);
        return ResponseEntity.ok().body(members);
    }
}
