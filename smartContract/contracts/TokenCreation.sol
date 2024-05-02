// SPDX-License-Identifier: MIT
pragma solidity ^0.8.25;

import "./MyStructs.sol";
import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract TokenCreation is ERC20 {

    mapping(uint => Subscription) public subscriptions;
    mapping(uint => uint) public tokenSupplies;
    constructor () ERC20("Token", "MTK")
    {}

    event testCreateToken(string message);

    function createToken(Subscription memory subscription, uint amount) external payable {
        emit testCreateToken("TokenCreation.CreateToken");
        uint code = subscription.code;
        require(subscriptions[code].code == 0, "Token code already exists");

        subscriptions[code] = subscription;
        tokenSupplies[code] = amount;
        _mint(msg.sender, amount * 10 ** uint(decimals()));
    }
}