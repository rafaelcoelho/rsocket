package com.personal.rsocket.rsocket.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order
{
    private String id;
    private Integer price;
    private String name;
    private String description;
    private String time;
}
