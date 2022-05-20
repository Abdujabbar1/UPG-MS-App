package uz.pdp.productserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.productserver.entity.Attachment;

public interface AttachmentRepo extends JpaRepository<Attachment, Long> {
}
