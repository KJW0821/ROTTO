// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

import "../MyStructs.sol";

interface ITokenDistribute {
    function distributeToken(Subscription memory subscription, address _to, uint amount) external;

    function transfer(Subscription memory subscription, address _to, uint amount) external;
}