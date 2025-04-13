package spring_boot_with_external_server.spring_boot_with_external_server.service.member;

import spring_boot_with_external_server.spring_boot_with_external_server.dtos.MemberDTO;

import java.util.List;

public interface MemberService {

    MemberDTO addMember(MemberDTO memberDTO);

    List<MemberDTO> getAllMembers();
}
