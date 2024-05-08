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
    public static final String BINARY = "608060405234801561001057600080fd5b5061002d61002261003260201b60201c565b61003a60201b60201c565b6100fe565b600033905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b610e548061010d6000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c8063cdd1e86e11610066578063cdd1e86e146100f8578063d2e470c314610114578063ed8c24b314610130578063f2fde38b1461014c578063ff70e8451461016857610093565b80633d4c0bb21461009857806365d9b0dc146100b4578063715018a6146100d05780638da5cb5b146100da575b600080fd5b6100b260048036038101906100ad91906108d3565b610184565b005b6100ce60048036038101906100c99190610a67565b610239565b005b6100d86102d4565b005b6100e26102e8565b6040516100ef9190610ab6565b60405180910390f35b610112600480360381019061010d91906108d3565b610311565b005b61012e600480360381019061012991906108d3565b6103c6565b005b61014a60048036038101906101459190610ad1565b61047b565b005b610166600480360381019061016191906108d3565b61058a565b005b610182600480360381019061017d9190610b24565b61060d565b005b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036101f4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101eb90610bfa565b60405180910390fd5b81600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b61024161071c565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166365d9b0dc83836040518363ffffffff1660e01b815260040161029e929190610c89565b600060405180830381600087803b1580156102b857600080fd5b505af11580156102cc573d6000803e3d6000fd5b505050505050565b6102dc61071c565b6102e6600061079a565b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610381576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161037890610bfa565b60405180910390fd5b81600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610436576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161042d90610bfa565b60405180910390fd5b81600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b61048361071c565b81600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036104f3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104ea90610bfa565b60405180910390fd5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ed8c24b38585856040518463ffffffff1660e01b815260040161055293929190610cb2565b600060405180830381600087803b15801561056c57600080fd5b505af1158015610580573d6000803e3d6000fd5b5050505050505050565b61059261071c565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610601576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105f890610d5b565b60405180910390fd5b61060a8161079a565b50565b61061561071c565b81600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610685576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161067c90610bfa565b60405180910390fd5b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ff70e8458585856040518463ffffffff1660e01b81526004016106e493929190610d7b565b600060405180830381600087803b1580156106fe57600080fd5b505af1158015610712573d6000803e3d6000fd5b5050505050505050565b61072461085e565b73ffffffffffffffffffffffffffffffffffffffff166107426102e8565b73ffffffffffffffffffffffffffffffffffffffff1614610798576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161078f90610dfe565b60405180910390fd5b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600033905090565b6000604051905090565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60006108a082610875565b9050919050565b6108b081610895565b81146108bb57600080fd5b50565b6000813590506108cd816108a7565b92915050565b6000602082840312156108e9576108e8610870565b5b60006108f7848285016108be565b91505092915050565b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b61094e82610905565b810181811067ffffffffffffffff8211171561096d5761096c610916565b5b80604052505050565b6000610980610866565b905061098c8282610945565b919050565b6000819050919050565b6109a481610991565b81146109af57600080fd5b50565b6000813590506109c18161099b565b92915050565b600063ffffffff82169050919050565b6109e0816109c7565b81146109eb57600080fd5b50565b6000813590506109fd816109d7565b92915050565b600060608284031215610a1957610a18610900565b5b610a236060610976565b90506000610a33848285016109b2565b6000830152506020610a47848285016109b2565b6020830152506040610a5b848285016109ee565b60408301525092915050565b60008060808385031215610a7e57610a7d610870565b5b6000610a8c85828601610a03565b9250506060610a9d858286016109b2565b9150509250929050565b610ab081610895565b82525050565b6000602082019050610acb6000830184610aa7565b92915050565b600080600060a08486031215610aea57610ae9610870565b5b6000610af886828701610a03565b9350506060610b09868287016108be565b9250506080610b1a868287016109b2565b9150509250925092565b600080600060608486031215610b3d57610b3c610870565b5b6000610b4b868287016109b2565b9350506020610b5c868287016108be565b9250506040610b6d868287016109b2565b9150509250925092565b600082825260208201905092915050565b7fec98acebb094eba5b4eca78020ec958aec9d8020eca3bcec868cec9e85eb8b8860008201527feb8ba42e00000000000000000000000000000000000000000000000000000000602082015250565b6000610be4602483610b77565b9150610bef82610b88565b604082019050919050565b60006020820190508181036000830152610c1381610bd7565b9050919050565b610c2381610991565b82525050565b610c32816109c7565b82525050565b606082016000820151610c4e6000850182610c1a565b506020820151610c616020850182610c1a565b506040820151610c746040850182610c29565b50505050565b610c8381610991565b82525050565b6000608082019050610c9e6000830185610c38565b610cab6060830184610c7a565b9392505050565b600060a082019050610cc76000830186610c38565b610cd46060830185610aa7565b610ce16080830184610c7a565b949350505050565b7f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160008201527f6464726573730000000000000000000000000000000000000000000000000000602082015250565b6000610d45602683610b77565b9150610d5082610ce9565b604082019050919050565b60006020820190508181036000830152610d7481610d38565b9050919050565b6000606082019050610d906000830186610c7a565b610d9d6020830185610aa7565b610daa6040830184610c7a565b949350505050565b7f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572600082015250565b6000610de8602083610b77565b9150610df382610db2565b602082019050919050565b60006020820190508181036000830152610e1781610ddb565b905091905056fea26469706673582212202b030b175e89e55104fa829c9315262b9c9896f3f13ccd6fecea43b5bc8a1e2664736f6c63430008130033";

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

    public RemoteFunctionCall<TransactionReceipt> createToken(Subscription subscription, BigInteger amount) {
        final Function function = new Function(
                FUNC_CREATETOKEN, 
                Arrays.<Type>asList(subscription, 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
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

    public RemoteFunctionCall<TransactionReceipt> distributeToken(Subscription subscription, String _wallet, BigInteger amount) {
        final Function function = new Function(
                FUNC_DISTRIBUTETOKEN, 
                Arrays.<Type>asList(subscription, 
                new org.web3j.abi.datatypes.Address(160, _wallet), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
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

        public BigInteger confirm_price;

        public BigInteger limit_num;

        public Subscription(BigInteger code, BigInteger confirm_price, BigInteger limit_num) {
            super(new org.web3j.abi.datatypes.generated.Uint256(code), 
                    new org.web3j.abi.datatypes.generated.Uint256(confirm_price), 
                    new org.web3j.abi.datatypes.generated.Uint32(limit_num));
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
