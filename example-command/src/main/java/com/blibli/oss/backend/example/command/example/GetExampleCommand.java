package com.blibli.oss.backend.example.command.example;

import com.blibli.oss.backend.command.Command;
import com.blibli.oss.backend.command.model.example.GetExampleCommandRequest;
import com.blibli.oss.backend.example.web.model.response.example.GetExampleWebResponse;

public interface GetExampleCommand extends Command<GetExampleCommandRequest, GetExampleWebResponse> {
  
}
