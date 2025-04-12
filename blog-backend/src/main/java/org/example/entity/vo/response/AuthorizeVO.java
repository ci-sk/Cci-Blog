package org.example.entity.vo.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
public class AuthorizeVO {
    String username;
    String role;
    String token;
    Date expire;
}
