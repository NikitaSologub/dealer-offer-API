package ru.alfaleasing.dealer.offer.api.constant;

/**
 * Статусы обработки объекта типа Task в системах ГОИ и КЛИ
 */
public enum TaskStatus {

    /**
     * task в процессе обработки
     */
    IN_WORK,

    /**
     * Обработка task была завершена успешно
     */
    DONE,

    /**
     * Обработка task была завершена неудачно
     */
    FAIL
}
