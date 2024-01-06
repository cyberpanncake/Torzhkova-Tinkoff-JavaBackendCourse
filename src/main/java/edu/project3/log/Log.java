package edu.project3.log;

import java.time.LocalDateTime;

public record Log(
    String ip,
    String userName,
    LocalDateTime date,
    String method,
    String resource,
    String protocol,
    HttpResponse response,
    long fileSize,
    String referer,
    String agent,
    String originUrl) {
}
