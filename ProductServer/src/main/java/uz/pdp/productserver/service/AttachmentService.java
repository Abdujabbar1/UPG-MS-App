package uz.pdp.productserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.productserver.common.ApiResponse;
import uz.pdp.productserver.entity.Attachment;
import uz.pdp.productserver.entity.AttachmentContent;
import uz.pdp.productserver.repository.AttachmentContentRepo;
import uz.pdp.productserver.repository.AttachmentRepo;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentService {
    private final AttachmentRepo attachmentRepo;
    private final AttachmentContentRepo contentRepo;

    public ApiResponse attachmentById(Long attachmentId) {
        Optional<Attachment> byId = attachmentRepo.findById(attachmentId);
        return byId.map(attachment -> new ApiResponse("Success!", true, attachment))
                .orElseGet(() -> new ApiResponse("Attachment not found!", false));
    }


    public ApiResponse fileUpload(MultipartFile file) throws IOException {
        Attachment attachment = attachmentRepo.save(new Attachment(file.getOriginalFilename(), file.getContentType(), file.getSize()));
        contentRepo.save(new AttachmentContent(file.getBytes(), attachment));
        return new ApiResponse("Success!", true);
    }

    public ApiResponse editFile(Long attachmentId, MultipartFile file) {
        Optional<Attachment> optionalAttachment = attachmentRepo.findById(attachmentId);
        if (optionalAttachment.isEmpty()) {
            return new ApiResponse("Attachment Not Found!", false);
        }
        try {
            Attachment editAttachment = optionalAttachment.get();
            editAttachment.setOriginName(file.getOriginalFilename());
            editAttachment.setContentType(file.getContentType());
            editAttachment.setSize(file.getSize());
            Attachment attachment = attachmentRepo.save(editAttachment);

            AttachmentContent editAttachmentContent = contentRepo.getById(attachmentId);
            editAttachmentContent.setAttachment(attachment);
            editAttachmentContent.setBytes(file.getBytes());
            contentRepo.save(editAttachmentContent);
            return new ApiResponse("Success!", true);
        } catch (IOException e) {
            return new ApiResponse("Error!", false);
        }
    }

    public ApiResponse deleteFile(Long attachmentId) {
        Optional<Attachment> optionalAttachment = attachmentRepo.findById(attachmentId);
        if (optionalAttachment.isEmpty()) {
            return new ApiResponse("Attachment not Found", false);
        }
        try {
            AttachmentContent attachmentContent = contentRepo.getById(attachmentId);
            contentRepo.delete(attachmentContent);
            attachmentRepo.delete(optionalAttachment.get());
            return new ApiResponse("Success!", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }
}
