// SPDX-License-Identifier: MIT
pragma solidity ^0.8.25;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/access/Ownable.sol";
import "@openzeppelin/contracts/access/AccessControl.sol";

contract TokenStorage is ERC20, Ownable, AccessControl {
    bytes32 public constant MINTER_ROLE = keccak256("MINTER_ROLE"); // 토큰 생성 role
    bytes32 public constant BURNER_ROLE = keccak256("BURNER_ROLE"); // 토큰 burn role

    mapping(uint => bool) public isExists; // 청약 코드에 맞는 token이 현재 발급되었는지 유무 확인
    mapping(uint => uint) public tokenSupplies; // 청약 코드에 맞는 token이 현재 몇개 소유중인지 확인.
    mapping(address => mapping(uint => uint)) ownToken;

    constructor (address _minter) ERC20("RoastingToken", "ROTTO") Ownable(msg.sender) {
        _grantRole(MINTER_ROLE, _minter); // TokenCreation contract에 토큰 생성 역할 부여
    }

    event testEvent(string message);
    event testAddress(address addr);
    
    // 토큰 생성
    function mint(uint code, uint amount) external {
        emit testEvent("TokenStorage.mint");
        emit testAddress(msg.sender);
        require(hasRole(MINTER_ROLE, msg.sender) || msg.sender == owner(), unicode"요청 권한이 없습니다.");
        require(isExists[code] != true, unicode"이미 생성된 토큰입니다.");

        isExists[code] = true;
        tokenSupplies[code] = amount;
        _mint(owner(), amount);
    }

    // 청약 코드에 맞는 token의 남은 개수 조회
    function leftover(uint code) external view returns(uint) {
        require(isExists[code], "Token is not created");
        return tokenSupplies[code];
    }
    
}