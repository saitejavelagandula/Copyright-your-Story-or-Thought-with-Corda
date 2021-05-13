package com.template.webserver;

import com.template.helper.OtherParties;
import net.corda.core.messaging.CordaRPCOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Define your API endpoints here.
 */
@RestController
@RequestMapping("/") // The paths for HTTP requests are relative to this base path.
public class Controller {

    @Autowired
    private CordappService cordappService;
    private final CordaRPCOps proxy;
    private final static Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller(NodeRPCConnection rpc) {
        this.proxy = rpc.proxy;
    }

    @GetMapping(value = "/templateendpoint", produces = "text/plain")
    private String templateendpoint() {
        return "Define an endpoint here.";
    }

   @RequestMapping(method = RequestMethod.POST, value = "/uploadStoryFile", consumes = {"multipart/form-data", "application/json"})
    private String uploadStoryFile(@RequestParam(value = "fileData", required = true) MultipartFile fileData,
                                   @RequestParam(value = "fileName", required = true) String fileName,
                                   @RequestParam(value = "fileUploader", required = true) String fileUploader,
                                   @RequestParam(value = "organizationName", required = true) String orgName
                                   ) {
        System.out.println("contrroller");

       return cordappService.UploadStoryFile(fileData, fileUploader, fileName, orgName, proxy);
   }

}