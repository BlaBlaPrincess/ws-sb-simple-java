package com.blablaprincess.springboot_simplejava.business.arraycounting.presenters.formatters;

import com.blablaprincess.springboot_simplejava.business.arraycounting.presenters.ArrayCountingAlgorithmsPresenterDto;
import com.blablaprincess.springboot_simplejava.business.arraycounting.presenters.ArrayCountingAlgorithmsPresenterDtoFormatter;
import lombok.var;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "count_alg.presenter.format", havingValue = "text")
public class ArrayCountingAlgorithmsPresenterDtoTextFormatter
        implements ArrayCountingAlgorithmsPresenterDtoFormatter {

    @Override
    public String format(ArrayCountingAlgorithmsPresenterDto data) {
        var builder = new StringBuilder();
        for (var count : data.getCounts().entrySet()) {
            builder.append(String.format("%s: %.2f%n", count.getKey(), count.getValue()));
        }
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

}