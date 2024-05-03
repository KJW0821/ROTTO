// SPDX-License-Identifier: MIT
pragma solidity ^0.8.25;

import "../MyStructs.sol";

interface ITokenDistribute {
    function TokenDistribute(address _to, uint amount) external;
}