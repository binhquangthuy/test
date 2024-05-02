package org.example.entityManage.request.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entityManage.dto.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSearchReq extends Page {
    private String id;
    private String customerName;
}
