package com.pbj.blog.dto.reply;

import com.pbj.blog.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyListDTO {

    private Long id;
    private String body;
    private String loginId;
    private LocalDateTime regDate;

    public ReplyListDTO(Reply reply){

        this.id = reply.getId();
        this.body = reply.getBody();
        this.loginId = reply.getMember().getLoginId();
        this.regDate = reply.getRegDate();

    }
}