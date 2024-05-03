// SPDX-License-Identifier: MIT
pragma solidity ^0.8.25;

import "./MyStructs.sol";
import "./TokenStorage.sol";
import "./interfaces/ITokenCreation.sol";
import "@openzeppelin/contracts/utils/Strings.sol";

contract TokenCreation {
    address private tokenStorageAddress;
    constructor ()  {}

    event testCreateToken(string message);

    function setStorageAddress(address _addr) public {
        require(_addr != address(0), unicode"올바르지 않은 주소값입니다.");
        tokenStorageAddress = _addr;
    }

    function createToken(Subscription memory subscription, uint amount) external {
        emit testCreateToken("TokenCreation.createToken");
        ITokenCreation(tokenStorageAddress).mint(subscription.code, amount);
    }
}