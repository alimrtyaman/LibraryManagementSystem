package com.aliyaman.libraryapp.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exception <E>{

    private String hostName;

    private String path;

    private Date createTime;

    private E message;
}
