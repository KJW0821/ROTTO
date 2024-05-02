const TokenCreation = artifacts.require("TokenCreation");

module.exports = function (deployer) {
    deployer.deploy(TokenCreation);
};