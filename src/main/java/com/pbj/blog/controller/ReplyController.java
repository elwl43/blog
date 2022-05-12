package com.pbj.blog.controller;

import com.pbj.blog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

}
