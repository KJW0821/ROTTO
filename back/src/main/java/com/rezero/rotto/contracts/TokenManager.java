package com.rezero.rotto.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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
import org.web3j.tuples.generated.Tuple7;
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
public class TokenManager extends Contract {
    public static final String BINARY = "60806040526040518060e0016040528060018152602001600a81526020016127108152602001600081526020016127108152602001600a815260200160058152506001600082015181600001556020820151816001015560408201518160020155606082015181600301556080820151816004015560a0820151816005015560c08201518160060155505034801561009657600080fd5b506100b36100a86100b860201b60201c565b6100c060201b60201c565b610184565b600033905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b611166806101936000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c8063ac9d662211610071578063ac9d66221461012a578063c230cf341461014e578063cdd1e86e1461016a578063d2e470c314610186578063f2fde38b146101a2578063ff70e845146101be576100a9565b80632d571cc4146100ae578063332dfe09146100ca5780633d4c0bb2146100e6578063715018a6146101025780638da5cb5b1461010c575b600080fd5b6100c860048036038101906100c391906109d7565b6101da565b005b6100e460048036038101906100df9190610a62565b6102e1565b005b61010060048036038101906100fb9190610ab5565b6103f0565b005b61010a6104a5565b005b6101146104b9565b6040516101219190610af1565b60405180910390f35b6101326104e2565b6040516101459796959493929190610b1b565b60405180910390f35b61016860048036038101906101639190610ccf565b610512565b005b610184600480360381019061017f9190610ab5565b61054c565b005b6101a0600480360381019061019b9190610ab5565b610601565b005b6101bc60048036038101906101b79190610ab5565b6106b6565b005b6101d860048036038101906101d39190610a62565b610739565b005b6101e2610848565b7fdc9ba1ac4442464a29b740ac82a1971f51e25c7f8f1593cdb051a72f15513cd9336040516102119190610af1565b60405180910390a17f0972694eedc3ab3d42314be8d483e8e3e12a41000189e54a50f494e5bdaffc1f60405161024690610d59565b60405180910390a1600860009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663fc2030216001836040518363ffffffff1660e01b81526004016102ac929190610e96565b600060405180830381600087803b1580156102c657600080fd5b505af11580156102da573d6000803e3d6000fd5b5050505050565b6102e9610848565b81600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610359576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161035090610f32565b60405180910390fd5b600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663332dfe098585856040518463ffffffff1660e01b81526004016103b893929190610f52565b600060405180830381600087803b1580156103d257600080fd5b505af11580156103e6573d6000803e3d6000fd5b5050505050505050565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610460576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161045790610f32565b60405180910390fd5b81600960006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b6104ad610848565b6104b760006108c6565b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b60018060000154908060010154908060020154908060030154908060040154908060050154908060060154905087565b7f5cbd734a096e48f6a155efec0089ec63cb4452ec5569031b316e19cb2046569c816040516105419190611017565b60405180910390a150565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036105bc576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105b390610f32565b60405180910390fd5b81600a60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610671576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161066890610f32565b60405180910390fd5b81600860006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b6106be610848565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff160361072d576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610724906110a4565b60405180910390fd5b610736816108c6565b50565b610741610848565b81600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036107b1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107a890610f32565b60405180910390fd5b600a60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ff70e8458585856040518463ffffffff1660e01b815260040161081093929190610f52565b600060405180830381600087803b15801561082a57600080fd5b505af115801561083e573d6000803e3d6000fd5b5050505050505050565b61085061098a565b73ffffffffffffffffffffffffffffffffffffffff1661086e6104b9565b73ffffffffffffffffffffffffffffffffffffffff16146108c4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108bb90611110565b60405180910390fd5b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600033905090565b6000604051905090565b600080fd5b6000819050919050565b6109b4816109a1565b81146109bf57600080fd5b50565b6000813590506109d1816109ab565b92915050565b6000602082840312156109ed576109ec61099c565b5b60006109fb848285016109c2565b91505092915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610a2f82610a04565b9050919050565b610a3f81610a24565b8114610a4a57600080fd5b50565b600081359050610a5c81610a36565b92915050565b600080600060608486031215610a7b57610a7a61099c565b5b6000610a89868287016109c2565b9350506020610a9a86828701610a4d565b9250506040610aab868287016109c2565b9150509250925092565b600060208284031215610acb57610aca61099c565b5b6000610ad984828501610a4d565b91505092915050565b610aeb81610a24565b82525050565b6000602082019050610b066000830184610ae2565b92915050565b610b15816109a1565b82525050565b600060e082019050610b30600083018a610b0c565b610b3d6020830189610b0c565b610b4a6040830188610b0c565b610b576060830187610b0c565b610b646080830186610b0c565b610b7160a0830185610b0c565b610b7e60c0830184610b0c565b98975050505050505050565b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b610bd882610b8f565b810181811067ffffffffffffffff82111715610bf757610bf6610ba0565b5b80604052505050565b6000610c0a610992565b9050610c168282610bcf565b919050565b600060e08284031215610c3157610c30610b8a565b5b610c3b60e0610c00565b90506000610c4b848285016109c2565b6000830152506020610c5f848285016109c2565b6020830152506040610c73848285016109c2565b6040830152506060610c87848285016109c2565b6060830152506080610c9b848285016109c2565b60808301525060a0610caf848285016109c2565b60a08301525060c0610cc3848285016109c2565b60c08301525092915050565b600060e08284031215610ce557610ce461099c565b5b6000610cf384828501610c1b565b91505092915050565b600082825260208201905092915050565b7f546f6b656e4d616e616765722e637265617465546f6b656e0000000000000000600082015250565b6000610d43601883610cfc565b9150610d4e82610d0d565b602082019050919050565b60006020820190508181036000830152610d7281610d36565b9050919050565b60008160001c9050919050565b6000819050919050565b6000610da3610d9e83610d79565b610d86565b9050919050565b610db3816109a1565b82525050565b60e082016000808301549050610dce81610d90565b610ddb6000860182610daa565b5060018301549050610dec81610d90565b610df96020860182610daa565b5060028301549050610e0a81610d90565b610e176040860182610daa565b5060038301549050610e2881610d90565b610e356060860182610daa565b5060048301549050610e4681610d90565b610e536080860182610daa565b5060058301549050610e6481610d90565b610e7160a0860182610daa565b5060068301549050610e8281610d90565b610e8f60c0860182610daa565b5050505050565b600061010082019050610eac6000830185610db9565b610eb960e0830184610b0c565b9392505050565b7fec98acebb094eba5b4eca78020ec958aec9d8020eca3bcec868cec9e85eb8b8860008201527feb8ba42e00000000000000000000000000000000000000000000000000000000602082015250565b6000610f1c602483610cfc565b9150610f2782610ec0565b604082019050919050565b60006020820190508181036000830152610f4b81610f0f565b9050919050565b6000606082019050610f676000830186610b0c565b610f746020830185610ae2565b610f816040830184610b0c565b949350505050565b60e082016000820151610f9f6000850182610daa565b506020820151610fb26020850182610daa565b506040820151610fc56040850182610daa565b506060820151610fd86060850182610daa565b506080820151610feb6080850182610daa565b5060a0820151610ffe60a0850182610daa565b5060c082015161101160c0850182610daa565b50505050565b600060e08201905061102c6000830184610f89565b92915050565b7f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160008201527f6464726573730000000000000000000000000000000000000000000000000000602082015250565b600061108e602683610cfc565b915061109982611032565b604082019050919050565b600060208201905081810360008301526110bd81611081565b9050919050565b7f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572600082015250565b60006110fa602083610cfc565b9150611105826110c4565b602082019050919050565b60006020820190508181036000830152611129816110ed565b905091905056fea26469706673582212209e6a94bb7e94183521df3775c0d512a19b7dac1eae17012905244cd6eb65d3f264736f6c63430008130033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CREATETOKEN = "createToken";

