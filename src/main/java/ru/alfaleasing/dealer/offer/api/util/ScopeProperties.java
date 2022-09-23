package ru.alfaleasing.dealer.offer.api.util;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ScopeProperties {

    DEALER_OFFER("dealer-offer");

    private final String scopeName;

    public String getAlias() {
        return "SCOPE_" + scopeName;
    }
}
