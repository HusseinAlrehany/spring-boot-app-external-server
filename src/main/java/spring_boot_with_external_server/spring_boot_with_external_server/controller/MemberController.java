package spring_boot_with_external_server.spring_boot_with_external_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring_boot_with_external_server.spring_boot_with_external_server.dtos.ApiResponse;
import spring_boot_with_external_server.spring_boot_with_external_server.dtos.MemberDTO;
import spring_boot_with_external_server.spring_boot_with_external_server.service.member.MemberService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public ResponseEntity<ApiResponse<MemberDTO>> addMember(@RequestBody @Validated MemberDTO memberDTO){

        MemberDTO memberDTO1 = memberService.addMember(memberDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>("Member Created Successfully", memberDTO1));

    }

    @GetMapping("/members")
    public ResponseEntity<ApiResponse<List<MemberDTO>>> getAllMembers(){
        List<MemberDTO> memberDTOList = memberService.getAllMembers();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>("All Members Retrieved Success", memberDTOList));
    }
}
