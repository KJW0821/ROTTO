const TokenManager = artifacts.require("TokenManager");
const TokenDistribute = artifacts.require("TokenDistribute");

module.exports = async (deployer) => {
    await deployer.deploy(TokenDistribute);
    const ManagerInstance = await TokenManager.deployed();
    const DistributeInstance = await TokenDistribute.deployed();
    await ManagerInstance.setTokenDistributeAddress(DistributeInstance.address);
}