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
    public static final String BINARY = "608060405234801561001057600080fd5b5061002d61002261003260201b60201c565b61003a60201b60201c565b6100fe565b600033905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b61150c8061010d6000396000f3fe608060405234801561001057600080fd5b50600436106100b45760003560e01c8063cdd1e86e11610071578063cdd1e86e14610151578063d2e470c31461016d578063d37fc7e614610189578063d67069fe146101a5578063ed8c24b3146101c1578063f2fde38b146101dd576100b4565b806339e899ee146100b95780633d4c0bb2146100d557806360a20c49146100f157806365d9b0dc1461010d578063715018a6146101295780638da5cb5b14610133575b600080fd5b6100d360048036038101906100ce9190610eb5565b6101f9565b005b6100ef60048036038101906100ea9190610eb5565b6102ae565b005b61010b60048036038101906101069190610eb5565b610363565b005b61012760048036038101906101229190611049565b61054f565b005b6101316105ea565b005b61013b6105fe565b6040516101489190611098565b60405180910390f35b61016b60048036038101906101669190610eb5565b610627565b005b61018760048036038101906101829190610eb5565b6106dc565b005b6101a3600480360381019061019e91906110b3565b610791565b005b6101bf60048036038101906101ba9190610eb5565b610980565b005b6101db60048036038101906101d691906110f3565b610a89565b005b6101f760048036038101906101f29190610eb5565b610c7b565b005b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610269576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610260906111c9565b60405180910390fd5b81600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff160361031e576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610315906111c9565b60405180910390fd5b81600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b806000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639cce37c6836040518263ffffffff1660e01b81526004016103c19190611098565b602060405180830381865afa1580156103de573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906104029190611221565b905080610444576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161043b906112c0565b60405180910390fd5b82600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036104b4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104ab906111c9565b60405180910390fd5b6104bc610cfe565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166360a20c49856040518263ffffffff1660e01b81526004016105179190611098565b600060405180830381600087803b15801561053157600080fd5b505af1158015610545573d6000803e3d6000fd5b5050505050505050565b610557610cfe565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166365d9b0dc83836040518363ffffffff1660e01b81526004016105b492919061134f565b600060405180830381600087803b1580156105ce57600080fd5b505af11580156105e2573d6000803e3d6000fd5b505050505050565b6105f2610cfe565b6105fc6000610d7c565b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610697576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161068e906111c9565b60405180910390fd5b81600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff160361074c576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610743906111c9565b60405180910390fd5b81600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b806000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639cce37c6836040518263ffffffff1660e01b81526004016107ef9190611098565b602060405180830381865afa15801561080c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108309190611221565b905080610872576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610869906112c0565b60405180910390fd5b82600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036108e2576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108d9906111c9565b60405180910390fd5b6108ea610cfe565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663d37fc7e686866040518363ffffffff1660e01b8152600401610947929190611378565b600060405180830381600087803b15801561096157600080fd5b505af1158015610975573d6000803e3d6000fd5b505050505050505050565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036109f0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016109e7906111c9565b60405180910390fd5b6109f8610cfe565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663d67069fe836040518263ffffffff1660e01b8152600401610a539190611098565b600060405180830381600087803b158015610a6d57600080fd5b505af1158015610a81573d6000803e3d6000fd5b505050505050565b816000600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639cce37c6836040518263ffffffff1660e01b8152600401610ae79190611098565b602060405180830381865afa158015610b04573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610b289190611221565b905080610b6a576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b61906112c0565b60405180910390fd5b83600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610bda576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610bd1906111c9565b60405180910390fd5b610be2610cfe565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ed8c24b38787876040518463ffffffff1660e01b8152600401610c41939291906113a1565b600060405180830381600087803b158015610c5b57600080fd5b505af1158015610c6f573d6000803e3d6000fd5b50505050505050505050565b610c83610cfe565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610cf2576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610ce99061144a565b60405180910390fd5b610cfb81610d7c565b50565b610d06610e40565b73ffffffffffffffffffffffffffffffffffffffff16610d246105fe565b73ffffffffffffffffffffffffffffffffffffffff1614610d7a576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d71906114b6565b60405180910390fd5b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600033905090565b6000604051905090565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610e8282610e57565b9050919050565b610e9281610e77565b8114610e9d57600080fd5b50565b600081359050610eaf81610e89565b92915050565b600060208284031215610ecb57610eca610e52565b5b6000610ed984828501610ea0565b91505092915050565b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b610f3082610ee7565b810181811067ffffffffffffffff82111715610f4f57610f4e610ef8565b5b80604052505050565b6000610f62610e48565b9050610f6e8282610f27565b919050565b6000819050919050565b610f8681610f73565b8114610f9157600080fd5b50565b600081359050610fa381610f7d565b92915050565b600063ffffffff82169050919050565b610fc281610fa9565b8114610fcd57600080fd5b50565b600081359050610fdf81610fb9565b92915050565b600060608284031215610ffb57610ffa610ee2565b5b6110056060610f58565b9050600061101584828501610f94565b600083015250602061102984828501610f94565b602083015250604061103d84828501610fd0565b60408301525092915050565b600080608083850312156110605761105f610e52565b5b600061106e85828601610fe5565b925050606061107f85828601610f94565b9150509250929050565b61109281610e77565b82525050565b60006020820190506110ad6000830184611089565b92915050565b600080604083850312156110ca576110c9610e52565b5b60006110d885828601610f94565b92505060206110e985828601610ea0565b9150509250929050565b600080600060a0848603121561110c5761110b610e52565b5b600061111a86828701610fe5565b935050606061112b86828701610ea0565b925050608061113c86828701610f94565b9150509250925092565b600082825260208201905092915050565b7fec98acebb094eba5b4eca78020ec958aec9d8020eca3bcec868cec9e85eb8b8860008201527feb8ba42e00000000000000000000000000000000000000000000000000000000602082015250565b60006111b3602483611146565b91506111be82611157565b604082019050919050565b600060208201905081810360008301526111e2816111a6565b9050919050565b60008115159050919050565b6111fe816111e9565b811461120957600080fd5b50565b60008151905061121b816111f5565b92915050565b60006020828403121561123757611236610e52565b5b60006112458482850161120c565b91505092915050565b7fed9788ec9aa9eb9098eca78020ec958aeb8a9420eca780eab091ec9e85eb8b8860008201527feb8ba42e00000000000000000000000000000000000000000000000000000000602082015250565b60006112aa602483611146565b91506112b58261124e565b604082019050919050565b600060208201905081810360008301526112d98161129d565b9050919050565b6112e981610f73565b82525050565b6112f881610fa9565b82525050565b60608201600082015161131460008501826112e0565b50602082015161132760208501826112e0565b50604082015161133a60408501826112ef565b50505050565b61134981610f73565b82525050565b600060808201905061136460008301856112fe565b6113716060830184611340565b9392505050565b600060408201905061138d6000830185611340565b61139a6020830184611089565b9392505050565b600060a0820190506113b660008301866112fe565b6113c36060830185611089565b6113d06080830184611340565b949350505050565b7f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160008201527f6464726573730000000000000000000000000000000000000000000000000000602082015250565b6000611434602683611146565b915061143f826113d8565b604082019050919050565b6000602082019050818103600083015261146381611427565b9050919050565b7f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572600082015250565b60006114a0602083611146565b91506114ab8261146a565b602082019050919050565b600060208201905081810360008301526114cf81611493565b905091905056fea26469706673582212208da96c1fee9401821639d1e64fdf460631199826f046de6d63fb44ebe2d1766464736f6c63430008130033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CREATETOKEN = "createToken";

    public static final String FUNC_DELETETOKEN = "deleteToken";

    public static final String FUNC_DISTRIBUTETOKEN = "distributeToken";

    public static final String FUNC_INSERTLIST = "insertList";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REMOVELIST = "removeList";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SETTOKENCREATIONADDRESS = "setTokenCreationAddress";

    public static final String FUNC_SETTOKENDELETIONADDRESS = "setTokenDeletionAddress";

    public static final String FUNC_SETTOKENDISTRIBUTEADDRESS = "setTokenDistributeAddress";

    public static final String FUNC_SETWHITELIST = "setWhiteList";

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

    public RemoteFunctionCall<TransactionReceipt> deleteToken(BigInteger code, String _wallet) {
        final Function function = new Function(
                FUNC_DELETETOKEN, 
                Arrays.<Type>asList(new Uint256(code),
                new Address(160, _wallet)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> distributeToken(Subscription subscription, String _wallet, BigInteger amount) {
        final Function function = new Function(
                FUNC_DISTRIBUTETOKEN, 
                Arrays.<Type>asList(subscription, 
                new Address(160, _wallet),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> insertList(String _wallet) {
        final Function function = new Function(
                FUNC_INSERTLIST, 
                Arrays.<Type>asList(new Address(160, _wallet)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeList(String _wallet) {
        final Function function = new Function(
                FUNC_REMOVELIST, 
                Arrays.<Type>asList(new Address(160, _wallet)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<TransactionReceipt> setWhiteList(String _addr) {
        final Function function = new Function(
                FUNC_SETWHITELIST, 
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
