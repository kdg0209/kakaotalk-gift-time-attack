package com.kakaotalk.gift.apis.openroom.api;

import com.kakaotalk.gift.apis.openroom.dto.OpenRoomCreateRequest;
import com.kakaotalk.gift.apis.openroom.dto.OpenRoomCreateResponse;
import com.kakaotalk.gift.apis.openroom.dto.OpenRoomJoinRequest;
import com.kakaotalk.gift.apis.openroom.dto.OpenRoomJoinResponse;
import com.kakaotalk.gift.domain.openroom.application.OpenRoomService;
import com.kakaotalk.gift.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.kakaotalk.gift.global.response.ResponseStatus.CODE_201;

@RestController
@RequestMapping("/open-rooms")
@RequiredArgsConstructor
public class OpenRoomApi {

    private final OpenRoomService openRoomService;

    @PostMapping
    public BaseResponse<OpenRoomCreateResponse> create(@Valid @RequestBody OpenRoomCreateRequest request) {
        String result = openRoomService.create(request.getMemberIdx());
        return new BaseResponse<>(CODE_201, new OpenRoomCreateResponse(result));
    }

    @PostMapping("/join")
    public BaseResponse<OpenRoomJoinResponse> join(@Valid @RequestBody OpenRoomJoinRequest request) {
        Long result = openRoomService.join(request.getMemberIdx(), request.getParticipationCode());
        return new BaseResponse<>(CODE_201, new OpenRoomJoinResponse(result));
    }
}
