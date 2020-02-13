package com.blibli.oss.backend.example.command.customer;

import com.blibli.oss.backend.command.Command;
import com.blibli.oss.backend.command.model.customer.CreateCustomerCommandRequest;
import com.blibli.oss.backend.example.web.model.response.customer.CreateCustomerWebResponse;

public interface CreateCustomerCommand extends Command<CreateCustomerCommandRequest, CreateCustomerWebResponse> {

}
