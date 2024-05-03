// SPDX-License-Identifier: MIT
pragma solidity ^0.8.25;

contract TokenDistribute {
    address tokenStorageAddress;
    
    constructor (address _addr)
    {
        tokenStorageAddress = _addr;
    }
    
    function distributeToken(address _wallet, uint amount) external {
        
    }
}