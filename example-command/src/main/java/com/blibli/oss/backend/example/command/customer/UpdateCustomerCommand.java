package com.blibli.oss.backend.example.command.customer;

import com.blibli.oss.backend.command.Command;
import com.blibli.oss.backend.command.model.customer.UpdateCustomerCommandRequest;
import com.blibli.oss.backend.example.web.model.response.customer.UpdateCustomerWebResponse;

public interface UpdateCustomerCommand extends Command<UpdateCustomerCommandRequest, UpdateCustomerWebResponse> {

}
