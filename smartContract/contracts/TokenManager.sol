// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

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
    address private tokenCreationAddress;

    event testCreateToken(string message);
    event CheckMsgSender(address addr);

    function setTokenCreationAddress(address _addr) public {
        require(_addr != address(0), unicode"올바르지 않은 주소입니다.");
        tokenCreationAddress = _addr;
    }


    // 토큰 생성
    function createToken(uint amount) external onlyOwner {
        emit CheckMsgSender(msg.sender);
        emit testCreateToken("TokenManager.createToken");
        ITokenCreation(tokenCreationAddress).createToken(testSubscription, amount);
    }
}