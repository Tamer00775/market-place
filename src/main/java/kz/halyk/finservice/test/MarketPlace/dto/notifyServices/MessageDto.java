package kz.halyk.finservice.test.MarketPlace.dto.notifyServices;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MessageDto {
    public String header;
    public String sendTo;
    public String from;
    public String message;
    public LocalDateTime messageDate;
}
