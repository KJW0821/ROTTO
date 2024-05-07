package com.rezero.rotto.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
public class TokenCreation extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506105c2806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c806359b910d61461003b578063fc20302114610057575b600080fd5b6100556004803603810190610050919061025c565b610073565b005b610071600480360381019061006c9190610404565b610125565b005b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036100e2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016100d9906104c8565b60405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b7f0972694eedc3ab3d42314be8d483e8e3e12a41000189e54a50f494e5bdaffc1f60405161015290610534565b60405180910390a160008054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16631b2ef1ca8360000151836040518363ffffffff1660e01b81526004016101b9929190610563565b600060405180830381600087803b1580156101d357600080fd5b505af11580156101e7573d6000803e3d6000fd5b505050505050565b6000604051905090565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610229826101fe565b9050919050565b6102398161021e565b811461024457600080fd5b50565b60008135905061025681610230565b92915050565b600060208284031215610272576102716101f9565b5b600061028084828501610247565b91505092915050565b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6102d78261028e565b810181811067ffffffffffffffff821117156102f6576102f561029f565b5b80604052505050565b60006103096101ef565b905061031582826102ce565b919050565b6000819050919050565b61032d8161031a565b811461033857600080fd5b50565b60008135905061034a81610324565b92915050565b600060e0828403121561036657610365610289565b5b61037060e06102ff565b905060006103808482850161033b565b60008301525060206103948482850161033b565b60208301525060406103a88482850161033b565b60408301525060606103bc8482850161033b565b60608301525060806103d08482850161033b565b60808301525060a06103e48482850161033b565b60a08301525060c06103f88482850161033b565b60c08301525092915050565b600080610100838503121561041c5761041b6101f9565b5b600061042a85828601610350565b92505060e061043b8582860161033b565b9150509250929050565b600082825260208201905092915050565b7fec98acebb094eba5b4eca78020ec958aec9d8020eca3bcec868ceab092ec9e8560008201527feb8b88eb8ba42e00000000000000000000000000000000000000000000000000602082015250565b60006104b2602783610445565b91506104bd82610456565b604082019050919050565b600060208201905081810360008301526104e1816104a5565b9050919050565b7f546f6b656e4372656174696f6e2e637265617465546f6b656e00000000000000600082015250565b600061051e601983610445565b9150610529826104e8565b602082019050919050565b6000602082019050818103600083015261054d81610511565b9050919050565b61055d8161031a565b82525050565b60006040820190506105786000830185610554565b6105856020830184610554565b939250505056fea2646970667358221220c0b7bb31829b190fcd367013e50df1cca4c9816c8b5d8bcd8e71a671e6a5c57164736f6c63430008130033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CREATETOKEN = "createToken";

    public static final String FUNC_SETSTORAGEADDRESS = "setStorageAddress";

    public static final Event TESTCREATETOKEN_EVENT = new Event("testCreateToken", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected TokenCreation(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TokenCreation(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TokenCreation(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TokenCreation(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<TestCreateTokenEventResponse> getTestCreateTokenEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TESTCREATETOKEN_EVENT, transactionReceipt);
        ArrayList<TestCreateTokenEventResponse> responses = new ArrayList<TestCreateTokenEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TestCreateTokenEventResponse typedResponse = new TestCreateTokenEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.message = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TestCreateTokenEventResponse getTestCreateTokenEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TESTCREATETOKEN_EVENT, log);
        TestCreateTokenEventResponse typedResponse = new TestCreateTokenEventResponse();
        typedResponse.log = log;
        typedResponse.message = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<TestCreateTokenEventResponse> testCreateTokenEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTestCreateTokenEventFromLog(log));
    }

    public Flowable<TestCreateTokenEventResponse> testCreateTokenEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TESTCREATETOKEN_EVENT));
        return testCreateTokenEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createToken(Subscription subscription, BigInteger amount) {
        final Function function = new Function(
                FUNC_CREATETOKEN, 
                Arrays.<Type>asList(subscription, 
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
    public static TokenCreation load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenCreation(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TokenCreation load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenCreation(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TokenCreation load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TokenCreation(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TokenCreation load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TokenCreation(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TokenCreation> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TokenCreation.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TokenCreation> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TokenCreation.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<TokenCreation> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TokenCreation.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TokenCreation> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TokenCreation.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class Subscription extends StaticStruct {
        public BigInteger code;

        public BigInteger farm_code;

        public BigInteger confirm_price;

        public BigInteger started_time;

        public BigInteger end_time;

        public BigInteger limit_num;

        public BigInteger return_rate;

        public Subscription(BigInteger code, BigInteger farm_code, BigInteger confirm_price, BigInteger started_time, BigInteger end_time, BigInteger limit_num, BigInteger return_rate) {
            super(new org.web3j.abi.datatypes.generated.Uint256(code), 
                    new org.web3j.abi.datatypes.generated.Uint256(farm_code), 
                    new org.web3j.abi.datatypes.generated.Uint256(confirm_price), 
                    new org.web3j.abi.datatypes.generated.Uint256(started_time), 
                    new org.web3j.abi.datatypes.generated.Uint256(end_time), 
                    new org.web3j.abi.datatypes.generated.Uint256(limit_num), 
                    new org.web3j.abi.datatypes.generated.Uint256(return_rate));
            this.code = code;
            this.farm_code = farm_code;
            this.confirm_price = confirm_price;
            this.started_time = started_time;
            this.end_time = end_time;
            this.limit_num = limit_num;
            this.return_rate = return_rate;
        }

        public Subscription(Uint256 code, Uint256 farm_code, Uint256 confirm_price, Uint256 started_time, Uint256 end_time, Uint256 limit_num, Uint256 return_rate) {
            super(code, farm_code, confirm_price, started_time, end_time, limit_num, return_rate);
            this.code = code.getValue();
            this.farm_code = farm_code.getValue();
            this.confirm_price = confirm_price.getValue();
            this.started_time = started_time.getValue();
            this.end_time = end_time.getValue();
            this.limit_num = limit_num.getValue();
            this.return_rate = return_rate.getValue();
        }
    }

    public static class TestCreateTokenEventResponse extends BaseEventResponse {
        public String message;
    }
}
