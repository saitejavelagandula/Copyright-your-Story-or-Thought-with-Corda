package com.template.flows;

import com.template.helper.OtherParties;
import com.template.states.UploadStoryFileState;
import net.corda.core.crypto.SecureHash;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.StartableByRPC;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.transactions.TransactionBuilder;

import java.util.List;

@StartableByRPC
public class UploadStoryFileFlow extends FlowLogic<String> {

    private String fileUploader;
    private String fileName;
    private SecureHash fileHashId;
    private String orgName;
    private List<AbstractParty> otherParties;

    public UploadStoryFileFlow(String fileUploader, String fileName, SecureHash fileHashId, String orgName, List<AbstractParty> otherParties) {
        this.fileUploader = fileUploader;
        this.fileName = fileName;
        this.fileHashId = fileHashId;
        this.orgName = orgName;
        this.otherParties = otherParties;
    }

    @Override
    public String call() throws FlowException {
        Party notary = getServiceHub().getNetworkParameters().getNotaries().get(0).getIdentity();

        TransactionBuilder transactionBuilder = new TransactionBuilder().addAttachment(fileHashId);

        UploadStoryFileState outputState = new UploadStoryFileState(fileUploader, fileName, fileHashId, orgName, otherParties);

        transactionBuilder.addOutputState(outputState);
        return null;
    }
}
