package Controllers;

import com.google.api.services.storage.Storage;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/gcp")
@RestController
public class GCPstorage {

    @Autowired
    private Storage storage;

    @GetMapping("/send-data")
    public String sendData(){
        BlobId id = BlobId.of("picboxbucket", "name-of-file");
        BlobInfo
    }
}
