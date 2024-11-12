package kz.dar.payment.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String ownerName;
    private String ownerSecondName;
    private String city;
    private double balance;
    private List<String> currency;
}
