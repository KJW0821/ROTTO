const TokenCreation = artifacts.require("TokenCreation");
const TokenStorage = artifacts.require("TokenStorage");

module.exports = async function (deployer) {
    const CreationInstance = await TokenCreation.deployed();
    await deployer.deploy(TokenStorage, CreationInstance.address);
    const StorageInstance = await TokenStorage.deployed();
    CreationInstance.setStorageAddress(StorageInstance.address);
};