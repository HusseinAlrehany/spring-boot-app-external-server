package spring_boot_with_external_server.spring_boot_with_external_server.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_boot_with_external_server.spring_boot_with_external_server.custommapper.MemberMapper;
import spring_boot_with_external_server.spring_boot_with_external_server.dtos.MemberDTO;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Member;
import spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types.MemberAlreadyRegisteredException;
import spring_boot_with_external_server.spring_boot_with_external_server.exceptions.types.NotFoundException;
import spring_boot_with_external_server.spring_boot_with_external_server.repository.MemberRepository;
import spring_boot_with_external_server.spring_boot_with_external_server.service.member.MemberService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    public MemberDTO addMember(MemberDTO memberDTO) {

        Member dbMember = memberRepository.findFirstByEmail(memberDTO.getEmail());

        if(dbMember != null){
            throw new MemberAlreadyRegisteredException("Member already registered with this email!");
        }

        Member member = MemberMapper.MEMBER_MAPPER.toMember(memberDTO);

        return MemberMapper.MEMBER_MAPPER.toMemberDTO(memberRepository.save(member));
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();

        if(members.isEmpty()){
            throw new NotFoundException("No Members Found!");
        }
        return MemberMapper.MEMBER_MAPPER.toMemberDTOList(members);
    }
}
