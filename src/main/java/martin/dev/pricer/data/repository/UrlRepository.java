package martin.dev.pricer.data.repository;

import martin.dev.pricer.data.model.Status;
import martin.dev.pricer.data.model.Url;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    List<Url> findAllByStatus(Status status);

    List<Url> findAllByStatusAndCheckedAtIsBefore(Status status, LocalDateTime checkedAt);

    List<Url> findAllByStatusAndCheckedAtIsBeforeOrStatusAndCheckedAtIsNull(Status status, LocalDateTime checkedAt, Status status2);

}
