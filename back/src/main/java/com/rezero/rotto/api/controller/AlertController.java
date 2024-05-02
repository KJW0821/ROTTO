package com.rezero.rotto.api.controller;

import com.rezero.rotto.api.service.AlertService;
import com.rezero.rotto.utils.JwtTokenProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Alert 컨트롤러", description = "알림 관리를 위한 API")
public class AlertController {

    private final AlertService alertService;
    private final JwtTokenProvider jwtTokenProvider;

}
