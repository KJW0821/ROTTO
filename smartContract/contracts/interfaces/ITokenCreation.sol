// SPDX-License-Identifier: MIT
pragma solidity ^0.8.25;

import "../MyStructs.sol";

interface ITokenCreation {
    function createToken(Subscription memory subscription, uint amount) external;

    function mint(uint code, uint amount) external;
}