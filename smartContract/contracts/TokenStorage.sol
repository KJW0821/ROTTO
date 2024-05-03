// SPDX-License-Identifier: MIT
pragma solidity ^0.8.25;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract TokenStorage is ERC20 {
    mapping(uint => bool) public isExists; // 청약 코드에 맞는 token이 현재 발급되었는지 유무 확인
    mapping(uint => uint) public tokenSupplies; // 청약 코드에 맞는 token이 현재 몇개 소유중인지 확인.
    mapping(address => mapping(uint => uint)) ownToken;

    constructor () ERC20("RoastingToken", "ROTTO") {}

    event testEvent(string message);

    // 토큰 생성
    function mint(uint code, uint amount) external {
        emit testEvent("TokenStorage.mint");
        emit testAddress(msg.sender);
        
        require(isExists[code] != true, "Token is already exists");

        isExists[code] = true;
        tokenSupplies[code] = amount;
        _mint(msg.sender, amount);
    }

    // 청약 코드에 맞는 token의 남은 개수 조회
    function leftover(uint code) external view returns(uint) {
        require(isExists[code], "Token is not created");
        return tokenSupplies[code];
    }
    
}