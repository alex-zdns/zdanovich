package ru.zdanovich.developerslife.data.remote

import java.lang.IllegalStateException

class MappingException(
    fieldName: String
): IllegalStateException("Проблемы конверсии поля $fieldName")