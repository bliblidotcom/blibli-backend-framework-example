package com.blibli.oss.backend.example.command.binlist;

import com.blibli.oss.backend.command.Command;
import com.blibli.oss.backend.command.model.binlist.GetBinListCommandRequest;
import com.blibli.oss.backend.example.web.model.response.binlist.GetBinListWebResponse;

public interface GetBinListCommand extends Command<GetBinListCommandRequest, GetBinListWebResponse> {

}
