package spring_boot_with_external_server.spring_boot_with_external_server.custommapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import spring_boot_with_external_server.spring_boot_with_external_server.dtos.MemberDTO;
import spring_boot_with_external_server.spring_boot_with_external_server.entities.Member;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Mapper
public interface MemberMapper {

    MemberMapper MEMBER_MAPPER = Mappers.getMapper(MemberMapper.class);


    //tell map struct to automatically set the join_date to LocalDateTime.now()
    @Mapping(target = "join_date", expression = "java(convertLocalDateTimeToDate(java.time.LocalDateTime.now()))")
    Member toMember(MemberDTO memberDTO);

    MemberDTO toMemberDTO(Member member);

    List<Member> toMemberList(List<MemberDTO> memberDTOS);

    List<MemberDTO> toMemberDTOList(List<Member> members);

    //to convert Date to LocalDateTime
    default Date convertLocalDateTimeToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
