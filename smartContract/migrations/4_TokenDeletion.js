const TokenManager = artifacts.require("TokenManager");
const TokenDeletion = artifacts.require("TokenDeletion");

module.exports = async (deployer) => {
    await deployer.deploy(TokenDeletion);
    const ManagerInstance = await TokenManager.deployed();
    const DeletionInstance = await TokenDeletion.deployed();
    ManagerInstance.setTokenDistributeAddress(DeletionInstance.address);
}