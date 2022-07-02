package com.pro.lesson_10.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private String number;
    private Integer floor;
    private String size;
    private Long hotelId;
}
