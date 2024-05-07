package com.rezero.rotto.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.5.3.
 */
@SuppressWarnings("rawtypes")
public class TokenDeletion extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610400806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c806359b910d61461003b578063ff70e84514610057575b600080fd5b6100556004803603810190610050919061021c565b610073565b005b610071600480360381019061006c919061027f565b610125565b005b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036100e2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016100d990610355565b60405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639eea5f668484846040518463ffffffff1660e01b815260040161018293929190610393565b600060405180830381600087803b15801561019c57600080fd5b505af11580156101b0573d6000803e3d6000fd5b50505050505050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006101e9826101be565b9050919050565b6101f9816101de565b811461020457600080fd5b50565b600081359050610216816101f0565b92915050565b600060208284031215610232576102316101b9565b5b600061024084828501610207565b91505092915050565b6000819050919050565b61025c81610249565b811461026757600080fd5b50565b60008135905061027981610253565b92915050565b600080600060608486031215610298576102976101b9565b5b60006102a68682870161026a565b93505060206102b786828701610207565b92505060406102c88682870161026a565b9150509250925092565b600082825260208201905092915050565b7fec98acebb094eba5b4eca78020ec958aec9d8020eca3bcec868ceab092ec9e8560008201527feb8b88eb8ba42e00000000000000000000000000000000000000000000000000602082015250565b600061033f6027836102d2565b915061034a826102e3565b604082019050919050565b6000602082019050818103600083015261036e81610332565b9050919050565b61037e81610249565b82525050565b61038d816101de565b82525050565b60006060820190506103a86000830186610375565b6103b56020830185610384565b6103c26040830184610375565b94935050505056fea26469706673582212205c140cdf3cb218a0304ef39cd6ef8ee35d6225c452cc4cdaa5071e8b06b81bd464736f6c63430008130033";

    private static String librariesLinkedBinary;

    public static final String FUNC_DELETETOKEN = "deleteToken";

    public static final String FUNC_SETSTORAGEADDRESS = "setStorageAddress";

    @Deprecated
    protected TokenDeletion(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TokenDeletion(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TokenDeletion(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TokenDeletion(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> deleteToken(BigInteger code, String _wallet, BigInteger amount) {
        final Function function = new Function(
                FUNC_DELETETOKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(code), 
                new org.web3j.abi.datatypes.Address(160, _wallet), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setStorageAddress(String _addr) {
        final Function function = new Function(
                FUNC_SETSTORAGEADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TokenDeletion load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenDeletion(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TokenDeletion load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenDeletion(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TokenDeletion load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TokenDeletion(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TokenDeletion load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TokenDeletion(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TokenDeletion> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TokenDeletion.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TokenDeletion> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TokenDeletion.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<TokenDeletion> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TokenDeletion.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TokenDeletion> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TokenDeletion.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }
}
