package org.example.userservice.dto;

import lombok.Data;
import org.example.userservice.vo.ResponseOrder;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private String email;
    private String name;
    private String password;
    private String userId;
    private Date createdAt;
    private String encryptedPassword;

    private List<ResponseOrder> orders;
}
