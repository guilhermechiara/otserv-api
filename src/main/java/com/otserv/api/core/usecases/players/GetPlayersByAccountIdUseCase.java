package com.otserv.api.core.usecases.players;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.domain.Player;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.repositories.PlayerRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPlayersByAccountIdUseCase implements
        UseCase<GetPlayersByAccountIdUseCase.InputValues, GetPlayersByAccountIdUseCase.OutputValues> {
    private PlayerRepository playerRepository;

    public GetPlayersByAccountIdUseCase(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(
                this.playerRepository
                        .getByAccountId(input.getId())
                        .orElseThrow(() -> new NotFoundException("Account with id not found"))
        );
    }

    @Value
    public static class InputValues {
        private Long id;
    }

    @Value
    public static class OutputValues {
        private List<Player> players;
    }
}
