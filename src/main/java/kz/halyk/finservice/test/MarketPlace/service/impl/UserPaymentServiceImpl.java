package kz.halyk.finservice.test.MarketPlace.service.impl;


import kz.halyk.finservice.test.MarketPlace.converter.userPayment.UserPaymentDtoConverter;
import kz.halyk.finservice.test.MarketPlace.dto.userPayment.UserPaymentDto;
import kz.halyk.finservice.test.MarketPlace.entity.User;
import kz.halyk.finservice.test.MarketPlace.entity.UserPayment;
import kz.halyk.finservice.test.MarketPlace.exception.MarketPlaceException;
import kz.halyk.finservice.test.MarketPlace.repository.UserPaymentRepository;
import kz.halyk.finservice.test.MarketPlace.service.UserPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPaymentServiceImpl implements UserPaymentService {

    private final UserPaymentRepository userPaymentRepository;

    private final UserPaymentDtoConverter userPaymentDtoConverter;

    @Override
    @Transactional(readOnly = true)
    public UserPaymentDto findById(Long id) {
        return userPaymentDtoConverter.convert(userPaymentRepository.findById(id).orElseThrow(
                () -> new MarketPlaceException(String.format("UserPayment with id %s not found", id),
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST)
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public UserPayment findByUser(User user) {
        return userPaymentRepository.findByUser(user).orElseThrow(
                () -> new MarketPlaceException(String.format("UserPayment with user %b not found", user),
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPaymentDto> findAll() {
        return userPaymentRepository
                .findAll()
                .stream()
                .map(userPaymentDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserPaymentDto> findAll(Pageable pageable) {
        return userPaymentRepository.findAll(pageable).map(userPaymentDtoConverter::convert);
    }

    @Override
    public void deleteById(Long id) {
        userPaymentRepository.deleteById(id);
    }

    @Override
    public UserPayment create(UserPaymentDto dto) {
        UserPayment userPayment = new UserPayment();
        userPayment.setPaymentType(dto.getPaymentType());
        userPayment.setProvider(dto.getProvider());
        userPayment.setAccountNo(dto.getAccountNo());
        userPayment.setExpiry(dto.getExpiry());
        userPayment.setIsDeleted(dto.getIsDeleted());
        userPayment.setUser(dto.getUser());
        return userPayment;
    }
}
