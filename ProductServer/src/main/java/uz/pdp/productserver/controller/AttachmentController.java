package uz.pdp.productserver.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.productserver.common.ApiResponse;
import uz.pdp.productserver.service.AttachmentService;

import java.io.IOException;

@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/upload")
    public HttpEntity<?> fileUpload(MultipartFile file) throws IOException {
        ApiResponse apiResponse = attachmentService.fileUpload(file);
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 400).body(apiResponse);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteFile(@PathVariable Long id) {
        ApiResponse delete = attachmentService.deleteFile(id);
        return ResponseEntity.status(delete.isStatus() ? 200 : 400).body(delete);
    }
}
