package com.blablaprincess.springboot_simplejava.business.methodcalls;

import com.blablaprincess.springboot_simplejava.business.common.persistence.OptionalPredicateBuilder;
import com.blablaprincess.springboot_simplejava.business.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MethodCallsHistoryService implements MethodCallsHistory {

    private final MethodCallDescriptionsRepository repository;
    private final StringUtils stringUtils;

    private final QMethodCallDescriptionEntity qEntity = QMethodCallDescriptionEntity.methodCallDescriptionEntity;

    @Override
    @Transactional
    @Async("controllerCallsHistoryServiceTaskExecutor")
    public void saveCall(MethodCallDescriptionEntity call) {
        String croppedArgs = stringUtils.cropByMaxLength(call.getArgs(), MethodCallDescriptionEntity.MAX_ARGS_LENGTH);
        call.setArgs(croppedArgs);
        String croppedResponse = stringUtils.cropByMaxLength(call.getResponse(), MethodCallDescriptionEntity.MAX_RESPONSE_LENGTH);
        call.setResponse(croppedResponse);
        repository.save(call);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MethodCallDescriptionEntity> getLastCalls(MethodCallsHistoryLastCallsArgs args) {
        Predicate predicate
                = new OptionalPredicateBuilder().optionalAnd(args.getTimestampAfter(), qEntity.timestamp::goe)
                .optionalAnd(args.getTimestampBefore(), qEntity.timestamp::loe)
                .build();

        return Lists.newArrayList(repository.findAll(predicate));
    }

}
