package kz.halyk.finservice.test.MarketPlace.service.impl;

import kz.halyk.finservice.test.MarketPlace.dto.notifyServices.MessageDto;
import kz.halyk.finservice.test.MarketPlace.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMessage(MessageDto messageDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(messageDto.getHeader());
        simpleMailMessage.setTo(messageDto.getSendTo());
        simpleMailMessage.setText(messageDto.getMessage());

        javaMailSender.send(simpleMailMessage);
    }

}
