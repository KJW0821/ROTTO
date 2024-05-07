// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

import "./MyStructs.sol";
import "./interfaces/ITokenCreation.sol";
import "./interfaces/ITokenDistribute.sol";
import "./interfaces/ITokenDeletion.sol";
import "./TokenCreation.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract TokenManager is Ownable {
    // 임의의 청약 정보
    Subscription public testSubscription = Subscription({
        code: 1,
        farm_code: 10,
        confirm_price: 10000,
        started_time: 0,
        end_time: 10000,
        limit_num: 10,
        return_rate: 5
    });
    address private tokenCreationAddress;
    address private tokenDistributeAddress;
    address private tokenDeletionAddress;

    event testCreateToken(string message);
    event CheckMsgSender(address addr);

    modifier validAddress(address _addr) {
        require(_addr != address(0), unicode"올바르지 않은 주소입니다.");
        _;
    }

    function setTokenCreationAddress(address _addr) public validAddress(_addr){
        tokenCreationAddress = _addr;
    }

    function setTokenDistributeAddress(address _addr) public validAddress(_addr){
        tokenDistributeAddress = _addr;
    }

    function setTokenDeletionAddress(address _addr) public validAddress(_addr){
        tokenDeletionAddress = _addr;
    }

    // 토큰 생성
    function createToken(uint amount) external onlyOwner {
        emit CheckMsgSender(msg.sender);
        emit testCreateToken("TokenManager.createToken");
        ITokenCreation(tokenCreationAddress).createToken(testSubscription, amount);
    }

    // 토큰 발급
    function distributeToken(uint code, address _wallet, uint amount) external onlyOwner validAddress(_wallet) {
        ITokenDistribute(tokenDistributeAddress).distributeToken(code, _wallet, amount);
    }

    // 토큰 환급(삭제)
    function deleteToken(uint code, address _wallet, uint amount) external onlyOwner validAddress(_wallet) {
        ITokenDeletion(tokenDeletionAddress).deleteToken(code, _wallet, amount);
    }
}