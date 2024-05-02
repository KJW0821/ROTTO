package com.rezero.rotto.api.controller;

import com.rezero.rotto.utils.JwtTokenProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/farm")
@RequiredArgsConstructor
@Tag(name = "Farm 컨트롤러", description = "농장 관리를 위한 API")
public class FarmController {

    private JwtTokenProvider jwtTokenProvider;

}
