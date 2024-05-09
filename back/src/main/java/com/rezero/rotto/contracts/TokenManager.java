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
    public static final String BINARY = "608060405234801561001057600080fd5b5061002d61002261003260201b60201c565b61003a60201b60201c565b6100fe565b600033905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b61141e8061010d6000396000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c8063cdd1e86e11610071578063cdd1e86e1461012a578063d2e470c314610146578063d37fc7e614610162578063d67069fe1461017e578063ed8c24b31461019a578063f2fde38b146101b6576100a9565b80633d4c0bb2146100ae57806360a20c49146100ca57806365d9b0dc146100e6578063715018a6146101025780638da5cb5b1461010c575b600080fd5b6100c860048036038101906100c39190610dc7565b6101d2565b005b6100e460048036038101906100df9190610dc7565b610287565b005b61010060048036038101906100fb9190610f5b565b61046d565b005b61010a610508565b005b61011461051c565b6040516101219190610faa565b60405180910390f35b610144600480360381019061013f9190610dc7565b610545565b005b610160600480360381019061015b9190610dc7565b6105fa565b005b61017c60048036038101906101779190610fc5565b6106af565b005b61019860048036038101906101939190610dc7565b610898565b005b6101b460048036038101906101af9190611005565b6109a1565b005b6101d060048036038101906101cb9190610dc7565b610b8d565b005b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610242576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610239906110db565b60405180910390fd5b81600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b80600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639cce37c6826040518263ffffffff1660e01b81526004016102e39190610faa565b602060405180830381865afa158015610300573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103249190611133565b610363576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161035a906111d2565b60405180910390fd5b81600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036103d3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103ca906110db565b60405180910390fd5b6103db610c10565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166360a20c49846040518263ffffffff1660e01b81526004016104369190610faa565b600060405180830381600087803b15801561045057600080fd5b505af1158015610464573d6000803e3d6000fd5b50505050505050565b610475610c10565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166365d9b0dc83836040518363ffffffff1660e01b81526004016104d2929190611261565b600060405180830381600087803b1580156104ec57600080fd5b505af1158015610500573d6000803e3d6000fd5b505050505050565b610510610c10565b61051a6000610c8e565b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036105b5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105ac906110db565b60405180910390fd5b81600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff160361066a576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610661906110db565b60405180910390fd5b81600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b80600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639cce37c6826040518263ffffffff1660e01b815260040161070b9190610faa565b602060405180830381865afa158015610728573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061074c9190611133565b61078b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610782906111d2565b60405180910390fd5b81600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036107fb576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107f2906110db565b60405180910390fd5b610803610c10565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663d37fc7e685856040518363ffffffff1660e01b815260040161086092919061128a565b600060405180830381600087803b15801561087a57600080fd5b505af115801561088e573d6000803e3d6000fd5b5050505050505050565b80600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610908576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016108ff906110db565b60405180910390fd5b610910610c10565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663d67069fe836040518263ffffffff1660e01b815260040161096b9190610faa565b600060405180830381600087803b15801561098557600080fd5b505af1158015610999573d6000803e3d6000fd5b505050505050565b81600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639cce37c6826040518263ffffffff1660e01b81526004016109fd9190610faa565b602060405180830381865afa158015610a1a573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610a3e9190611133565b610a7d576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a74906111d2565b60405180910390fd5b82600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610aed576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610ae4906110db565b60405180910390fd5b610af5610c10565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ed8c24b38686866040518463ffffffff1660e01b8152600401610b54939291906112b3565b600060405180830381600087803b158015610b6e57600080fd5b505af1158015610b82573d6000803e3d6000fd5b505050505050505050565b610b95610c10565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1603610c04576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610bfb9061135c565b60405180910390fd5b610c0d81610c8e565b50565b610c18610d52565b73ffffffffffffffffffffffffffffffffffffffff16610c3661051c565b73ffffffffffffffffffffffffffffffffffffffff1614610c8c576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610c83906113c8565b60405180910390fd5b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600033905090565b6000604051905090565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610d9482610d69565b9050919050565b610da481610d89565b8114610daf57600080fd5b50565b600081359050610dc181610d9b565b92915050565b600060208284031215610ddd57610ddc610d64565b5b6000610deb84828501610db2565b91505092915050565b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b610e4282610df9565b810181811067ffffffffffffffff82111715610e6157610e60610e0a565b5b80604052505050565b6000610e74610d5a565b9050610e808282610e39565b919050565b6000819050919050565b610e9881610e85565b8114610ea357600080fd5b50565b600081359050610eb581610e8f565b92915050565b600063ffffffff82169050919050565b610ed481610ebb565b8114610edf57600080fd5b50565b600081359050610ef181610ecb565b92915050565b600060608284031215610f0d57610f0c610df4565b5b610f176060610e6a565b90506000610f2784828501610ea6565b6000830152506020610f3b84828501610ea6565b6020830152506040610f4f84828501610ee2565b60408301525092915050565b60008060808385031215610f7257610f71610d64565b5b6000610f8085828601610ef7565b9250506060610f9185828601610ea6565b9150509250929050565b610fa481610d89565b82525050565b6000602082019050610fbf6000830184610f9b565b92915050565b60008060408385031215610fdc57610fdb610d64565b5b6000610fea85828601610ea6565b9250506020610ffb85828601610db2565b9150509250929050565b600080600060a0848603121561101e5761101d610d64565b5b600061102c86828701610ef7565b935050606061103d86828701610db2565b925050608061104e86828701610ea6565b9150509250925092565b600082825260208201905092915050565b7fec98acebb094eba5b4eca78020ec958aec9d8020eca3bcec868cec9e85eb8b8860008201527feb8ba42e00000000000000000000000000000000000000000000000000000000602082015250565b60006110c5602483611058565b91506110d082611069565b604082019050919050565b600060208201905081810360008301526110f4816110b8565b9050919050565b60008115159050919050565b611110816110fb565b811461111b57600080fd5b50565b60008151905061112d81611107565b92915050565b60006020828403121561114957611148610d64565b5b60006111578482850161111e565b91505092915050565b7fed9788ec9aa9eb9098eca78020ec958aeb8a9420eca780eab091ec9e85eb8b8860008201527feb8ba42e00000000000000000000000000000000000000000000000000000000602082015250565b60006111bc602483611058565b91506111c782611160565b604082019050919050565b600060208201905081810360008301526111eb816111af565b9050919050565b6111fb81610e85565b82525050565b61120a81610ebb565b82525050565b60608201600082015161122660008501826111f2565b50602082015161123960208501826111f2565b50604082015161124c6040850182611201565b50505050565b61125b81610e85565b82525050565b60006080820190506112766000830185611210565b6112836060830184611252565b9392505050565b600060408201905061129f6000830185611252565b6112ac6020830184610f9b565b9392505050565b600060a0820190506112c86000830186611210565b6112d56060830185610f9b565b6112e26080830184611252565b949350505050565b7f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160008201527f6464726573730000000000000000000000000000000000000000000000000000602082015250565b6000611346602683611058565b9150611351826112ea565b604082019050919050565b6000602082019050818103600083015261137581611339565b9050919050565b7f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572600082015250565b60006113b2602083611058565b91506113bd8261137c565b602082019050919050565b600060208201905081810360008301526113e1816113a5565b905091905056fea26469706673582212203f2a89a4b1ae608ac8f1284e257621c75e453620851c1906de756ca893077f9a64736f6c63430008130033";

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

    public RemoteFunctionCall<TransactionReceipt> deleteToken(BigInteger code, String _wallet) {
        final Function function = new Function(
                FUNC_DELETETOKEN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(code), 
                new org.web3j.abi.datatypes.Address(160, _wallet)), 
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

    public RemoteFunctionCall<TransactionReceipt> insertList(String _wallet) {
        final Function function = new Function(
                FUNC_INSERTLIST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _wallet)), 
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
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _wallet)), 
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