    public static final String FUNC_DELETETOKEN = "deleteToken";

    public static final String FUNC_DISTRIBUTETOKEN = "distributeToken";

    public static final String FUNC_GETSUBSCRIPTION = "getSubscription";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SETTOKENCREATIONADDRESS = "setTokenCreationAddress";

    public static final String FUNC_SETTOKENDELETIONADDRESS = "setTokenDeletionAddress";

    public static final String FUNC_SETTOKENDISTRIBUTEADDRESS = "setTokenDistributeAddress";

    public static final String FUNC_TESTSUBSCRIPTION = "testSubscription";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event CHECKMSGSENDER_EVENT = new Event("CheckMsgSender", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PRINTSUBSCRIPTION_EVENT = new Event("printSubscription", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Subscription>() {}));
    ;

    public static final Event TESTCREATETOKEN_EVENT = new Event("testCreateToken", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected TokenManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TokenManager(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TokenManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TokenManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<CheckMsgSenderEventResponse> getCheckMsgSenderEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CHECKMSGSENDER_EVENT, transactionReceipt);
        ArrayList<CheckMsgSenderEventResponse> responses = new ArrayList<CheckMsgSenderEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CheckMsgSenderEventResponse typedResponse = new CheckMsgSenderEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CheckMsgSenderEventResponse getCheckMsgSenderEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CHECKMSGSENDER_EVENT, log);
        CheckMsgSenderEventResponse typedResponse = new CheckMsgSenderEventResponse();
        typedResponse.log = log;
        typedResponse.addr = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<CheckMsgSenderEventResponse> checkMsgSenderEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCheckMsgSenderEventFromLog(log));
    }

    public Flowable<CheckMsgSenderEventResponse> checkMsgSenderEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHECKMSGSENDER_EVENT));
        return checkMsgSenderEventFlowable(filter);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnershipTransferredEventResponse getOwnershipTransferredEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
        OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
        typedResponse.log = log;
        typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnershipTransferredEventFromLog(log));
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public static List<PrintSubscriptionEventResponse> getPrintSubscriptionEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PRINTSUBSCRIPTION_EVENT, transactionReceipt);
        ArrayList<PrintSubscriptionEventResponse> responses = new ArrayList<PrintSubscriptionEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PrintSubscriptionEventResponse typedResponse = new PrintSubscriptionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.subscription = (Subscription) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PrintSubscriptionEventResponse getPrintSubscriptionEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PRINTSUBSCRIPTION_EVENT, log);
        PrintSubscriptionEventResponse typedResponse = new PrintSubscriptionEventResponse();
        typedResponse.log = log;
        typedResponse.subscription = (Subscription) eventValues.getNonIndexedValues().get(0);
        return typedResponse;
    }

    public Flowable<PrintSubscriptionEventResponse> printSubscriptionEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPrintSubscriptionEventFromLog(log));
    }

    public Flowable<PrintSubscriptionEventResponse> printSubscriptionEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PRINTSUBSCRIPTION_EVENT));
        return printSubscriptionEventFlowable(filter);
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

    public RemoteFunctionCall<TransactionReceipt> createToken(BigInteger amount) {
        final Function function = new Function(
                FUNC_CREATETOKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<TransactionReceipt> distributeToken(BigInteger code, String _wallet, BigInteger amount) {
        final Function function = new Function(
                FUNC_DISTRIBUTETOKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(code), 
                new org.web3j.abi.datatypes.Address(160, _wallet), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getSubscription(Subscription subscription) {
        final Function function = new Function(
                FUNC_GETSUBSCRIPTION, 
                Arrays.<Type>asList(subscription), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setTokenCreationAddress(String _addr) {
        final Function function = new Function(
                FUNC_SETTOKENCREATIONADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setTokenDeletionAddress(String _addr) {
        final Function function = new Function(
                FUNC_SETTOKENDELETIONADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setTokenDistributeAddress(String _addr) {
        final Function function = new Function(
                FUNC_SETTOKENDISTRIBUTEADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> testSubscription() {
        final Function function = new Function(FUNC_TESTSUBSCRIPTION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TokenManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TokenManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TokenManager load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TokenManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TokenManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TokenManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TokenManager> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TokenManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TokenManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TokenManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<TokenManager> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TokenManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TokenManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TokenManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
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

    public static class CheckMsgSenderEventResponse extends BaseEventResponse {
        public String addr;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class PrintSubscriptionEventResponse extends BaseEventResponse {
        public Subscription subscription;
    }

    public static class TestCreateTokenEventResponse extends BaseEventResponse {
        public String message;
    }
}
