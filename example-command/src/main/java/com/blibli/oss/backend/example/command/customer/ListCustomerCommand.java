package com.blibli.oss.backend.example.command.customer;

import com.blibli.oss.backend.command.Command;
import com.blibli.oss.backend.command.model.customer.ListCustomerCommandRequest;
import com.blibli.oss.backend.example.web.model.response.customer.ListCustomerWebResponse;

public interface ListCustomerCommand extends Command<ListCustomerCommandRequest, ListCustomerWebResponse> {

}
