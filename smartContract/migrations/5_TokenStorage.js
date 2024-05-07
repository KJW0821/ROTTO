const TokenCreation = artifacts.require("TokenCreation");
const TokenDistribute = artifacts.require("TokenDistribute");
const TokenDeletion = artifacts.require("TokenDeletion");
const TokenStorage = artifacts.require("TokenStorage");

module.exports = async function (deployer) {
    const CreationInstance = await TokenCreation.deployed();
    const DistributeInstance = await TokenDistribute.deployed();
    const DeletetionInstance = await TokenDeletion.deployed();
    
    await deployer.deploy(TokenStorage, CreationInstance.address, DistributeInstance.address, DeletetionInstance.address);
    const StorageInstance = await TokenStorage.deployed();

    CreationInstance.setStorageAddress(StorageInstance.address);
    DistributeInstance.setStorageAddress(StorageInstance.address);
    DeletetionInstance.setStorageAddress(StorageInstance.address);
};