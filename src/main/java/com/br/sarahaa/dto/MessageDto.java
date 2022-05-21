package com.br.sarahaa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto implements Serializable {

    private Integer id;

    private LocalDateTime createdDate;

    @NotBlank(message = "You need to write a message")
    @NotNull
    private String content;

    private boolean favori;

    private boolean publicMsg;

    private String typeMsg;

    private Integer senderId;

    @NotNull(message = "You need to select the receiver")
    private Integer receiverId;
}
