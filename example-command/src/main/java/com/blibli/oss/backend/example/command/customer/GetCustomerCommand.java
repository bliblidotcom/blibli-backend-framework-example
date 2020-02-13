package com.blibli.oss.backend.example.command.customer;

import com.blibli.oss.backend.command.Command;
import com.blibli.oss.backend.command.model.customer.GetCustomerCommandRequest;
import com.blibli.oss.backend.example.web.model.response.customer.GetCustomerWebResponse;

public interface GetCustomerCommand extends Command<GetCustomerCommandRequest, GetCustomerWebResponse> {

}
