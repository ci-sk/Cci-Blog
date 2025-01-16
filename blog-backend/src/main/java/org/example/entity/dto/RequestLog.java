package org.example.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RequestLog {
    private String time;
    private Integer status;
    private String url;
    private long processingTime;

    public String toJsonString() {
        return String.format("{\"time\":\"%s\",\"status\":%d,\"url\":\"%s\",\"processingTime\":%d}",
                time, status, url, processingTime);
    }
}
