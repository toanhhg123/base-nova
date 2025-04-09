package com.example.demo.core.entities.command;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BaseEntityPredicates {

    public BooleanBuilder search(String keyword, SearchType searchType, StringPath... fields) {
        BooleanBuilder builder = new BooleanBuilder();
        if (keyword != null && !keyword.isEmpty() && fields != null && fields.length > 0) {
            var combinedPredicate = createPredicate(fields[0], keyword, searchType);
            for (int i = 1; i < fields.length; i++) {
                combinedPredicate = combinedPredicate.or(fields[i].containsIgnoreCase(keyword));
            }
            builder.and(combinedPredicate);
        }
        return builder;
    }

    private static BooleanExpression createPredicate(StringPath path, String keyword, SearchType searchType) {
        return switch (searchType) {
            case STARTS_WITH -> path.startsWithIgnoreCase(keyword);
            case ENDS_WITH -> path.endsWithIgnoreCase(keyword);
            case EQUALS -> path.equalsIgnoreCase(keyword);
            default -> path.containsIgnoreCase(keyword);
        };
    }
}
