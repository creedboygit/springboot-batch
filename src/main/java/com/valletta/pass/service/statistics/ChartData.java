package com.valletta.pass.service.statistics;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ChartData {

    private List<String> labels;
    private List<Long> attendedCounts;
    private List<Long> canceledCounts;
}
