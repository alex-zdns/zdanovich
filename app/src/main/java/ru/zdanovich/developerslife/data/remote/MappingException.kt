package ru.zdanovich.developerslife.data.remote

import java.lang.IllegalStateException

class MappingException(
    fieldName: String
): IllegalStateException("Проблема конверсии поля $fieldName")