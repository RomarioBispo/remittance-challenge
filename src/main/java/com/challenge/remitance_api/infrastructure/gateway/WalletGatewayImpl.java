package com.challenge.remitance_api.infrastructure.gateway;

import com.challenge.remitance_api.businessrule.domain.Wallet;
import com.challenge.remitance_api.businessrule.gateway.WalletGateway;
import com.challenge.remitance_api.infrastructure.exception.UserIdNotFoundExceptionException;
import com.challenge.remitance_api.infrastructure.mapper.WalletMapper;
import com.challenge.remitance_api.infrastructure.repository.WalletRepository;
import com.challenge.remitance_api.infrastructure.repository.entity.WalletEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WalletGatewayImpl implements WalletGateway {
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    @Override
    public Wallet findByPersonId(Integer personId) {
        Optional<WalletEntity> walletEntity = walletRepository.findBypersonId(personId);
        if (!walletEntity.isPresent()){
            throw new UserIdNotFoundExceptionException("User id not found");
        }
        return walletMapper.toDomain(walletEntity.get());
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletEntity savedWallet = walletRepository.save(walletMapper.toEntity(wallet));
        return walletMapper.toDomain(savedWallet);
    }
}
