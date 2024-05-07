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
public class TokenDistribute extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610400806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c8063332dfe091461003b57806359b910d614610057575b600080fd5b61005560048036038101906100509190610252565b610073565b005b610071600480360381019061006c91906102a5565b610107565b005b60008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f8548e368484846040518463ffffffff1660e01b81526004016100d0939291906102f0565b600060405180830381600087803b1580156100ea57600080fd5b505af11580156100fe573d6000803e3d6000fd5b50505050505050565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610176576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161016d906103aa565b60405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b600080fd5b6000819050919050565b6101d1816101be565b81146101dc57600080fd5b50565b6000813590506101ee816101c8565b92915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061021f826101f4565b9050919050565b61022f81610214565b811461023a57600080fd5b50565b60008135905061024c81610226565b92915050565b60008060006060848603121561026b5761026a6101b9565b5b6000610279868287016101df565b935050602061028a8682870161023d565b925050604061029b868287016101df565b9150509250925092565b6000602082840312156102bb576102ba6101b9565b5b60006102c98482850161023d565b91505092915050565b6102db816101be565b82525050565b6102ea81610214565b82525050565b600060608201905061030560008301866102d2565b61031260208301856102e1565b61031f60408301846102d2565b949350505050565b600082825260208201905092915050565b7fec98acebb094eba5b4eca78020ec958aec9d8020eca3bcec868ceab092ec9e8560008201527feb8b88eb8ba42e00000000000000000000000000000000000000000000000000602082015250565b6000610394602783610327565b915061039f82610338565b604082019050919050565b600060208201905081810360008301526103c381610387565b905091905056fea264697066735822122016a26a04cf5b6bac702d811f563c1813ddc6e93f2b2a3a8bb51dfa2fbdb944f064736f6c63430008130033";

    private static String librariesLinkedBinary;

    public static final String FUNC_DISTRIBUTETOKEN = "distributeToken";

    public static final String FUNC_SETSTORAGEADDRESS = "setStorageAddress";

    @Deprecated
    protected TokenDistribute(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TokenDistribute(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TokenDistribute(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TokenDistribute(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> distributeToken(BigInteger code, String _wallet, BigInteger amount) {
        final Function function = new Function(
                FUNC_DISTRIBUTETOKEN, 
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
    public static TokenDistribute load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenDistribute(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TokenDistribute load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenDistribute(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TokenDistribute load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TokenDistribute(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TokenDistribute load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TokenDistribute(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TokenDistribute> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TokenDistribute.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TokenDistribute> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TokenDistribute.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<TokenDistribute> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TokenDistribute.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TokenDistribute> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TokenDistribute.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }
}
