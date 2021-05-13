package com.template.webserver;

import com.template.flows.UploadStoryFileFlow;
import com.template.helper.OtherParties;
import net.corda.core.crypto.SecureHash;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.messaging.FlowHandle;
import net.corda.core.node.services.vault.*;
import org.hibernate.QueryException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

@Service
public class CordappService {

    public CordappService() {

    }

    public String UploadStoryFile(MultipartFile fileData, String fileUploader, String fileName, String orgName,  CordaRPCOps proxy)  {
        JSONObject response = new JSONObject();
        try {
            ColumnPredicate<String> columnPredicate = new ColumnPredicate.EqualityComparison<>(EqualityComparisonOperator.EQUAL, fileName);
            AttachmentQueryCriteria attachmentQueryCriteria = new AttachmentQueryCriteria.AttachmentsQueryCriteria().withFilename(columnPredicate);
            System.out.println("cordappservoce");

            List<SecureHash> attachmentIds = proxy.queryAttachments(attachmentQueryCriteria, null);

            if (attachmentIds.size() >= 1) {
                response.put("status", "200");
                response.put("message", "File with name " + fileName + " already exists.");
            }
            else {
                InputStream inputStream = new BufferedInputStream(fileData.getInputStream());

                SecureHash hashId = proxy.uploadAttachmentWithMetadata(inputStream, fileUploader, fileName);

                inputStream.close();
                response.put("status", "200");


//                List<AbstractParty> otherParties = new ArrayList<>();
//                for (int i = 0; i < otherPartyList.getOtherParties().size(); i++) {
//                    CordaX500Name x500Name = CordaX500Name.parse(otherPartyList.getOtherParties().get(i));
//                    otherParties.add(proxy.wellKnownPartyFromX500Name(x500Name));
//                }
//
//                System.out.println("check mapping " +  otherPartyList.getOtherParties());

//                FlowHandle flowHandle = proxy.startFlowDynamic(UploadStoryFileFlow.class, fileUploader, fileName, hashId, orgName, otherParties);

            }
        } catch (QueryException | FileAlreadyExistsException e ) {
            response.put("status", "500");
            response.put("message", e.getMessage());
        } catch (IOException e) {
            response.put("status", "500");
            response.put("message", e.getMessage());
        }
        return response.toString();
    }
}
