const TokenCreation = artifacts.require("TokenCreation");
const TokenManager = artifacts.require("TokenManager")

module.exports = async function (deployer) {
    const ManagerInstance = await TokenManager.deployed();
    await deployer.deploy(TokenStorage, ManagerInstance.address);

    const StorageInstance = await TokenStorage.deployed();
    await ManagerInstance.setStorageAddress(StorageInstance.address);
};