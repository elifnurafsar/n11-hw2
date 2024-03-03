package com.MavenOrderCraft.MavenOrderCraft.Controller;

import com.MavenOrderCraft.MavenOrderCraft.Request.BaseRequest;
import org.springframework.http.ResponseEntity;
import java.util.UUID;

public interface BaseController<T extends BaseRequest> {

    ResponseEntity<?> create(T request);

    ResponseEntity<?> getById(UUID id);

    ResponseEntity<?> getAll();
}
