package com.rezero.rotto.api.controller;

import com.rezero.rotto.utils.JwtTokenProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Tag(name = "News API", description = "서비스 관련 소식 관리를 위한 API")
public class NewsController {

    private final JwtTokenProvider jwtTokenProvider;

}
