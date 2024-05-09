const TokenManager = artifacts.require("TokenManager");
const TokenCreation = artifacts.require("TokenCreation");

module.exports = async function (deployer) {
    await deployer.deploy(TokenCreation);
    const ManagerInstance = await TokenManager.deployed();
    const CreationInstance = await TokenCreation.deployed();
    await ManagerInstance.setTokenCreationAddress(CreationInstance.address);
}