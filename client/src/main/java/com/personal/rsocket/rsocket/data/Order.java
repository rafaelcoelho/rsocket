package com.personal.rsocket.rsocket.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order
{
    private String id;
    private Integer price;
    private String name;
    private String description;
    private String time;
}
