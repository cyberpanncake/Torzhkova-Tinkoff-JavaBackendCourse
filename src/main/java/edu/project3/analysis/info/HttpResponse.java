package edu.project3.analysis.info;

import edu.project3.log.IllegalLogException;

public enum HttpResponse {
    _200(200, "OK"),
    _404(404, "Not found");

    private final int code;
    private final String name;

    HttpResponse(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static HttpResponse toResponse(int code) {
        for (HttpResponse response : HttpResponse.values()) {
            if (response.code == code) {
                return response;
            }
        }
        throw new IllegalLogException("Неизвестный код ответа HTTP");
    }
}
