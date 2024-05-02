// SPDX-License-Identifier: MIT
pragma solidity ^0.8.25;

import "./MyStructs.sol";
import "./TokenStorage.sol";
import "./interfaces/ITokenCreation.sol";
import "@openzeppelin/contracts/utils/Strings.sol";

contract TokenCreation {
    address tokenStorageAddress;
    constructor (address _addr)
    {
        tokenStorageAddress = _addr;
    }

    event testCreateToken(string message);

    function createToken(Subscription memory subscription, uint amount) external {
        emit testCreateToken("TokenCreation.createToken");
        ITokenCreation(tokenStorageAddress).mint(subscription.code, amount);
    }
}