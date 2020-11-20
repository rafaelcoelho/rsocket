package com.personal.rsocket.rsocket.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Order
{
    @Id
    private String id;
    private Integer price;
    private String name;
    private String description;
    private String time;
}
