package kz.dar.payment.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestModel {

    private String name;
    private String secondName;
    private String city;
}
