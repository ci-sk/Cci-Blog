package org.example.entity.vo.response;

import lombok.Data;

import java.util.Date;

@Data
public class AccountVO {
    String username;
    String email;

    Date time;
    String role;
}
