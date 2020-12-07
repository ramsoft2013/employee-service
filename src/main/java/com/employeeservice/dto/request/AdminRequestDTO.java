package com.employeeservice.dto.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "This is admin request class")
public class AdminRequestDTO implements Serializable {

    @ApiModelProperty(value = "This is the id of admin")
    private Long id;

    @ApiModelProperty(value = "This is the username of admin")
    private String userName;

    @ApiModelProperty(value = "This is firstName name of admin")
    private String firstName;

    @ApiModelProperty(value = "This is lastName name of admin")
    private String lastName;

    @ApiModelProperty(value = "This is password name of admin")
    private String password;
    
    @ApiModelProperty(value = "This is the email address of admin")
    private String email;

  }






