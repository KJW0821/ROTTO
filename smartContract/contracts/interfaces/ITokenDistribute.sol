// SPDX-License-Identifier: MIT
pragma solidity ^0.8.25;

import "../MyStructs.sol";

interface ITokenDistribute {
    function distributeToken(uint code, address _to, uint amount) external;

    function transfer(uint code, address _to, uint amount) external;
}