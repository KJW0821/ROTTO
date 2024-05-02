package com.rezero.rotto.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
@Tag(name = "Like 컨트롤러", description = "좋아요 기능 관리를 위한 API")
public class LikeController {

}
