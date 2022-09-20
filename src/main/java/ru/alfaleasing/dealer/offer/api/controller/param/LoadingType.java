package ru.alfaleasing.dealer.offer.api.controller.param;

/**
 * Способы загрузки данных по стокам
 */
public enum LoadingType {

    /**
     * Напрямую используя api текущего микросервиса
     */
    API,

    /**
     * Через внешнюю ссылку
     */
    LINK,

    /**
     * Способы загрузки данных по стокам
     */
    FILE,

    /**
     * Используя api стороннего микросервиса
     */
    EXTERNAL_API
}
