package com.personal.rsocket.rsocket.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order
{
    private Integer id;
    private Integer price;
    private String name;
    private String description;
}
