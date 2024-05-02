const tokenStorage = artifacts.require("TokenStorage");
const TokenCreation = artifacts.require("TokenCreation");

module.exports = async function (deployer) {
    const tokenStorageInstance = await tokenStorage.deployed();
    await deployer.deploy(TokenCreation, tokenStorageInstance.address);
};