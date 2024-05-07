// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

interface ITokenDeletion {
    function deleteToken(uint code, address _wallet, uint amount) external;

    function burn(uint code, address _wallet, uint amount) external;
}