// SPDX-License-Identifier: MIT
pragma solidity ^0.8.25;

import "./MyStructs.sol";
import "./interfaces/ITokenCreation.sol";
import "./TokenCreation.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract TokenManager is Ownable {
    Subscription public testSubscription = Subscription({
        code: 1,
        farm_code: 10,
        confirm_price: 10000,
        started_time: 0,
        end_time: 10000,
        limit_num: 10,
        return_rate: 5
    });

    constructor() Ownable(msg.sender) {
    }

    event testCreateToken(string message);

    // 토큰 생성
    function createToken(address _addr, uint amount) external payable onlyOwner {
        emit testCreateToken("TokenManager.createToken");
        ITokenCreation(_addr).createToken(testSubscription, amount);
    }
}