package org.example.entityManage.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entityManage.util.ParserUtils;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSearchedDto {
    private String id;

    private Date createdDate;

    private String nameCustomer;

    private String address;

    private String email;

    private String phoneNumber;

    private Integer status;

    private Long paidMoney;

    public OrderSearchedDto(Object[] fields) {
        this.id = ParserUtils.parseObjectToString(fields[0]);
        this.createdDate = ParserUtils.parseStringToDate(fields[1]);
        this.nameCustomer = ParserUtils.parseObjectToString(fields[2]);
        this.address = ParserUtils.parseObjectToString(fields[4]);
        this.email = ParserUtils.parseObjectToString(fields[5]);
        this.phoneNumber = ParserUtils.parseObjectToString(fields[6]);
        this.status = ParserUtils.parseObjectToInteger(fields[7]);
        this.paidMoney = ParserUtils.parseObjectToLong(fields[8]);
    }
}
