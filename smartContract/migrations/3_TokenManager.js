const TokenManager = artifacts.require("TokenManager");

module.exports = async function (deployer) {
    const tokenCreationInstance = await artifacts.require("TokenCreation").deployed();
    deployer.deploy(TokenManager, tokenCreationInstance.address);
};