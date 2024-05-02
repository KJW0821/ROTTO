// SPDX-License-Identifier: MIT
pragma solidity ^0.8.25;

import "./MyStructs.sol";
import "./interfaces/ITokenCreation.sol";
import "./TokenCreation.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract TokenManager is Ownable {
    // 임의의 청약 정보
    Subscription public testSubscription = Subscription({
        code: 1,
        farm_code: 10,
        confirm_price: 10000,
        started_time: 0,
        end_time: 10000,
        limit_num: 10,
        return_rate: 5
    });
    address tokenCreationAddress;


    constructor(address _addr) Ownable(msg.sender) {
        tokenCreationAddress = _addr;
    }

    event testCreateToken(string message);
    event CheckMsgSender(address addr);

    // 토큰 생성
    function createToken(uint amount) external onlyOwner {
        emit CheckMsgSender(msg.sender);
        emit testCreateToken("TokenManager.createToken");
        ITokenCreation(tokenCreationAddress).createToken(testSubscription, amount);
    }
}