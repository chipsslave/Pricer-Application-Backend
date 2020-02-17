package martin.dev.pricer.data.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import martin.dev.pricer.data.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Price extends BaseEntity {

    private Double price;
    private Double delta;
    private LocalDateTime foundAt;

//    @NotNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                ", delta=" + delta +
                ", foundAt=" + foundAt +
                '}';
    }
}
