package kz.dar.payment.api.repository.enttiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document("account-entity")
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    @Id
    private String id;
    private String number;
    private String ownerName;
    private String ownerSecondName;
    private double balance;
    private String city;
    private boolean active;
    private List<String> currency;
}
