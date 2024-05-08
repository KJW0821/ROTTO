package com.rezero.rotto.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
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
public class TokenManager extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061002d61002261003260201b60201c565b61003a60201b60201c565b6100fe565b600033905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b610dca8061010d6000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c80638da5cb5b116100665780638da5cb5b146100f6578063cdd1e86e14610114578063d2e470c314610130578063f2fde38b1461014c578063ff70e8451461016857610093565b8063332dfe09146100985780633d4c0bb2146100b457806365d9b0dc146100d0578063715018a6146100ec575b600080fd5b6100b260048036038101906100ad9190610909565b610184565b005b6100ce60048036038101906100c9919061095c565b610293565b005b6100ea60048036038101906100e59190610aba565b610348565b005b6100f46103e3565b005b6100fe6103f7565b60405161010b9190610b09565b60405180910390f35b61012e6004803603810190610129919061095c565b610420565b005b61014a6004803603810190610145919061095c565b6104d5565b005b6101666004803603810190610161919061095c565b61058a565b005b610182600480360381019061017d9190610909565b61060d565b005b61018c61071c565b81600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036101fc576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101f390610ba7565b60405180910390fd5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663332dfe098585856040518463ffffffff1660e01b815260040161025b93929190610bd6565b600060405180830381600087803b15801561027557600080fd5b505af1158015610289573d6000803e3d6000fd5b5050505050505050565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610303576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102fa90610ba7565b60405180910390fd5b81600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b61035061071c565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166365d9b0dc83836040518363ffffffff1660e01b81526004016103ad929190610c6d565b600060405180830381600087803b1580156103c757600080fd5b505af11580156103db573d6000803e3d6000fd5b505050505050565b6103eb61071c565b6103f5600061079a565b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610490576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161048790610ba7565b60405180910390fd5b81600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610545576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161053c90610ba7565b60405180910390fd5b81600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b61059261071c565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610601576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105f890610d08565b60405180910390fd5b61060a8161079a565b50565b61061561071c565b81600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610685576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161067c90610ba7565b60405180910390fd5b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ff70e8458585856040518463ffffffff1660e01b81526004016106e493929190610bd6565b600060405180830381600087803b1580156106fe57600080fd5b505af1158015610712573d6000803e3d6000fd5b5050505050505050565b61072461085e565b73ffffffffffffffffffffffffffffffffffffffff166107426103f7565b73ffffffffffffffffffffffffffffffffffffffff1614610798576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161078f90610d74565b60405180910390fd5b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600033905090565b6000604051905090565b600080fd5b6000819050919050565b61088881610875565b811461089357600080fd5b50565b6000813590506108a58161087f565b92915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006108d6826108ab565b9050919050565b6108e6816108cb565b81146108f157600080fd5b50565b600081359050610903816108dd565b92915050565b60008060006060848603121561092257610921610870565b5b600061093086828701610896565b9350506020610941868287016108f4565b925050604061095286828701610896565b9150509250925092565b60006020828403121561097257610971610870565b5b6000610980848285016108f4565b91505092915050565b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b6109d78261098e565b810181811067ffffffffffffffff821117156109f6576109f561099f565b5b80604052505050565b6000610a09610866565b9050610a1582826109ce565b919050565b600063ffffffff82169050919050565b610a3381610a1a565b8114610a3e57600080fd5b50565b600081359050610a5081610a2a565b92915050565b600060608284031215610a6c57610a6b610989565b5b610a7660606109ff565b90506000610a8684828501610896565b6000830152506020610a9a84828501610896565b6020830152506040610aae84828501610a41565b60408301525092915050565b60008060808385031215610ad157610ad0610870565b5b6000610adf85828601610a56565b9250506060610af085828601610896565b9150509250929050565b610b03816108cb565b82525050565b6000602082019050610b1e6000830184610afa565b92915050565b600082825260208201905092915050565b7fec98acebb094eba5b4eca78020ec958aec9d8020eca3bcec868cec9e85eb8b8860008201527feb8ba42e00000000000000000000000000000000000000000000000000000000602082015250565b6000610b91602483610b24565b9150610b9c82610b35565b604082019050919050565b60006020820190508181036000830152610bc081610b84565b9050919050565b610bd081610875565b82525050565b6000606082019050610beb6000830186610bc7565b610bf86020830185610afa565b610c056040830184610bc7565b949350505050565b610c1681610875565b82525050565b610c2581610a1a565b82525050565b606082016000820151610c416000850182610c0d565b506020820151610c546020850182610c0d565b506040820151610c676040850182610c1c565b50505050565b6000608082019050610c826000830185610c2b565b610c8f6060830184610bc7565b9392505050565b7f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160008201527f6464726573730000000000000000000000000000000000000000000000000000602082015250565b6000610cf2602683610b24565b9150610cfd82610c96565b604082019050919050565b60006020820190508181036000830152610d2181610ce5565b9050919050565b7f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572600082015250565b6000610d5e602083610b24565b9150610d6982610d28565b602082019050919050565b60006020820190508181036000830152610d8d81610d51565b905091905056fea2646970667358221220d6b3687ce4d7a5e9775998b32f290eee82eb446c40051d0f80826ed67157883064736f6c63430008130033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CREATETOKEN = "createToken";

    public static final String FUNC_DELETETOKEN = "deleteToken";

    public static final String FUNC_DISTRIBUTETOKEN = "distributeToken";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SETTOKENCREATIONADDRESS = "setTokenCreationAddress";

    public static final String FUNC_SETTOKENDELETIONADDRESS = "setTokenDeletionAddress";

    public static final String FUNC_SETTOKENDISTRIBUTEADDRESS = "setTokenDistributeAddress";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
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

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnershipTransferredEventResponse getOwnershipTransferredEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
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

    public RemoteFunctionCall<TransactionReceipt> createToken(Subscription subscription, BigInteger amount) {
        final Function function = new Function(
                FUNC_CREATETOKEN, 
                Arrays.<Type>asList(subscription, 
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deleteToken(BigInteger code, String _wallet, BigInteger amount) {
        final Function function = new Function(
                FUNC_DELETETOKEN, 
                Arrays.<Type>asList(new Uint256(code),
                new Address(160, _wallet),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> distributeToken(BigInteger code, String _wallet, BigInteger amount) {
        final Function function = new Function(
                FUNC_DISTRIBUTETOKEN, 
                Arrays.<Type>asList(new Uint256(code),
                new Address(160, _wallet),
                new Uint256(amount)),
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
                Arrays.<Type>asList(new Address(160, _addr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setTokenDeletionAddress(String _addr) {
        final Function function = new Function(
                FUNC_SETTOKENDELETIONADDRESS, 
                Arrays.<Type>asList(new Address(160, _addr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setTokenDistributeAddress(String _addr) {
        final Function function = new Function(
                FUNC_SETTOKENDISTRIBUTEADDRESS, 
                Arrays.<Type>asList(new Address(160, _addr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(160, newOwner)),
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

        public BigInteger confirm_price;

        public BigInteger limit_num;

        public Subscription(BigInteger code, BigInteger confirm_price, BigInteger limit_num) {
            super(new Uint256(code),
                    new Uint256(confirm_price),
                    new Uint32(limit_num));
            this.code = code;
            this.confirm_price = confirm_price;
            this.limit_num = limit_num;
        }

        public Subscription(Uint256 code, Uint256 confirm_price, Uint32 limit_num) {
            super(code, confirm_price, limit_num);
            this.code = code.getValue();
            this.confirm_price = confirm_price.getValue();
            this.limit_num = limit_num.getValue();
        }
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }
}
