package com.pik.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDto {
    private String senderId;
    private String recipientId;
    private String advertisementId;
    private String content;
}
