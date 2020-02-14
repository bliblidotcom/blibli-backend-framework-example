package com.blibli.oss.backend.example.command.impl.binlist;

import com.blibli.oss.backend.command.model.binlist.GetBinListCommandRequest;
import com.blibli.oss.backend.example.client.BinListApiClient;
import com.blibli.oss.backend.example.client.model.BinResponse;
import com.blibli.oss.backend.example.command.binlist.GetBinListCommand;
import com.blibli.oss.backend.example.web.model.response.binlist.GetBinListWebResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class GetBinListCommandImpl implements GetBinListCommand {

  @Autowired
  private BinListApiClient binListApiClient;

  @Override
  public Mono<GetBinListWebResponse> execute(GetBinListCommandRequest request) {
    return binListApiClient.lookup(request.getNumber())
      .map(this::toWebResponse);
  }

  private GetBinListWebResponse toWebResponse(BinResponse response) {
    GetBinListWebResponse webResponse = new GetBinListWebResponse();
    BeanUtils.copyProperties(response, webResponse);

    Optional.ofNullable(response.getBank()).ifPresent(bank -> {
      GetBinListWebResponse.Bank bankResponse = new GetBinListWebResponse.Bank();
      BeanUtils.copyProperties(bank, bankResponse);
      webResponse.setBank(bankResponse);
    });

    Optional.ofNullable(response.getCountry()).ifPresent(country -> {
      GetBinListWebResponse.Country countryResponse = new GetBinListWebResponse.Country();
      BeanUtils.copyProperties(country, countryResponse);
      webResponse.setCountry(countryResponse);
    });

    Optional.ofNullable(response.getNumber()).ifPresent(number -> {
      GetBinListWebResponse.Number numberResponse = new GetBinListWebResponse.Number();
      BeanUtils.copyProperties(number, numberResponse);
      webResponse.setNumber(numberResponse);
    });

    return webResponse;
  }
}
