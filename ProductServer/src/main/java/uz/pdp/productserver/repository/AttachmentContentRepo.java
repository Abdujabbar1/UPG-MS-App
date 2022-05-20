package uz.pdp.productserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.productserver.entity.AttachmentContent;

public interface AttachmentContentRepo extends JpaRepository<AttachmentContent, Long> {
    AttachmentContent findByAttachmentId(Long id);
}
