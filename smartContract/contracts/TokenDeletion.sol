// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

import "./interfaces/ITokenDeletion.sol";

contract TokenDeletion {
    address private tokenStorageAddress;

    function setStorageAddress(address _addr) public {
        require(_addr != address(0), unicode"올바르지 않은 주소값입니다.");
        tokenStorageAddress = _addr;
    }

    function deleteToken(uint code, address _wallet) external {
        ITokenDeletion(tokenStorageAddress).burn(code, _wallet);
    }
}