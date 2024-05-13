package kz.halyk.finservice.test.MarketPlace.service;

import kz.halyk.finservice.test.MarketPlace.dto.notifyServices.MessageDto;

public interface MailService {

    void sendMessage(MessageDto messageDto);
}
