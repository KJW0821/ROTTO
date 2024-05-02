// SPDX-License-Identifier: MIT
pragma solidity ^0.8.25;

// 청약
struct Subscription {
    uint code;  // 청약 코드
    uint farm_code; // 농장 코드
    uint confirm_price; // 공모가
    uint started_time; // 청약 시작일시
    uint end_time; // 청약 종료일시
    uint limit_num; // 제한갯수 (1인당 최대 토큰 구매 개수)
    uint return_rate; // 수익률
}

// 토큰 정보
struct TokenInfo {
    string tokenName; // 토큰명
    string currencySymbol; // 통화 기호
}
