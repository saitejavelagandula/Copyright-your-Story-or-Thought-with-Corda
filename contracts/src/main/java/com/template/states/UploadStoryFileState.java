package com.template.states;

import com.sun.javafx.collections.ImmutableObservableList;
import com.template.helper.OtherParties;
import jdk.nashorn.internal.ir.annotations.Immutable;
import net.corda.core.contracts.ContractState;
import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.crypto.SecureHash;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.schemas.CommonSchemaV1;
import net.corda.core.schemas.MappedSchema;
import net.corda.core.schemas.PersistentState;
import net.corda.core.schemas.QueryableState;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

public class UploadStoryFileState implements LinearState, QueryableState {

    private String fileUploader;
    private String fileName;
    private SecureHash fileHashId;
    private String orgName;
    private List<AbstractParty> otherPartyList;

    public UploadStoryFileState(String fileUploader, String fileName, SecureHash fileHashId, String orgName, List<AbstractParty> otherPartyList) {
        this.fileUploader = fileUploader;
        this.fileName = fileName;
        this.fileHashId = fileHashId;
        this.orgName = orgName;
        this.otherPartyList = otherPartyList;
    }

    @NotNull
    @Override
    public PersistentState generateMappedObject(@NotNull MappedSchema schema) {
        return null;
    }

    @NotNull
    @Override
    public Iterable<MappedSchema> supportedSchemas() {
        return null;
    }

    @NotNull
    @Override
    public UniqueIdentifier getLinearId() {
        return null;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {

        return otherPartyList;
    }
}
